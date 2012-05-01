package edu.gac.ATEP.client;

import java.util.ArrayList;

import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.AssessmentTemplate;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.FieldVerifier;
import edu.gac.ATEP.shared.Question;
import edu.gac.ATEP.shared.Student;

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
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
	private static final String SERVER_ERROR = "An error occurred while "
			+ "attempting to contact the server. Please check your network "
			+ "connection and try again.";

	/**
	 * Create a remote service proxy to talk to the server-side Greeting service.
	 */
	private final GreetingServiceAsync greetingService = GWT
			.create(GreetingService.class);

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		errorLabel.addStyleName("error");
		
		ArrayList<Student> studentList = new ArrayList<Student>();
		// Test name
		ArrayList<Category> categories = new ArrayList<Category>();
		ArrayList<Question> questions = new ArrayList<Question>();

		Category category1 = new Category("Category 1", questions);
		Question q1 = new Question(category1, "Body of Question 1");
		Student harry = new Student("Harry", 2);
		AssessmentTemplate aT = new AssessmentTemplate("Template 1", categories, 2);
		harry.addAssessment(new Assessment(aT, harry));
		studentList.add(harry);
		studentList.add(new Student("Mary", 1));
		//TODO insert code here to fill the list
		
		// Create some panels to hold the widgets together
		final VerticalPanel mainPanel = new VerticalPanel();
		mainPanel.add(errorLabel);

		// Add the mainPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("applicationContainer");
		rootPanel.add(mainPanel);
		
		Label lblSearchStudents = new Label("Search Students");
		mainPanel.add(lblSearchStudents);
		lblSearchStudents.setWidth("240px");
		
		StackPanel studentListPanel = new StackPanel();
		mainPanel.add(studentListPanel);
		studentListPanel.setWidth("240px");
		
		Label lblEditTemplates = new Label("Edit Assessment Templates");
		mainPanel.add(lblEditTemplates);
		lblEditTemplates.setWidth("240px");
		
		StackPanel assessmentTemplatePanel = new StackPanel();
		mainPanel.add(assessmentTemplatePanel);
		assessmentTemplatePanel.setSize("240px", "54px");
		
		Label lblAssessmentList = new Label("Assessments");
		lblAssessmentList.setWidth("240px");
		
		StackPanel assessmentListPanel = new StackPanel();
		assessmentListPanel.setWidth("240px");

		
		for (Student s : studentList) {
			studentListPanel.add(new Label("Year in Program: " + s.getClassYear()), s.getName());
			studentListPanel.add(new Button("Delete this student"));
			studentListPanel.add(lblAssessmentList);
			studentListPanel.add(assessmentListPanel);
			ArrayList<Assessment> assessments = s.getMyAssessments();
			for (Assessment a : assessments) {
				assessmentListPanel.add(new Label(a.getName() + " Status: " + a.getStatus()));
				studentListPanel.add(new Button("Delete this assessment"));
			}
		}
		
		//end of assessor/student stuff
		
		

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
}
