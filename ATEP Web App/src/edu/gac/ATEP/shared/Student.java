package edu.gac.ATEP.shared;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.gac.ATEP.client.StudentPanel;
import edu.gac.ATEP.shared.AssessmentTempStore;

import javax.jdo.PersistenceManager;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.rpc.AsyncCallback;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Student extends User implements Serializable {
	/**
	 * 
	 */
	private Long nextID = 1L;
	private final AssessmentTempStoreAsync tempStore = GWT.create(AssessmentTempStore.class);
	private static final long serialVersionUID = 1L;
	@Persistent(valueStrategy=IdGeneratorStrategy.SEQUENCE)
	private Long ID;
	@Persistent
	private int classYear;
	@Persistent(mappedBy="owner")
	private ArrayList<Assessment> myAssessments;
	
	
	public Student(String n, int cY) {
		super(n);
		this.classYear = cY;
		this.myAssessments = new ArrayList<Assessment>();
		
	}
	
	public int getClassYear() {
		return classYear;
	}
	
	public void addAssessment(Assessment a) {
		myAssessments.add(a);
	}
	
	public void populateAssessmentList(Student stud){
		final Student student1 = stud;
		tempStore.getAssessmentTemplates(nextID,
				new AsyncCallback<List<AssessmentTemplate>>(){

			@Override
			public void onFailure(Throwable caught) {
			}

			@Override
			public void onSuccess(List<AssessmentTemplate> tempList) {
				for(AssessmentTemplate temp : tempList){
						if (temp.getClassYear() == student1.getClassYear()){
							Assessment newAssessment = new Assessment(temp, student1);
							student1.addAssessment(newAssessment);
						}
					}
				if(!tempList.isEmpty()){
					nextID = tempList.get(0).getID() + 1;
				}
			}

});
	}
	
	public ArrayList<Assessment> getMyAssessments() {
		if (myAssessments == null) {
			return new ArrayList<Assessment>();
		}
		return myAssessments;
	}
	
	public String toString() {
		return super.toString() + " -- Year in Program: " + classYear;
	}
	
	@SuppressWarnings("unused")
	private Student(){
		super();
	}

	public Long getID() {
		return ID;
	}

	public void setMyAssessments(ArrayList<Assessment> assessments) {
		myAssessments = assessments;
	}
}
