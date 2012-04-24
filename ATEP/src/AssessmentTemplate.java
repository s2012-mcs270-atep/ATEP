import javax.swing.JCheckBox;
import java.util.ArrayList;


public class AssessmentTemplate {
	private String name;
	private ArrayList<Category> categories;
	
	private int classYear; // used to determine who should take this assessment
	
	public AssessmentTemplate(String name, ArrayList<Category> categories, int classYear) {
		this.name = name;
		this.categories = categories;
		this.classYear = classYear;
		// interface idea: Assessor types in categories one by one, clicks "add category", 
		// each category added to a list of categories for this template which is passed as 
		// a parameter to this constructor
	}
	
	public void loadFromTemplate(AssessmentTemplate template){
		for (Category category: categories){
			for (Question question: category.questions){
				final JCheckBox oneBox = new JCheckBox("1");
				final JCheckBox twoBox = new JCheckBox("2");
				final JCheckBox threeBox = new JCheckBox("3");
				final JCheckBox fourBox = new JCheckBox("4");
				final JCheckBox fiveBox = new JCheckBox("5");
				
				
				
			}
		}
	}
	
	
}
