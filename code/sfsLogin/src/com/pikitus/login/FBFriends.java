package com.pikitus.login;

import java.util.List;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.FacebookException;


public class FBFriends {
	
	private String MY_ACCESS_TOKEN = "";
	FacebookClient facebookClient = new DefaultFacebookClient(MY_ACCESS_TOKEN);

	public FBFriends()
	{
	}
	
	public List<FriendListResult> getFriendsList() 
	{
		String query = "SELECT uid, name, picture FROM user WHERE uid IN (SELECT uid2 FROM friend WHERE uid1 = me()) AND is_app_user = 1";
		List<FriendListResult> users = null;
		try {
			users = facebookClient.executeQuery(query, FriendListResult.class);
		} catch (FacebookException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return users;
	}
}



