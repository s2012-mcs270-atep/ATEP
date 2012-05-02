/**
 * 
 */
package edu.gac.ATEP.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * @author tjaede
 *
 */
public interface AssessmentTempStoreAsync {

	/**
	 * 
	 * @see edu.gac.ATEP.shared.AssessmentTempStore#getAssessmentTemplates(java.lang.Long)
	 */
	void getAssessmentTemplates(Long minimumID,
			AsyncCallback<List<AssessmentTemplate>> callback);

	/**
	 * 
	 * @see edu.gac.ATEP.shared.AssessmentTempStore#storeAssessmentTemplates(edu.gac.ATEP.shared.AssessmentTemplate)
	 */
	void storeAssessmentTemplate(AssessmentTemplate aT,
			AsyncCallback<Void> callback);

}
