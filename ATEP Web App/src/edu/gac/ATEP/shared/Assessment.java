package edu.gac.ATEP.shared;
import java.util.ArrayList;


public class Assessment extends AssessmentTemplate {
	private int status; // 0 if not started, 1 if in progress, 2 if complete
	private Student owner;
	
	public Assessment(String name, ArrayList<Category> categories, int classYear, Student owner) {
		super(name, categories, classYear);
		this.owner = owner;
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
	
	public void setPassed() {
		
	}
	
	public void setComplete() {
		
	}
	
	public String toString() {
		return super.getName() + " Status: " + getStatus();
	}
	
	
	//methods for editing assessments go here? Or in Assessor class?
}
