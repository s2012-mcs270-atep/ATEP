package edu.gac.ATEP.shared;

public class Question {
	private String category; //possibly change to class instead of String
	private String bodyText;
	private int score; // not used for template
// each assessment has multiple Questions
	
	public Question(String category, String bodyText) {
		this.category = category;
		this.bodyText = bodyText;
		// score not initialized to prevent false results
	}

	public void setScore(int score2) {
		// TODO Auto-generated method stub
		// interface must ensure that score is between 0/1 and 5
		score = score2;
	}
	
	

}
