package com.pikitus.login;

import com.restfb.Facebook;

public class FriendListResult 
{
	@Facebook
	String uid;

	@Facebook
	String name;
	
	@Facebook
	String picture;

	@Override
	public String toString() 
	{
		return String.format("%s - %s - %s", uid, name, picture); 	
	}
	
	public String getId()
	{
		return uid;
	}
	public String getName()
	{
		return name;
	}
	public String getPicture()
	{
		return picture;
	}
}