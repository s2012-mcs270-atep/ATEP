package edu.gac.ATEP.shared;

import java.io.Serializable;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class User implements Serializable {
	@PrimaryKey
	@Persistent
	private String name; //Username or name?
	
	public User(String n) {
		name = n;
	}
	
	public String getName() {
		 return name;
	}
	
	public String toString(){
		return name + ""; // fill in later with other parameters?
	}
	
	@SuppressWarnings("unused")
	public User(){}
}
