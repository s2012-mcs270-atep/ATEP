import java.util.ArrayList;


public class Assessment extends AssessmentTemplate {
	private boolean isComplete;
	private boolean isPassed;
	private Student owner;
	
	public Assessment(String name, ArrayList<Category> categories, int classYear, Student owner) {
		super(name, categories, classYear);
		this.owner = owner;
		// make a copy of appropriate assessment form for specified student owner
	}
	
	//methods for editing assessments go here? Or in Assessor class?
}
