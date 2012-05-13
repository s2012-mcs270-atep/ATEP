package edu.gac.ATEP.shared;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;
import java.util.ArrayList;
import java.io.Serializable;
import edu.gac.ATEP.server.AssessmentTempStoreImpl;


@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class AssessmentTemplate implements Serializable {
	@PrimaryKey
	@Persistent
	private String name;
	@Persistent(mappedBy="owner")
	private ArrayList<Category> categories;
	@Persistent(valueStrategy=IdGeneratorStrategy.SEQUENCE)
	private Long ID;
	public static ArrayList<AssessmentTemplate> templates;

	private int classYear; // used to determine who should take this assessment
	
	private static final long serialVersionUID = 1L; 

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
	
	ArrayList<Category> categoriesBones = new ArrayList<Category>();
	ArrayList<Question> questionsBones = new ArrayList<Question>();
	AssessmentTemplate bonesTemplate = new AssessmentTemplate("Bones Assessment", categoriesBones, 2);
	Category bones = new Category("Bones", questionsBones);
	Question bones1 = new Question("How come bones?");
	//questionsBones.add(bones1);
	//categoriesBones.add(bones);
	
	ArrayList<Category> categoriesFace = new ArrayList<Category>();
	ArrayList<Question> questionsFace = new ArrayList<Question>();
	AssessmentTemplate faceTemplate = new AssessmentTemplate("Face Assessment", categoriesFace, 3);
	Category face = new Category("Face", questionsFace);
	Question questionC1 = new Question("What's wrong with your face?");
	//questionsFace.add(questionC1);
	//categoriesFace.add(face);

	public Long getID() {
		return ID;
	}
	
	@SuppressWarnings("unused") 
	private AssessmentTemplate(){}
}

