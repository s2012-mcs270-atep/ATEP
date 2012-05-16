package edu.gac.ATEP.shared;

public class Administrator extends Assessor {
	
	
	public Administrator(String n) {
		super(n);
		//adds each admin to the adminList
		adminList.add(this);
	}
	
	
}
