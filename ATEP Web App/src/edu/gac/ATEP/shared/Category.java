package edu.gac.ATEP.shared;
import java.util.ArrayList;


public class Category {
	private String name;
	public ArrayList<Question> questions;
//category of questions

	public Category(String name, ArrayList<Question> questions) {
		this.name = name;
		this.questions = questions;
	}
	
	public void addQuestion(Question q) {
		questions.add(q);
		// consider other options for where to place question in list
	}
	
	public ArrayList<Question> getQuestions(){
		return questions;
	}
	
	public String getName(){
		return name;
	}
}
