package edu.gac.ATEP.shared;
import java.util.ArrayList;



public class Assessment{
	private String name;
	private int status; // 0 if not started, 1 if in progress, 2 if complete
	private Student owner;
	private int classYear; 
	
	public Assessment(AssessmentTemplate template, Student owner) {
		name = template.getName(); 
		classYear = template.getClassYear(); 
		this.owner = owner;
		status = 0;
		// have we accounted for all info. needed to initialize an assessment? 
		// make a copy of appropriate assessment form for specified student owner
	
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
	
	public void setInProgress() { 
		status = 1;
	}
	public void setComplete() {
		status = 2;
		
	}
	
	public String toString() {
		return super.getName() + " Status: " + getStatus();
	}
	
	
	//methods for editing assessments go here? Or in Assessor class?
}
