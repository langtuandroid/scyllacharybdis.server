package com.pikitus.login;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.pikitus.login.handlers.LoginEventHandler;
import com.pikitus.login.handlers.OnUserGoneHandler;

public class SFSLogin extends SFSExtension 
{
	private final String version = "0.2";
	
	@Override
	public void init() {
	
		trace("SFSLogin Extension started, rel. " + version);
		
		// Standard events
		addEventHandler(SFSEventType.USER_LOGIN, LoginEventHandler.class); 
	    addEventHandler(SFSEventType.USER_DISCONNECT, OnUserGoneHandler.class);
	    addEventHandler(SFSEventType.USER_LEAVE_ROOM, OnUserGoneHandler.class);

	    // Get facebook friends
	    addRequestHandler("getFriends", FriendsList.class);
	}

	public void sendSFSObject(String name, ISFSObject object)
	{
		send( name, object, getParentRoom().getUserList() );
	}

	public void sendSFSObject(String name, ISFSObject object, User user) 
	{
		send( name, object, user );
	}
}	
