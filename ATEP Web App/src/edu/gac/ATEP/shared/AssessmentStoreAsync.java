package edu.gac.ATEP.shared;

import java.util.List;
import com.google.gwt.user.client.rpc.AsyncCallback;


/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AssessmentStoreAsync {

	/**
	 * 
	 * @see edu.gac.mcs270.messageboard.shared.MessageStore#getMessages(long)
	 */
	void getAssessments(Long minimumID, AsyncCallback<List<Assessment>> callback);

	/**
	 * 
	 * @see edu.gac.mcs270.messageboard.shared.MessageStore#storeMessage(edu.gac.mcs270.messageboard.shared.Message)
	 */
	void storeAssessment(Assessment msg, AsyncCallback<Void> callback);
}
