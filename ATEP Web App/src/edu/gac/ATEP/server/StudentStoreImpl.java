package edu.gac.ATEP.server;

import java.util.ArrayList;
import java.util.List;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Query;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.ATEP.shared.StudentStore;
import edu.gac.ATEP.shared.Student;

public class StudentStoreImpl extends RemoteServiceServlet implements
 StudentStore {
	
	//will this serial number need to be different to allow two separate stores?? 
	private static final long serialVersionUID = 7367373321119740703L;

	private static final PersistenceManagerFactory pmf =
			JDOHelper.getPersistenceManagerFactory("transactions-optional");

	@Override
	public void storeStudent(Student s) {
		PersistenceManager pm = pmf.getPersistenceManager();
		pm.makePersistent(s);
	}

	@Override
	public List<Student> getStudents(Long minimumID) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Query query = pm.newQuery(Student.class);
		query.declareParameters("Long minimumID");
		query.setFilter("ID >= minimumID");
		query.setOrdering("ID descending");
		@SuppressWarnings("unchecked")
		List<Student> students = (List<Student>) query.execute(minimumID);
		return new ArrayList<Student>(students);
	}

}
