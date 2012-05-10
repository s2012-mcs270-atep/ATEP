package edu.gac.ATEP.shared;

public class Question {
	private String bodyText;
	private int score; // not used for template
// each assessment has multiple Questions
	
	public Question(String bodyText) {
		this.bodyText = bodyText;
		// score not initialized to prevent false results
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public void setScore(int score2) {
		// TODO Auto-generated method stub
		// interface must ensure that score is between 0/1 and 5
		score = score2;
	}
	
	

}
