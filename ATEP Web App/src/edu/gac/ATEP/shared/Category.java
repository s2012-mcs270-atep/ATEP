package edu.gac.ATEP.shared;
import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Category implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String key;
	@Persistent
	private String name;
	@Persistent
	private ArrayList<Question> questions;
	@Persistent
	private AssessmentTemplate owner;
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
	
	@SuppressWarnings("unused")
	private Category(){}
}
