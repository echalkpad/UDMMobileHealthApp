package com.udm.healthmonitor.internal;

public enum State {

	MI("Michigan", "MI");
	
	private State(String name, String code){
		this.code = code;
		this.name = name;
	}
	
	private String code;
	private String name;
	
	public String getStateCode(){
		return code;
	}
	
	public State getState(String name){
		return State.valueOf(name);
	}
	
	public String getName(){
		return name;
	}
	
	public String getStateCode(State state){
		return getStateCode();
	}
	
}
