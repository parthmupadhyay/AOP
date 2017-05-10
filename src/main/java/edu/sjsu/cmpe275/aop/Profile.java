package edu.sjsu.cmpe275.aop;

public class Profile 
{
	private String userId;
	private String name;
	
	public Profile(String id)
	{
		userId=id;
		name=id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String toString()
	{
		return userId;
	}
}
