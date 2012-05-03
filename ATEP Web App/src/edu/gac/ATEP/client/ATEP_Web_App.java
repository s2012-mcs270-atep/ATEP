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

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ATEP_Web_App implements EntryPoint {
	private final AssessmentTempStoreAsync assessmentStore = GWT.create(AssessmentTempStore.class);
	private final StudentStoreAsync studentStore = GWT.create(StudentStore.class);
	private StackPanel studentListPanel;
	private VerticalPanel assessmentInfoPanel;
	private ArrayList<VerticalPanel> studentInfoPanels;
	private ArrayList<VerticalPanel> assessmentInfoPanels;
	private final long nextID = 1L; //TODO change if appropriate
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
		
		//stuff for testing
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Question> questions = new ArrayList<Question>();

		Category category1 = new Category("Category 1", questions);
		Question q1 = new Question(category1, "Body of Question 1");
		Student harry = new Student("Harry", 2);
		Student mary = new Student("Mary", 3);
		AssessmentTemplate aT = new AssessmentTemplate("Template 1", categories, 2);
		harry.addAssessment(new Assessment(aT, harry));
		mary.addAssessment(new Assessment(aT, mary));
		studentList.add(harry);
		studentList.add(mary);
		
		// Create some panels to hold the widgets together
		final VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(errorLabel);

		// Add the mainPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("applicationContainer");
		rootPanel.add(mainPanel);
		
		Label lblSearchStudents = new Label("Search Students");
		mainPanel.add(lblSearchStudents);
		lblSearchStudents.setWidth("420px");
		
		studentListPanel = new StackPanel();
		mainPanel.add(studentListPanel);
		studentListPanel.setWidth("420px");
		
		StackPanel assessmentTemplatePanel = new StackPanel();
		mainPanel.add(assessmentTemplatePanel);
		assessmentTemplatePanel.setSize("418px", "54px");
		
		Label lblAssessmentList = new Label("Assessments");
		lblAssessmentList.setWidth("240px");
		
		StackPanel assessmentListPanel = new StackPanel();
		assessmentListPanel.setWidth("240px");
		
		assessmentInfoPanel = new VerticalPanel();
		studentInfoPanels = new ArrayList<VerticalPanel>();
		assessmentInfoPanels = new ArrayList<VerticalPanel>();
		
		//if there are no students in the database, prompt to add students
		//for now, just add manually
		studentStore.storeStudent(harry, 
			new AsyncCallback<Void>(){
				@Override
				public void onFailure(Throwable caught) {
					//might want to add a failure label here later
				}
	
				@Override
				public void onSuccess(Void result) {
					updateStudentList();
				}
			});
		
		studentStore.getStudents(nextID,
				new AsyncCallback<List<Student>>(){

				@Override
				public void onFailure(Throwable caught) {
				}

				@Override
				public void onSuccess(List<Student> studentList) {
				//Set up display of student list and list of assessments for each student
					//populate student and assessment lists
				int i = 0;
				int j = 0;
				for(Student s : studentList){
					//add student info to the student info panel for each student displayed
					studentInfoPanels.add(new VerticalPanel());
					VerticalPanel studentInfoPanel = studentInfoPanels.get(i);
					studentInfoPanel.add(new Label("Year in program: " + s.getClassYear()));
					studentInfoPanel.add(new Button("Delete this student")); //remove if not admin
					studentInfoPanel.add(new Label("Current Assessments:"));
					ArrayList<Assessment> assessments = s.getMyAssessments();
					for (Assessment a : assessments) {
						assessmentInfoPanels.add(new VerticalPanel());
						assessmentInfoPanel = assessmentInfoPanels.get(j);
						assessmentInfoPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
						assessmentInfoPanel.add(new Button("Delete this assessment"));
						studentInfoPanel.add(assessmentInfoPanel);
						j++;
					}
					i++;
				}
				
				}

				});
		
	

		// Create a handler for the sendButton and nameField
		class MyHandler implements ClickHandler, KeyUpHandler {
			/**
			 * Fired when the user clicks on the sendButton.
			 */
			public void onClick(ClickEvent event) {
				sendNameToServer();
			}

			/**
			 * Fired when the user types in the nameField.
			 */
			public void onKeyUp(KeyUpEvent event) {
				if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
					sendNameToServer();
				}
			}

			/**
			 * Send the name selected from the student list to the server and wait for a response.
			 */
			private void sendNameToServer() {
				// TODO we may need this method later when a student name 
				//      is selected in order to retrieve their info
			}	
		}

		// Add a handler to send the name to the server
		MyHandler handler = new MyHandler();
	}
	
	private void updateStudentList() {
		/*if(updatingLabel.isVisible()){
			return;
		}*/
		/*updatingLabel.setVisible(true);
		failureLabel.setVisible(false);*/
		studentStore.getStudents(nextID,
				new AsyncCallback<List<Student>>(){

					@Override
					public void onFailure(Throwable caught) {
					/*updatingLabel.setVisible(false);
					failureLabel.setVisible(true);*/
					}
		
					@Override
					public void onSuccess(List<Student> studentList) {
					//updatingLabel.setVisible(false);
					int i = 0;
					int j = 0;
					for(Student s : studentList){
						//call method to display list
						VerticalPanel studentInfoPanel = studentInfoPanels.get(i);
						studentInfoPanel.add(new Label("Year in program: " + s.getClassYear()));
						studentInfoPanel.add(new Button("Delete this student")); //remove if not admin
						studentInfoPanel.add(new Label("Current Assessments:"));
						ArrayList<Assessment> assessments = s.getMyAssessments();
						for (Assessment a : assessments) {
							assessmentInfoPanel = assessmentInfoPanels.get(j);
							assessmentInfoPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
							assessmentInfoPanel.add(new Button("Delete this assessment"));
							studentInfoPanel.add(assessmentInfoPanel);
							j++;
						}
						i++;
					}
		
					}

		});
	}
}
