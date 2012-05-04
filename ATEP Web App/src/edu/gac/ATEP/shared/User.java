package edu.gac.ATEP.shared;

import java.io.Serializable;

import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.IdentityType;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

public class User implements Serializable {
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
