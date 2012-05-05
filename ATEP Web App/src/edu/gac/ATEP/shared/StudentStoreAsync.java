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
public interface StudentStoreAsync {

	void getStudents(Long minimumID, AsyncCallback<List<Student>> callback);

	/**
	 * 
	 * @see edu.gac.ATEP.shared.StudentStore#storeStudent(edu.gac.ATEP.shared.Student)
	 */
	void storeStudent(Student s, AsyncCallback<Void> callback);

}
