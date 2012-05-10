package edu.gac.ATEP.client;

import com.google.gwt.user.client.ui.Button;

import edu.gac.ATEP.shared.Assessment;

public class AssessmentButton extends Button {

	private Assessment assessmentToView;
	
	public AssessmentButton(String buttonText, Assessment assessmentToView){
		super(buttonText);
		this.assessmentToView = assessmentToView;
		
	}
	public Assessment getAssessmentToView() {
		return this.assessmentToView;
	}
}
