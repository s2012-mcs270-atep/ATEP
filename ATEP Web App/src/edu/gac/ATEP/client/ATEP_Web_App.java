package edu.gac.ATEP.client;

import java.util.ArrayList;
import java.util.List;

import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.AssessmentTempStore;
import edu.gac.ATEP.shared.AssessmentTempStoreAsync;
import edu.gac.ATEP.shared.AssessmentTemplate;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.FieldVerifier;
import edu.gac.ATEP.shared.Question;
import edu.gac.ATEP.shared.Student;
import edu.gac.ATEP.shared.StudentStore;
import edu.gac.ATEP.shared.StudentStoreAsync;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Hyperlink;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.widget.client.TextButton;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ATEP_Web_App implements EntryPoint {
	private final AssessmentTempStoreAsync assessmentStore = GWT.create(AssessmentTempStore.class);
	private final StudentStoreAsync studentStore = GWT.create(StudentStore.class);
	private ArrayList<Student> currentStudents;
	private StackPanel studentListPanel;
	private HorizontalPanel menuPanel;
	private VerticalPanel adminPanel;
	private VerticalPanel mainPanel;
	private VerticalPanel assessmentInfoPanel;
	private VerticalPanel assessmentPanel;
	private ArrayList<VerticalPanel> studentInfoPanels;
	private ArrayList<VerticalPanel> assessmentInfoPanels;
	private Long nextID = 1L; //TODO change if appropriate
	
	private Label updatingLabel;
	private Label failureLabel;
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		errorLabel.addStyleName("error");
		//final Button viewButton = new Button("View");	
		final Button addStudentButton = new Button("Add New Student");
		final Button removeStudentButton = new Button("Remove Student");
		final Button addAssessmentTemplateButton = new Button("Add New Assessment Template");
		final Button removeAssessmentTemplateButton = new Button("Remove Assessment Template");
		final Button studListButton = new Button("Student List");
		
		//stuff for testing
		ArrayList<Student> studentList = new ArrayList<Student>();
		
		ArrayList<Category> categoriesBones = new ArrayList<Category>();
		ArrayList<Question> questionsBones = new ArrayList<Question>();
		AssessmentTemplate bonesTemplate = new AssessmentTemplate("Bones Assessment", categoriesBones, 2);
		Category bones = new Category("Bones", questionsBones);
		Question bones1 = new Question("How come bones?");
		questionsBones.add(bones1);
		categoriesBones.add(bones);
		
		ArrayList<Category> categoriesFace = new ArrayList<Category>();
		ArrayList<Question> questionsFace = new ArrayList<Question>();
		AssessmentTemplate faceTemplate = new AssessmentTemplate("Face Assessment", categoriesFace, 3);
		Category face = new Category("Face", questionsFace);
		Question questionC1 = new Question("What's wrong with your face?");
		questionsFace.add(questionC1);
		categoriesFace.add(face);

		Student harry = new Student("Harry", 2);
		Student mary = new Student("Mary", 3);
		//harry.addAssessment(new Assessment(bonesTemplate, harry));
		//mary.addAssessment(new Assessment(bonesTemplate, mary));
		//mary.addAssessment(new Assessment(faceTemplate, mary));
		//studentList.add(harry);
		//studentList.add(mary);
		
		// Create main panel to hold the widgets together
		mainPanel = new VerticalPanel();
		mainPanel.add(errorLabel);
		
		assessmentPanel = new VerticalPanel();
		assessmentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		adminPanel = new VerticalPanel();
		menuPanel = new HorizontalPanel();
		adminPanel.add(new Label("Administrator Menu"));
		adminPanel.add(menuPanel);
		menuPanel.add(addStudentButton);
		menuPanel.add(removeStudentButton);
		menuPanel.add(addAssessmentTemplateButton);
		menuPanel.add(removeAssessmentTemplateButton);
		menuPanel.add(studListButton);
		
		//set up updating and failure labels
		final HorizontalPanel statusPanel = new HorizontalPanel();
		statusPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		statusPanel.setHeight("3em");
		updatingLabel = new Label("Updating...");
		updatingLabel.setVisible(false);
		failureLabel = new Label("Lost connection to server.");
		failureLabel.setVisible(false);
		statusPanel.add(updatingLabel);
		statusPanel.add(failureLabel);
		mainPanel.add(statusPanel);
		
		//TODO This code is redundant, but all i could think of at the time.  Lets make it more efficient.
////////////////////////////////Create a handler for the studListButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
		class studListHandler implements ClickHandler {
			//fired when the user clicks on the studListButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(true);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
			}
		}
		studListHandler goBack = new studListHandler();
		studListButton.addClickHandler(goBack);

		// Add the mainPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("applicationContainer");
		rootPanel.add(adminPanel);
		rootPanel.add(mainPanel);
		mainPanel.setSize("775px", "151px");
		rootPanel.add(assessmentPanel);
		assessmentPanel.setSize("775px", "18px");

		assessmentPanel.setVisible(false);
		adminPanel.setVisible(true);
		
		//Add rest of panel structure
		Label lblSearchStudents = new Label("Search Students");
		mainPanel.add(lblSearchStudents);
		lblSearchStudents.setWidth("420px");
		
		studentListPanel = new StackPanel();
		mainPanel.add(studentListPanel);
		studentListPanel.setWidth("775px");
		
		StackPanel assessmentTemplatePanel = new StackPanel();
		mainPanel.add(assessmentTemplatePanel);
		assessmentTemplatePanel.setSize("775px", "54px");
		
		Label lblAssessmentList = new Label("Assessments");
		lblAssessmentList.setWidth("240px");
		
//		StackPanel assessmentListPanel = new StackPanel();
//		assessmentListPanel.setWidth("240px");
		
		assessmentInfoPanel = new VerticalPanel();
		studentInfoPanels = new ArrayList<VerticalPanel>();
		assessmentInfoPanels = new ArrayList<VerticalPanel>();
		currentStudents = new ArrayList<Student>();
		
		
		studentStore.storeStudent(harry, 
			new AsyncCallback<Void>(){
				@Override
				public void onFailure(Throwable caught) {
					caught.printStackTrace();
					failureLabel.setVisible(true);
				}
	
				@Override
				public void onSuccess(Void result) {
					failureLabel.setVisible(false);
					updateStudentList();
				}
			});
		
		studentStore.storeStudent(mary, 
				new AsyncCallback<Void>(){
					@Override
					public void onFailure(Throwable caught) {
						failureLabel.setVisible(true);
					}
		
					@Override
					public void onSuccess(Void result) {
						failureLabel.setVisible(false);
						updateStudentList();
					}
				});
		/*studentStore.getStudents(nextID,
				new AsyncCallback<List<Student>>(){

				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(List<Student> studentList) {
					//Set up display of student list and list of assessments for each student
					//populate student and assessment lists
					constructStudentPanels(studentList);
				
				}

				});*/

		//if there are no students in the database, prompt to add students
		//for now, just add manually

//		private void constructStudentPanels(List<Student> newStudentList) {
//			VerticalPanel studentInfoPanel;

			for(Student s : studentList){
				StudentPanel newStudPanel = new StudentPanel(s, mainPanel, assessmentPanel);
				studentListPanel.add(newStudPanel, s.getName());
			}
		}
	
	
	//TODO We need to figure out how we want to handle updates vs. displaying the updated list.  
	// As you can see from Max's email, if we leave the project how it currently is, sometimes 
	// on the first load, it won't display the students added to the database on startup.  This
	// problem might go away once we implement assessors/admins adding students to the database
	// instead of just doing it for them with default students on startup.  This method below
	// will likely replace the storeStudent calls with harry and mary above.
		
	private void updateStudentList() {
		if(updatingLabel.isVisible()){
			return;
		}
		updatingLabel.setVisible(true);
		failureLabel.setVisible(false);
		studentStore.getStudents(nextID,
				new AsyncCallback<List<Student>>(){

					@Override
					public void onFailure(Throwable caught) {
					updatingLabel.setVisible(false);
					failureLabel.setVisible(true);
					}
		
					@Override
					public void onSuccess(List<Student> studentList) {
						updatingLabel.setVisible(false);
						for(Student s : studentList){
							StudentPanel newStudPanel = new StudentPanel(s, mainPanel, assessmentPanel);
							studentListPanel.add(newStudPanel, s.getName());
							}
						if(!studentList.isEmpty()){
							nextID = studentList.get(0).getID() + 1;
						}
					}

		});
	}
}
