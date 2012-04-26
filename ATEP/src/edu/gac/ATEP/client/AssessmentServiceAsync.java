package edu.gac.ATEP.client;

import com.google.gwt.user.client.rpc.AsyncCallback;

/**
 * The async counterpart of <code>GreetingService</code>.
 */
public interface AssessmentServiceAsync {
	void atepServer(String input, AsyncCallback<String> callback)
			throws IllegalArgumentException;
}
