package edu.gac.ATEP.shared;
import java.util.ArrayList;


public class Student extends User {
	private int classYear;
	private ArrayList<Assessment> myAssessments;
	
	
	public Student(String n, int cY) {
		super(n);
		classYear = cY;
		myAssessments = null;
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
}
