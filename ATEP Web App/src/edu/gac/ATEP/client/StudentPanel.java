package edu.gac.ATEP.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Label;
import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.Student;

public class StudentPanel extends VerticalPanel{
	
	private static Student stud;
	
	public StudentPanel(Student stud){
		super();
		this.stud = stud;
		initGUI();
		
	}

///////////////////////////////Create a handler for the ViewAssessmentButton\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
	class ViewAssessmentHandler implements ClickHandler{
		
		//Fired when the user clicks on the ViewAssessmentButton.
		public void onClick(ClickEvent event){
			/*mainPanel.setVisible(false);
			assessmentPanel.setVisible(true);*/
			VerticalPanel populatedPanel = new VerticalPanel();
		}

		//Send the name selected from the student list to the server and wait for a response.
		private void sendNameToServer() {
			// TODO we may need this method later when a student name 
			//      is selected in order to retrieve their info
		}
	}
	
	private void initGUI() {
		this.add(new Label("Year in program: " + stud.getClassYear()));
		this.add(new Button("Delete this student"));
		this.add(new Label("Current Assessments: "));

		for (Assessment a : stud.getMyAssessments()){
			HorizontalPanel assessmentViewPanel = new HorizontalPanel();
			assessmentViewPanel.add(new Label(a.getName() + " -- Status: " + a.getStatus()));
			Button viewButton = new Button("View " + a.getName());
			assessmentViewPanel.add(viewButton);
			ViewAssessmentHandler viewAssessment = new ViewAssessmentHandler();
			viewButton.addClickHandler(viewAssessment);
			/*studentInfoPanel.add(assessmentInfoPanel);
			studentInfoPanel.add(assessmentViewPanel);
			j++;*/
		}
	
		
	}
}
