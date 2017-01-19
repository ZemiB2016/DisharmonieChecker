package Manager;

import java.util.Hashtable;

public class ClassManager {
	private Hashtable<String, MethodManager> methodmanager_table = new Hashtable<String,MethodManager>();
	private int ATFD;

	public Hashtable<String, MethodManager> getMethodmanagerTable() {
		return this.methodmanager_table;
	}

	public int getATFD() {
		return this.ATFD;
	}

	public int WMC() {
		return 0;
	}

	public int TCC() {
		return 0;
	}

	public void SetMethodmanagerTable(String str,MethodManager methodmanager){
		methodmanager_table.put(str, methodmanager);
	}

}
