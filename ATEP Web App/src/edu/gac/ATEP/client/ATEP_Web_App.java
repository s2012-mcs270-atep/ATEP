package edu.gac.ATEP.client;

import java.util.ArrayList;


import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.AssessmentTemplate;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.Question;
import edu.gac.ATEP.shared.Student;

//import edu.gac.ATEP.shared.AssessmentTempStore;
//import edu.gac.ATEP.shared.AssessmentTempStoreAsync;
//import edu.gac.ATEP.shared.StudentStore;
//import edu.gac.ATEP.shared.StudentStoreAsync;
//import com.google.gwt.user.client.rpc.AsyncCallback;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.StackPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class ATEP_Web_App implements EntryPoint {
//Assessmentstore and Studentstore are declared below as they would need to be used for persistence. 
//	private final AssessmentTempStoreAsync assessmentTempStore = GWT.create(AssessmentTempStore.class);
//	private final StudentStoreAsync studentStore = GWT.create(StudentStore.class);
	
	
	private StackPanel studentListPanel;
	private HorizontalPanel menuPanel;
	private VerticalPanel adminPanel;
	private VerticalPanel mainPanel;
	private VerticalPanel assessmentPanel;
	private VerticalPanel addStudentPanel;
	private VerticalPanel removeStudentPanel;
	private VerticalPanel addAssessmentTemplatePanel;
	private VerticalPanel removeAssessmentTemplatePanel;
//	private Long nextID = 1L; //TODO change if appropriate
	
//	private Label updatingLabel;
//	private Label failureLabel;
	/**
	 * The message displayed to the user when the server cannot be reached or
	 * returns an error.
	 */
//	private static final String SERVER_ERROR = "An error occurred while "
//			+ "attempting to contact the server. Please check your network "
//			+ "connection and try again.";


	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		errorLabel.addStyleName("error");
		final Button addStudentButton = new Button("Add New Student");
		final Button addStudentToListButton = new Button("Add Student");
		final Button removeStudentButton = new Button("Remove Student");
		final Button addAssessmentTemplateButton = new Button("Add New Assessment Template");
		final Button removeAssessmentTemplateButton = new Button("Remove Assessment Template");
		final Button studListButton = new Button("Student List");
		final Button removeTheseStudentsButton = new Button("Remove (these) Student(s)");
		final Button addTemplateButton = new Button("Add Template");
		final Button removeTheseAssessmentTemplatesButton = new Button("Remove (these) Assessment Template(s)");
		
		//STUFF FOR TESTING
		final ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<AssessmentTemplate> templateList = new ArrayList<AssessmentTemplate>();
		
		//Create Sample Assessment Templates
		ArrayList<Category> categoriesBones = new ArrayList<Category>();
		ArrayList<Question> questionsBones = new ArrayList<Question>();
		AssessmentTemplate bonesTemplate = new AssessmentTemplate("Bones Assessment", categoriesBones, 2);
		Category bones = new Category("Bones", questionsBones);
		Question bones1 = new Question("How come bones?");
		questionsBones.add(bones1);
		categoriesBones.add(bones);
		templateList.add(bonesTemplate);
		
		ArrayList<Category> categoriesFace = new ArrayList<Category>();
		ArrayList<Question> questionsFace = new ArrayList<Question>();
		AssessmentTemplate faceTemplate = new AssessmentTemplate("Face Assessment", categoriesFace, 3);
		Category face = new Category("Face", questionsFace);
		Question questionC1 = new Question("What's wrong with your face?");
		Question questionC2 = new Question("How many noses are on a normal face?");
		questionsFace.add(questionC1);
		questionsFace.add(questionC2);
		categoriesFace.add(face);
		templateList.add(faceTemplate);
		
		//Create Sample Students
		Student harry = new Student("Harry", 2);
		Student mary = new Student("Mary", 3);
		harry.addAssessment(new Assessment(bonesTemplate, harry));
		mary.addAssessment(new Assessment(bonesTemplate, mary));
		mary.addAssessment(new Assessment(faceTemplate, mary));
		studentList.add(harry);
		studentList.add(mary);
		
		// Create main panel to hold the widgets together
		mainPanel = new VerticalPanel();
		mainPanel.add(errorLabel);
		
		// Create assessment panel to hold assessment data
		assessmentPanel = new VerticalPanel();
		assessmentPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
		
		// Create admin panel to hold the menu panel containing administrative tools
		adminPanel = new VerticalPanel();
		adminPanel.add(new Label("Administrator Menu"));
		
		// Create menu panel to hold all of the buttons for administrative use only
		menuPanel = new HorizontalPanel();
		menuPanel.add(addStudentButton);
		menuPanel.add(removeStudentButton);
		menuPanel.add(addAssessmentTemplateButton);
		menuPanel.add(removeAssessmentTemplateButton);
		menuPanel.add(studListButton);
		adminPanel.add(menuPanel);
		
		// Create add student panel to display the tools for adding a new student
		addStudentPanel = new VerticalPanel();
		Label addStudentLabel = new Label("Enter the Gustavus email address of the student you would like to add: ");
		addStudentPanel.add(addStudentLabel);
		HorizontalPanel sendStudentPanel = new HorizontalPanel();
		final TextBox studentName = new TextBox();
		sendStudentPanel.add(studentName);
		sendStudentPanel.add(addStudentToListButton);
		studentName.setWidth("211px");
		addStudentPanel.add(sendStudentPanel);
		
		// Create remove student panel to display a list of all students for administrator to choose which ones to remove
		removeStudentPanel = new VerticalPanel();
		Label removeStudentLabel = new Label("Please select the student(s) from the list that you would like to remove: ");
		removeStudentPanel.add(removeStudentLabel);
		for(Student s : studentList){
			HorizontalPanel studentToRemove = new HorizontalPanel();
			studentToRemove.add(new CheckBox(s.getName()));
			removeStudentPanel.add(studentToRemove);
		}
		removeStudentPanel.add(removeTheseStudentsButton);
		
		// Create add assessment template panel to display the tools for adding a new assessment template
		addAssessmentTemplatePanel = new VerticalPanel();
		Label addAssessmentTemplateLabel = new Label("Enter the name of the new Assessment Template you would like to add: ");
		addAssessmentTemplatePanel.add(addAssessmentTemplateLabel);
		HorizontalPanel sendTemplatePanel = new HorizontalPanel();
		final TextBox templateName = new TextBox();
		sendTemplatePanel.add(templateName);
		sendTemplatePanel.add(addTemplateButton);
		templateName.setWidth("211px");
		addAssessmentTemplatePanel.add(sendTemplatePanel);
		
		// Create remove assessment template panel to display a list of all assessment templates for administrator to choose which ones to remove
		removeAssessmentTemplatePanel = new VerticalPanel();
		Label removeAssessmentTemplateLabel = new Label("Please select the Assessment Template(s) from the list that you would like to remove: ");
		removeAssessmentTemplatePanel.add(removeAssessmentTemplateLabel);
		for(AssessmentTemplate t : templateList){
			HorizontalPanel templateToRemove = new HorizontalPanel();
			templateToRemove.add(new CheckBox(t.getName()));
			removeAssessmentTemplatePanel.add(templateToRemove);
		}
		removeAssessmentTemplatePanel.add(removeTheseAssessmentTemplatesButton);
		
		
	//Status Panel allows the user to see when problems occur when using persistence. 
		
		//set up updating and failure labels
//		final HorizontalPanel statusPanel = new HorizontalPanel();
//		statusPanel.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
//		statusPanel.setHeight("3em");
//		updatingLabel = new Label("Updating...");
//		updatingLabel.setVisible(false);
//		failureLabel = new Label("Lost connection to server.");
//		failureLabel.setVisible(false);
//		statusPanel.add(updatingLabel);
//		statusPanel.add(failureLabel);
//		mainPanel.add(statusPanel);
		
		
		
		//TODO This code is redundant, as it may be possible to implement one handler for all of the buttons 
		// since they all have essentially the same goal, simply with a different panel shown upon completion. 
		class studListHandler implements ClickHandler {
			//fired when the user clicks on the studListButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(true);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
				addStudentPanel.setVisible(false);
				removeStudentPanel.setVisible(false);
				addAssessmentTemplatePanel.setVisible(false);
				removeAssessmentTemplatePanel.setVisible(false);
			}
		}
		studListHandler goBack = new studListHandler();
		studListButton.addClickHandler(goBack);
		
		class addStudentHandler implements ClickHandler {
			//fired when the user clicks on the addStudentButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(false);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
				addStudentPanel.setVisible(true);
				removeStudentPanel.setVisible(false);
				addAssessmentTemplatePanel.setVisible(false);
				removeAssessmentTemplatePanel.setVisible(false);
			}
		}
		addStudentHandler addStud = new addStudentHandler();
		addStudentButton.addClickHandler(addStud);
		

		class removeStudentHandler implements ClickHandler {
			//fired when the user clicks on the removeStudentButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(false);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
				addStudentPanel.setVisible(false);
				removeStudentPanel.setVisible(true);
				addAssessmentTemplatePanel.setVisible(false);
				removeAssessmentTemplatePanel.setVisible(false);
			}
		}
		removeStudentHandler removeStudent = new removeStudentHandler();
		removeStudentButton.addClickHandler(removeStudent);
		
		class addAssessmentTemplateHandler implements ClickHandler {
			//fired when the user clicks on the addStudentButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(false);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
				addStudentPanel.setVisible(false);
				removeStudentPanel.setVisible(false);
				addAssessmentTemplatePanel.setVisible(true);
				removeAssessmentTemplatePanel.setVisible(false);
			}
		}
		addAssessmentTemplateHandler addTemp = new addAssessmentTemplateHandler();
		addAssessmentTemplateButton.addClickHandler(addTemp);
		
		class removeAssessmentTemplateHandler implements ClickHandler {
			//fired when the user clicks on the removeStudentButton.
			public void onClick(ClickEvent event){
				mainPanel.setVisible(false);
				adminPanel.setVisible(true);
				assessmentPanel.setVisible(false);
				addStudentPanel.setVisible(false);
				removeStudentPanel.setVisible(false);
				addAssessmentTemplatePanel.setVisible(false);
				removeAssessmentTemplatePanel.setVisible(true);
			}
		}
		removeAssessmentTemplateHandler removeTemplate = new removeAssessmentTemplateHandler();
		removeAssessmentTemplateButton.addClickHandler(removeTemplate);
		
		// Add the mainPanel, adminPanel, assessmentPanel, addStudentPanel, and removeStudentPanel to the RootPanel
		// Use RootPanel.get() to get the entire body element
		RootPanel rootPanel = RootPanel.get("applicationContainer");
		rootPanel.add(adminPanel);
		rootPanel.add(mainPanel);
		rootPanel.add(assessmentPanel);
		rootPanel.add(addStudentPanel);
		rootPanel.add(removeStudentPanel);
		rootPanel.add(addAssessmentTemplatePanel);
		rootPanel.add(removeAssessmentTemplatePanel);
		mainPanel.setSize("775px", "151px");
		assessmentPanel.setSize("775px", "18px");
		addStudentPanel.setSize("775px", "18px");
		removeStudentPanel.setSize("775px", "18px");
		addAssessmentTemplatePanel.setSize("775px", "18px");
		removeAssessmentTemplatePanel.setSize("775px", "18px");

		// Set initial visibility of panels when first entering application
		mainPanel.setVisible(true);
		assessmentPanel.setVisible(false);
		adminPanel.setVisible(true);
		addStudentPanel.setVisible(false);
		removeStudentPanel.setVisible(false);
		addAssessmentTemplatePanel.setVisible(false);
		removeAssessmentTemplatePanel.setVisible(false);
		
		//Add rest of panel structure
		mainPanel.add(new Label("Search Students"));
		studentListPanel = new StackPanel();
		mainPanel.add(studentListPanel);
		studentListPanel.setWidth("775px");
		
		//This loop should be placed below the Persistence access code below once the datastore is successfully implemented. 
		//At this stage we felt a more succinct organization would be to place it here. 
		for(Student s : studentList){
			StudentPanel newStudPanel = new StudentPanel(s, mainPanel, assessmentPanel);
			studentListPanel.add(newStudPanel, s.getName());
			
		
		// Code for implementing the Persistence datastore. For the purpose of turning in an 
		// aesthetically pleasing Interface, all information will be local
		
		//Perhaps unnecessary and primitive code. We can leave it if you like. 
//	   	studentStore.storeStudent(harry, 
//			new AsyncCallback<Void>(){
//				@Override
//				public void onFailure(Throwable caught) {
//					caught.printStackTrace();
//					failureLabel.setVisible(true);
//				}
//	
//				@Override
//				public void onSuccess(Void result) {
//					failureLabel.setVisible(false);
//					updateStudentList();
//				}
//			});
//		
//		studentStore.storeStudent(mary, 
//				new AsyncCallback<Void>(){
//					@Override
//					public void onFailure(Throwable caught) {
//						failureLabel.setVisible(true);
//					}
//		
//					@Override
//					public void onSuccess(Void result) {
//						failureLabel.setVisible(false);
//						updateStudentList();
//					}
//				});


		
		

			}
		}
	
	

	
	// This method is called each time a new student is added to the StudentList. 
		
//	private void updateStudentList() {
//		if(updatingLabel.isVisible()){
//			return;
//		}
//		updatingLabel.setVisible(true);
//		failureLabel.setVisible(false);
//		studentStore.getStudents(nextID,
//				new AsyncCallback<List<Student>>(){
//
//					@Override
//					public void onFailure(Throwable caught) {
//					updatingLabel.setVisible(false);
//					failureLabel.setVisible(true);
//					}
//		
//					@Override
//					public void onSuccess(List<Student> studentList) {
//						updatingLabel.setVisible(false);
//						for(Student s : studentList){
//							StudentPanel newStudPanel = new StudentPanel(s, mainPanel, assessmentPanel);
//							studentListPanel.add(newStudPanel, s.getName());
//							}
//						if(!studentList.isEmpty()){
//							nextID = studentList.get(0).getID() + 1;
//						}
//					}
//
//		});
//	}
}
