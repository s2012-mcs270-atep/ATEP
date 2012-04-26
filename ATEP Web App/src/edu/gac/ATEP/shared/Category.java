package edu.gac.ATEP.shared;
import java.util.ArrayList;


public class Category {
	public ArrayList<Question> questions;
//category of questions

	public Category(ArrayList<Question> questions) {
		this.questions = questions;
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
		// consider other options for where to place question in list
	}
}
