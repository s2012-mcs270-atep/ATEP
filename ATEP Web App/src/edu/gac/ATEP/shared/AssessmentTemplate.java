package edu.gac.ATEP.shared;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.ArrayList;
import java.io.Serializable;


@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class AssessmentTemplate implements Serializable {
	@PrimaryKey
	@Persistent
	private String name;
	@Persistent(mappedBy="owner")
	private ArrayList<Category> categories;

	private int classYear; // used to determine who should take this assessment
	
	private static final long serialVersionID = 1L; 

	public AssessmentTemplate(String name, ArrayList<Category> categories, int classYear) {
		this.name = name;
		this.categories = categories;
		this.classYear = classYear;
		// interface idea: Assessor types in categories one by one, clicks "add category", 
		// each category added to a list of categories for this template which is passed as 
		// a parameter to this constructor
	}
	
	public String getName() {
		return name;
	}
	
	public int getClassYear() { 
		return classYear;
	}
	
	public ArrayList<Category> getCategories() {
		return categories;
	}
	
	@SuppressWarnings("unused") 
	private AssessmentTemplate(){}
}
