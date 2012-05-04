package edu.gac.ATEP.shared;
import java.io.Serializable;
import java.util.ArrayList;



import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Student extends User implements Serializable {
	@PrimaryKey
	private String name;
	@Persistent
	private int classYear;
	@Persistent
	private ArrayList<Assessment> myAssessments;
	
	
	public Student(String n, int cY) {
		name = n;
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
	
	public String toString() {
		return super.toString() + " -- Year in Program: " + classYear;
	}
	
	@SuppressWarnings("unused")
	private Student(){
		super();
	}
}
