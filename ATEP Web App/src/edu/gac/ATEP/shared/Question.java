package edu.gac.ATEP.shared;

import java.io.Serializable;

import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
public class Question implements Serializable {
	@SuppressWarnings("unused")
	@PrimaryKey
	@Persistent (valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName="datanucleus", key="gae.encoded-pk", value="true")
	private String key;
	@Persistent
	private String bodyText;
	@Persistent
	private int score; // not used for template
// each assessment has multiple Questions
	
	public Question(String bodyText) {
		this.bodyText = bodyText;
		// score not initialized to prevent false results
	}

	public String getBodyText() {
		return bodyText;
	}

	public void setBodyText(String bodyText) {
		this.bodyText = bodyText;
	}

	public void setScore(int score2) {
		// TODO Auto-generated method stub
		// interface must ensure that score is between 0/1 and 5
		score = score2;
	}
	
	@SuppressWarnings("unused")
	private Question(){}

}
