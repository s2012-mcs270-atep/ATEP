package edu.gac.ATEP.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.RadioButton;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.Student;
import edu.gac.ATEP.shared.Category;
import edu.gac.ATEP.shared.Question;

public class StudentPanel extends VerticalPanel{

	private static Student stud;
	private static VerticalPanel mainPanel;
	private static VerticalPanel singleAssessmentPanel;
	private VerticalPanel populatedPanel = new VerticalPanel();
	ArrayList<Assessment> assessments;

	//constructor
	public StudentPanel(Student stud, VerticalPanel mainPanel, VerticalPanel singleAssessmentPanel){
		super();
		this.stud = stud;
		this.mainPanel = mainPanel;
		this.singleAssessmentPanel = singleAssessmentPanel;
		assessments = stud.getMyAssessments();
		initGUI();
	}

////////////////////////////////Create a handler for the rtslButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	//rtsl = Return To Student List
	class rtslHandler implements ClickHandler {
		//fired when the user clicks on the rtslButton.
		public void onClick(ClickEvent event){
			mainPanel.setVisible(true);
			singleAssessmentPanel.setVisible(false);
			singleAssessmentPanel.remove(populatedPanel);
		}
	}

///////////////////////////////Create a handler for the ViewAssessmentButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	class ViewAssessmentHandler implements ClickHandler{
		private Assessment assessmentToView;

		public ViewAssessmentHandler(Assessment a) {
			assessmentToView = a;
		}

		//Fired when the user clicks on the ViewAssessmentButton.
		public void onClick(ClickEvent event){
			mainPanel.setVisible(false);	
			singleAssessmentPanel.clear();	//specifically removes redundant "Return to Student List" buttons.
			populatedPanel.clear();	//specifically removes the categories and questions.
			final Button rtslButton = new Button("Return to Student List");
			singleAssessmentPanel.add(rtslButton);
			rtslHandler goBack = new rtslHandler();
			rtslButton.addClickHandler(goBack);
			populatePanel(assessmentToView);
			singleAssessmentPanel.setVisible(true);
		}


		//Method for populating the assessmentPanel (panel2) with categories and questions
		private void populatePanel(Assessment assessmentToPopulate) {
			ArrayList<Category> cats = assessmentToPopulate.getCategories();
			//Goes through list of categories and makes a new Panel & Label for each
			for (Category cat : cats){
				VerticalPanel catPanel = new VerticalPanel();
				ArrayList<Question> Qs = cat.getQuestions();
				Label catLabel = new Label(cat.getName());
				catLabel.addStyleName("label");
				catPanel.add(catLabel);
				//Goes through list of questions in a specific category, creates a panel for the 
				//question and the score buttons and adds to category panel (catPanel)
				for (Question q : Qs){
					VerticalPanel questionPanel = new VerticalPanel();
					HorizontalPanel scorePanel = new HorizontalPanel();
					Label questionLabel = new Label(q.getBodyText());
					RadioButton zero = new RadioButton(q.getBodyText(), "0");
					RadioButton one = new RadioButton(q.getBodyText(), "1");
					RadioButton two = new RadioButton(q.getBodyText(), "2");
					RadioButton three = new RadioButton(q.getBodyText(), "3");
					RadioButton four = new RadioButton(q.getBodyText(), "4");
					RadioButton five = new RadioButton(q.getBodyText(), "5");
					scorePanel.add(zero);
					scorePanel.add(one);
					scorePanel.add(two);
					scorePanel.add(three);
					scorePanel.add(four);
					scorePanel.add(five);
					questionPanel.add(questionLabel);
					questionPanel.add(scorePanel);
					catPanel.add(questionPanel);
				}
				//Adds category panel to the panel we want to populate the assessment with
				populatedPanel.add(catPanel);
			}
			//Adds newly created panel to assessment panel.
			singleAssessmentPanel.add(populatedPanel);

		}
	}

	//Initialize method to display info in each student panel
	private void initGUI() {
		this.add(new Label("Year in program: " + stud.getClassYear()));
		this.add(new Label("Current Assessments: "));
		//Loop to add button and click handler to navigate to each assessment
		for (Assessment a : assessments){
			HorizontalPanel assessmentViewPanel = new HorizontalPanel();
			assessmentViewPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
			AssessmentButton viewButton = new AssessmentButton("View " + a.getName(), a);
			assessmentViewPanel.add(viewButton);
			ViewAssessmentHandler viewAssessment = new ViewAssessmentHandler(a);
			viewButton.addClickHandler(viewAssessment);
			this.add(assessmentViewPanel);

		}	
	}
}