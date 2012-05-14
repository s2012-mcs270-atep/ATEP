package edu.gac.ATEP.client;

import java.util.ArrayList;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.CheckBox;
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
	private static VerticalPanel panel1;
	private static VerticalPanel panel2;
	private VerticalPanel populatedPanel = new VerticalPanel();
	ArrayList<Assessment> assessments;
	ArrayList<RadioButton> buttons; 
	
	public StudentPanel(Student stud, VerticalPanel panel1, VerticalPanel panel2){
		super();
		this.stud = stud;
		this.panel1 = panel1;
		this.panel2 = panel2;
		assessments = stud.getMyAssessments();
		initGUI();
		
	}

////////////////////////////////Create a handler for the rtslButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	class rtslHandler implements ClickHandler {
		//fired when the user clicks on the rtslButton.
		public void onClick(ClickEvent event){
			panel1.setVisible(true);
			panel2.setVisible(false);
			panel2.remove(populatedPanel);
		}
	}
	class saveHandler implements ClickHandler {
		private Assessment assessmentToSave;
		//fired when the user clicks on the rtslButton.
		public saveHandler(Assessment a) { 
			assessmentToSave = a; 
		}
		public void onClick(ClickEvent event){
			((StudentPanel) populatedPanel).saveState(assessmentToSave);
			
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
			panel1.setVisible(false);	
			panel2.clear();	//specifically removes redundant "Return to Student List" buttons.
			populatedPanel.clear();	//specifically removes the categories and questions.
			final Button rtslButton = new Button("Return to Student List");
			final Button saveButton = new Button("Save Progress");
			panel2.add(rtslButton);
			panel2.add(saveButton);
			rtslHandler goBack = new rtslHandler();
			saveHandler save  = new saveHandler(assessmentToView); 
			rtslButton.addClickHandler(goBack);
			saveButton.addClickHandler(save);
			populatePanel(assessmentToView);
			panel2.setVisible(true);
		}
		

		// Add a handler to hide mainPanel and display assessmentPanel


		//Send the name selected from the student list to the server and wait for a response.
		private void populatePanel(Assessment assessmentToPopulate) {
			//create getCategories
			//VerticalPanel viewAssessmentPanel = new VerticalPanel();
			ArrayList<Category> cats = assessmentToPopulate.getCategories();
			ArrayList<RadioButton> buttons = new ArrayList<RadioButton>();
			for (Category cat : cats){
				VerticalPanel catPanel = new VerticalPanel();
				ArrayList<Question> Qs = cat.getQuestions();
				Label catLabel = new Label(cat.getName());
				catLabel.addStyleName("label");
				catPanel.add(catLabel);
				for (Question q : Qs){
					VerticalPanel questionPanel = new VerticalPanel();
					HorizontalPanel scorePanel = new HorizontalPanel();
					Label questionLabel = new Label(q.getBodyText());
					for (int i = 0; i < 6; i++){ 
						
						RadioButton b = new RadioButton(q.getBodyText(), "" + i);
						if (q.getScore() == i) { 
							b.setChecked(true); 
						}
						scorePanel.add(b);
						buttons.add(b);
						
					}
					questionPanel.add(questionLabel);
					questionPanel.add(scorePanel);
					catPanel.add(questionPanel);
				}
				populatedPanel.add(catPanel);
				//viewAssessmentPanel.add(populatedPanel);
			}
			panel2.add(populatedPanel);
			
		}
	}
	
    @SuppressWarnings("deprecation")
	private void saveState(Assessment assessmentToSave) { 
    	ArrayList<Category> cats = assessmentToSave.getCategories();
    	for (Category cat : cats){
			VerticalPanel catPanel = new VerticalPanel();
			ArrayList<Question> Qs = cat.getQuestions();
			Label catLabel = new Label(cat.getName());
			catLabel.addStyleName("label");
			catPanel.add(catLabel);
			for (Question q : Qs){
				for (RadioButton b : buttons) { 
					if (b.isChecked() && q.getBodyText() == b.getName()) { 
						q.setScore(Integer.parseInt(b.getText()));
						
					}
				} 
			} 
    	}
    
	}
	private void initGUI() {
		this.add(new Label("Year in program: " + stud.getClassYear()));
		this.add(new Button("Delete this student"));
		this.add(new Label("Current Assessments: "));
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
