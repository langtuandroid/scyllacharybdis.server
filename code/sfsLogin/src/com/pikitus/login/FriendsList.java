package com.pikitus.login;

import java.util.Iterator;
import java.util.List;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class FriendsList extends BaseClientRequestHandler
{
	private FBFriends fbFriends = new FBFriends();

	public FriendsList()
	{
	}
	
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSLogin gameExt = (SFSLogin) getParentExtension();
		gameExt.sendSFSObject( "friendsListResults", getFriendsList(), user );
	}
	
	public ISFSObject getFriendsList()
	{
		ISFSArray friendsArray = new SFSArray();

		List<FriendListResult> users = fbFriends.getFriendsList();
		Iterator<FriendListResult> itr = users.iterator();
		while( itr.hasNext() ) 
		{
			// Get the data
			FriendListResult element = itr.next();
			System.out.print( element.toString() );

			// Create an object with the data
			ISFSObject friend = new SFSObject();
			friend.putUtfString("uid", (String) element.uid );
			friend.putUtfString("name", (String) element.name );
			friend.putUtfString("picture", (String) element.picture );
			
			friendsArray.addSFSObject(friend);
		}

		ISFSObject obj = new SFSObject();
		obj.putSFSArray("friendsArray", friendsArray);
		return obj;
	}
}