package edu.gac.ATEP.shared;

public class User {
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
}
