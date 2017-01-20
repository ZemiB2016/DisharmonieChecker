import java.io.File;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.regex.Pattern;

import org.eclipse.jdt.core.dom.CompilationUnit;

public class ZemiB {
	
	public static int FEW = 5;
	public static int MANY = 8;

	public static void main(final String[] args) {

		Hashtable<String, Hashtable<String, Integer>> data_list = check_directory(new File(args[0]));
		Hashtable<String, Hashtable<String, Double>> threshold_list = get_threshold_list(data_list);
		ArrayList<String> godclass_list = get_godclass_list(data_list, threshold_list);
		ArrayList<String> dataclass_list = get_dataclass_list(data_list, threshold_list);
		System.out.println("godclass");
		for (String file: godclass_list) {
			System.out.println(file);
		}
		System.out.println("dataclass");
		for (String file: dataclass_list) {
			System.out.println(file);
		}
	}
	
	public static ArrayList<String> get_godclass_list(Hashtable<String, Hashtable<String, Integer>> data_list,
			Hashtable<String, Hashtable<String, Double>> threshold_list){
		ArrayList<String> godclass_list = new ArrayList<String>();
		for (String file: data_list.keySet()) {
			if (data_list.get(file).get("ATFD") > FEW &&
					data_list.get(file).get("WMC") > threshold_list.get("WMC").get("VeryHigh") &&
					data_list.get(file).get("TCC") < 1/3){
				godclass_list.add(file);
			}
		}
		return godclass_list;
	}
	
	public static ArrayList<String> get_dataclass_list(Hashtable<String, Hashtable<String, Integer>> data_list,
			Hashtable<String, Hashtable<String, Double>> threshold_list){
		ArrayList<String> dataclass_list = new ArrayList<String>();
		for (String file: data_list.keySet()) {
			int NOAP_NOAM = data_list.get(file).get("NOAP") + data_list.get(file).get("NOAM");
			int WMC = data_list.get(file).get("WMC");
			if ((NOAP_NOAM > FEW && WMC < threshold_list.get("WMC").get("High")) ||
					NOAP_NOAM > MANY && WMC < threshold_list.get("WMC").get("VeryHigh")) {
				dataclass_list.add(file);
			}
		}
		return dataclass_list;
	}
	
	public static Hashtable<String, Hashtable<String, Integer>> check_directory(File dir){
		Hashtable<String, Hashtable<String, Integer>> data_list = new Hashtable<String, Hashtable<String, Integer>>();
		File[] files = dir.listFiles();
		if (files == null) {
			return new Hashtable<String, Hashtable<String, Integer>>();
		}
		for (File file: files) {
			String regex = "^.*\\.java$";
			if (!file.exists()) {
				continue;
			}else if (file.isDirectory()) {
				data_list.putAll(check_directory(file));
			}else if (file.isFile() && Pattern.compile(regex).matcher(file.getName()).find()) {
				data_list.put(file.getAbsolutePath(), get_file_value(file));
			}
		}
		return data_list;
	}
	
	public static Hashtable<String, Integer> get_file_value(File file) {
		System.out.println(file.getAbsolutePath());
		final CompilationUnit unit = ZemiBASTVisitor.createAST(file.getAbsolutePath());
		final ZemiBASTVisitor visitor = new ZemiBASTVisitor();
		unit.accept(visitor);
		return visitor.output();
	}
	
	public static Hashtable<String, ArrayList<Integer>> add_data_list(Hashtable<String, ArrayList<Integer>> data_list,
			Hashtable<String, ArrayList<Integer>> new_data){
		for (String key: new_data.keySet()) {
			if (!data_list.containsKey(key)) {
				data_list.put(key, new ArrayList<Integer>());
			}
			data_list.get(key).addAll(new_data.get(key));
		}
		return data_list;
	}
	
	public static Hashtable<String, Hashtable<String, Integer>> add_data(Hashtable<String, Hashtable<String, Integer>> data_list,
			Hashtable<String, Integer> data){
		for (String key: data.keySet()) {
			if (!data_list.containsKey(key)) {
				data_list.put(key, new ArrayList<Integer>());
			}
			data_list.get(key).add(data.get(key));
		}
		return data_list;
	}
	
	public static Hashtable<String, Hashtable<String, Double>> get_threshold_list(Hashtable<String, Hashtable<String, Integer>> data_list) {
		Hashtable<String, Hashtable<String, Double>> threshold_list = new Hashtable<String, Hashtable<String, Double>>();
		Hashtable<String, ArrayList<Integer>> value_list = new Hashtable<String, ArrayList<Integer>>();
		for (Hashtable<String, Integer> data: data_list.values()) {
			Hashtable<String, Integer> type_list = new Hashtable<String, Integer>();
			for (String type: data.keySet()) {
				if (!value_list.containsKey(type)) {
					value_list.put(type, new ArrayList<Integer>());
				}
				value_list.get(type).add(data.get(type));
			}
		}
		for (String key: value_list.keySet()) {
			double sum = 0;
			double ave;
			double var = 0;
			double std;
			Hashtable<String, Double> threshold = new Hashtable<String, Double>();
			for (Integer val: value_list.get(key)) {
				sum += val.doubleValue();
			}
			ave = sum / value_list.get(key).size();
			for (Integer val: value_list.get(key)) {
				var += (val.doubleValue() - ave) * (val.doubleValue() - ave);
			}
			std = Math.sqrt(var / value_list.get(key).size());
			threshold.put("Avg", ave);
			threshold.put("Std", std);
			threshold.put("Low", ave - std);
			threshold.put("High", ave + std);
			threshold.put("VeryHigh", (ave + std) * 1.5);
			threshold_list.put(key, threshold);
		}
		return threshold_list;
	}
}
