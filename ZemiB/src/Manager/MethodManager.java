package Manager;

import java.util.ArrayList;

public class MethodManager {
	private int MAXNESTING;
	private ArrayList<String> fieldList = new ArrayList<String>();

	public int getMAXNESTING() {
		return this.MAXNESTING;
	}

	public int CINT() {
		return 0;
	}

	public int CDIPS() {
		return 0;
	}

	public void setUseField(String str){
		fieldList.add(str);
	}

}
