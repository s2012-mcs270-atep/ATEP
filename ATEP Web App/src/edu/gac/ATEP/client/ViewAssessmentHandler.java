package edu.gac.ATEP.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.VerticalPanel;

public class ViewAssessmentHandler implements ClickHandler{

		//Fired when the user clicks on the ViewAssessmentButton.
		public void onClick(ClickEvent event){
			mainPanel.setVisible(false);
			assessmentPanel.setVisible(true);
			//VerticalPanel populatedPanel = new VerticalPanel();
		}

		//Send the name selected from the student list to the server and wait for a response.
		private void sendNameToServer() {
			// TODO we may need this method later when a student name 
			//      is selected in order to retrieve their info
		}
}
