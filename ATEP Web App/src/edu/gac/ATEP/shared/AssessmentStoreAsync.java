/**
 * 
 */
package edu.gac.ATEP.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author helen
 *
 */
public interface AssessmentStoreAsync {

	/**
	 * 
	 * @see edu.gac.ATEP.shared.AssessmentStore#getAssessments(java.lang.Long)
	 */
	void getAssessments(Long minimumID, AsyncCallback<List<Assessment>> callback);

	/**
	 * 
	 * @see edu.gac.ATEP.shared.AssessmentStore#storeAssessment(edu.gac.ATEP.shared.Assessment)
	 */
	void storeAssessment(Assessment assessment, AsyncCallback<Void> callback);

}
