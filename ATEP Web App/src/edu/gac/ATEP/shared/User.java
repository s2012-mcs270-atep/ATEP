package edu.gac.ATEP.shared;

import java.io.Serializable;

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
