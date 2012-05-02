package edu.gac.ATEP.shared;

import java.io.Serializable;



public class Assessment implements Serializable {
	private String name; //need to make sure this name matches the name of the template it 
						 // was created from
	private int status; // 0 if not started, 1 if in progress, 2 if complete
	private Student owner;
	private int classYear; 
	
	private static final long serialVersionID = 1L;
	
	public Assessment(AssessmentTemplate template, Student owner) {
		name = template.getName(); 
		classYear = template.getClassYear(); 
		this.owner = owner;
		status = 0;
		// have we accounted for all info. needed to initialize an assessment? 
		// make a copy of appropriate assessment form for specified student owner
	
	}
	
	public String getName() {
		return name;
	}
	
	public String getStatus() {
		if (status == 0) {
			return "Not Started";
		} else if (status == 1) {
			return "In Progress";
		} else {
			return "Complete";
		}
	}
	public int getClassYear() { 
		return classYear; 
		
	}
	
	public void setInProgress() { 
		status = 1;
	}
	public void setComplete() {
		status = 2;
		
	}
	
	public String toString() {
		return name + " for " + owner.getName() + ". Status: " + getStatus();
	}
	
	@SuppressWarnings("unused")
	private Assessment(){}
	//methods for editing assessments go here? Or in Assessor class?
}
