import java.util.ArrayList;


public class Student extends User {
	private int classYear;
	private ArrayList<Assessment> myAssessments;
	
	
	public Student(String n, int cY) {
		super(n);
		classYear = cY;
	}
	
	
}
