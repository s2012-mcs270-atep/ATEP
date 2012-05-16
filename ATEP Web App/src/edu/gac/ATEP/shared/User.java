package edu.gac.ATEP.shared;

import java.io.Serializable;
import java.util.ArrayList;

import javax.jdo.annotations.Discriminator;
import javax.jdo.annotations.DiscriminatorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import edu.gac.ATEP.shared.Student;

@PersistenceCapable(identityType=IdentityType.APPLICATION)
@Discriminator(strategy=DiscriminatorStrategy.CLASS_NAME)
public class User implements Serializable {
	@PrimaryKey
	@Persistent
	private String name; 
	public ArrayList<User> adminList;

	public User(String n) {
		name = n;
	}

	public String getName() {
		 return name;
	}

	public String toString(){
		return name + ""; 
	}

	public boolean isAdmin() {
		for (User user : adminList){
			if (this.getName() == user.getName()) {return true;}
		}
		return false;
	}

	@SuppressWarnings("unused")
	public User(){}
}