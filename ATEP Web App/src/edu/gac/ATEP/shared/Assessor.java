package edu.gac.ATEP.shared;

public class Assessor extends User {
	
	public Assessor(String n) {
		super(n);
	}
	
	public void gradeQuestion(Question q, int score) {
		q.setScore(score);
	}	
}