package edu.gac.ATEP.shared;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.user.client.ui.Label;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Assessment implements Serializable {
	@SuppressWarnings("unused")
  @PrimaryKey
  	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String key;
	@Persistent
	private String name; //need to make sure this name matches the name of the template it 
						// was created from
	@Persistent
	private int status; // 0 if not started, 1 if in progress, 2 if complete
	@Persistent
	private Student owner;
	@Persistent
	private int classYear; 
	private AssessmentTemplate baseTemplate;
	
	public Assessment(AssessmentTemplate template, Student owner) {
		name = template.getName(); 
		classYear = template.getClassYear(); 
		this.owner = owner;
		status = 0;
		baseTemplate = template;
		// have we accounted for all info. needed to initialize an assessment? 
		// make a copy of appropriate assessment form for specified student owner
	
	}
	
	public String getName() {
		return name;
	}
	public String getStatus() {
		String notStarted = new String("Not Started");
		String inProgress = new String("In Progress");
		String complete = new String("Complete");
		if (status == 0) {
			return notStarted;
		} else if (status == 1) {
			return inProgress;
		} else {
			return complete;
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
	
	public ArrayList<Category> getCategories() {
		//if (baseTemplate == null) {
		return baseTemplate.getCategories();
		//}
		//return baseTemplate.getCategories();
	}
	
	public String toString() {
		return name + " for " + owner.getName() + ". Status: " + getStatus();
	}
	
	@SuppressWarnings("unused")
	private Assessment(){}
	//methods for editing assessments go here? Or in Assessor class?

	public void setOwner(Student s) {
		owner = s;
		
	}
}
