import greenfoot.*;  // (World, Actor, GreenfootImage, Greenfoot and MouseInfo)

/**
 * Write a description of class State here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */ 


public enum State {
	NOT_SELECTED("Not Selected"), SELECTED("Selected"), SUGGESTED("Suggested");

	String value;

	
    
	private State(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return value;
	}
}


