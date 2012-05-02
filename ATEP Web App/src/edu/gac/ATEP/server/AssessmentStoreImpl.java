package edu.gac.ATEP.server;

import java.util.ArrayList;
import java.util.List;



import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import edu.gac.ATEP.shared.Assessment;
import edu.gac.ATEP.shared.AssessmentStore;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;


/**
 * The server side implementation of the RPC service.
 * @author max
 */

public class AssessmentStoreImpl extends RemoteServiceServlet implements 
	AssessmentStore {

		private static final long serialVersionUID = 7367373321119740703L;

		private static final PersistenceManagerFactory pmf =
				JDOHelper.getPersistenceManagerFactory("transactions-optional");

		@Override
		public void storeAssessment(Assessment a) {
			PersistenceManager pm = pmf.getPersistenceManager();
			pm.makePersistent(a);
		}

		@Override
		public List<Assessment> getAssessments(Long minimumID) {
			PersistenceManager pm = pmf.getPersistenceManager();
			Query query = pm.newQuery(Assessment.class);
			query.declareParameters("Long minimumID");
			query.setFilter("id >= minimumID");
			query.setOrdering("id descending");
			if(minimumID == 1){
				query.setRange(0, AssessmentStore.INITIAL_LIMIT);
			}
			@SuppressWarnings("unchecked")
			List<Assessment> assessments = (List<Assessment>) query.execute(minimumID);
			return new ArrayList<Assessment>(assessments);
		}
	

}
