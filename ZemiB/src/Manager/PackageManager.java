package Manager;

import java.util.Hashtable;

public class PackageManager {
	private Hashtable<String, ClassManager> classmanager_table;
	
	public Hashtable<String, ClassManager> getClassmanagerTable() {
		return this.classmanager_table;
	}
}
