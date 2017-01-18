package Manager;

import java.util.Hashtable;

public class ClassManager {
	private Hashtable<String, MethodManager> methodmanager_table;
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
}
