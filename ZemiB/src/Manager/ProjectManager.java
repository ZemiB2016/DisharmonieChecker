package Manager;

import java.util.Hashtable;

public class ProjectManager {
	private Hashtable<String, PackageManager> packagemanager_table; 
	private int NOP;
	private int NOC;
	private int NOM;
	private int LOC;
	private int CYCLO;
	private int CALLS;
	private int FANOUT;
	private int ANDC;
	private int AHH;

	public Hashtable<String, PackageManager> getPackagemanagerTable() {
		return this.packagemanager_table;
	}
	
	public int getNOP() {
		return this.NOP;
	}

	public int getNOC() {
		return this.NOC;
	}

	public int getNOM() {
		return this.NOM;
	}

	public int getLOC() {
		return this.LOC;
	}

	public int getCYCLO() {
		return this.CYCLO;
	}

	public int getCALLS() {
		return this.CALLS;
	}

	public int getFANOUT() {
		return this.FANOUT;
	}

	public int getANDC() {
		return this.ANDC;
	}

	public int getAHH() {
		return this.AHH;
	}

	public int HighlevelStructuring() {
		return 0;
	}

	public int OperationStructuring() {
		return 0;
	}

	public int IntrinsticOperationComplexity() {
		return 0;
	}

	public int CouplingIntensity() {
		return 0;
	}

	public int CouplingDispersion() {
		return 0;
	}
}
