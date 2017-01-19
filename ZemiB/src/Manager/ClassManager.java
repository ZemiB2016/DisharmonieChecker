package Manager;

import java.util.ArrayList;

public class ClassManager {
	private ArrayList<MethodManager> methodmanager_list = new ArrayList<MethodManager>();
	private int ATFD;

	public ArrayList<MethodManager> getMethodmanagerTable() {
		return this.methodmanager_list;
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
		methodmanager_list.add(methodmanager);
	}
}
