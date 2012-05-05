package edu.gac.ATEP.shared;

import java.util.List;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;


@RemoteServiceRelativePath("StudentStore")
public interface StudentStore extends RemoteService {
	/**
	 * Store a Message.
	 * @param msg the Message to store
	 */
	public void storeStudent(Student s);

	/**
	 * Retrieve Messages.
	 * If minimumID==1, no Messages are excluded on that basis but
	 * the List is limited to the newest INITIAL_LIMIT Messages.
	 * @param minimumID the lowest ID (oldest Message) to include
	 * @return a List from newest (greatest id) to oldest (least id)
	 */
	List<Student> getStudents(Long minimumID);

	/**
	 * Limit on how far back into history initial retrievals go.
	 */
	public static final int INITIAL_LIMIT=10;
}


