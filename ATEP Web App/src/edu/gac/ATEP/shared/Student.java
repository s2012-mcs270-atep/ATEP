package edu.gac.ATEP.shared;
import java.io.Serializable;
import java.util.ArrayList;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Student extends User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Persistent(valueStrategy=IdGeneratorStrategy.SEQUENCE)
	private Long ID;
	@Persistent
	private int classYear;
	@Persistent(mappedBy="owner")
	private ArrayList<Assessment> myAssessments;


	public Student(String n, int cY) {
		super(n);
		classYear = cY;
		myAssessments = new ArrayList<Assessment>();
	}

	public int getClassYear() {
		return classYear;
	}

	public void addAssessment(Assessment a) {
		myAssessments.add(a);
	}

	public ArrayList<Assessment> getMyAssessments() {
		if (myAssessments == null) {
			return new ArrayList<Assessment>();
		}
		return myAssessments;
	}

	public void setMyAssessments(ArrayList<Assessment> assessments) {
		myAssessments = assessments;
	}

	public Long getID() {
		return ID;
	}

	public String toString() {
		return super.toString() + " -- Year in Program: " + classYear;
	}

	@SuppressWarnings("unused")
	private Student(){
		super();
	}
}
