package edu.gac.ATEP.shared;
import java.util.ArrayList;


public class Student extends User {
	private int classYear;
	private ArrayList<Assessment> myAssessments;
	
	
	public Student(String n, int cY) {
		super(n);
		classYear = cY;
	}
	
	public int getClassYear() {
		return classYear;
	}
	
	public String toString() {
		return super.toString() + " -- Year in Program: " + classYear;
	}
}
