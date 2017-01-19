package Manager;

import java.util.ArrayList;

public class ClassManager {
	private ArrayList<MethodManager> methodmanager_list = new ArrayList<MethodManager>();
	private int ATFD = 0;
	private int numOfLoop = 0;
	private int numOfIf = 0;
	private int numOfPublicAttributes = 0;
	private int numOfAccessorMethod = 0;

	public ArrayList<MethodManager> getMethodmanagerTable() {
		return this.methodmanager_list;
	}

	public void addNumOfLoop(){
		numOfLoop++;
	}

	public void addNumOfIf(){
		numOfIf++;
	}

	public void addATFD(){
		ATFD++;
	}

	public int getATFD() {
		return this.ATFD;
	}

	public int WMC() {
		return 1 + numOfLoop + numOfIf;
	}

	public void addNOAP(){
		numOfPublicAttributes++;
	}
	public int getNOAP(){
		return this.numOfPublicAttributes;
	}

	public void addNOAM(){
		numOfAccessorMethod++;
	}

	public int getNOAM(){
		return this.numOfAccessorMethod;
	}

	public int TCC() {
		int sumRelative;
		int connectMethod = 0;
		int n = methodmanager_list.size()-1;

		sumRelative = n * (n-1) /2;

		for(MethodManager obj1: methodmanager_list){
			for(MethodManager obj2: methodmanager_list){
				if(!obj1.getName().equals(obj2.getName())){
					if(obj1.CheckConnect(obj2.getFieldList())){
						connectMethod++;
						break;
					}
				}
			}
		}
		return connectMethod/sumRelative;
	}

	public void SetMethodmanagerTable(MethodManager methodmanager){
		methodmanager_list.add(methodmanager);
	}
}