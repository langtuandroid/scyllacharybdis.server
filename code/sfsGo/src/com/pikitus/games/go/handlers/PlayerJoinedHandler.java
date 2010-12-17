package com.pikitus.games.go.handlers;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import com.pikitus.games.go.SFSGo;

public class PlayerJoinedHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSGo gameExt = (SFSGo) getParentExtension();
		
		if (user.isPlayer())
		{

			// Checks if two players are available and start game
			if (gameExt.getParentRoom().getSize().getUserCount() == 1){
				gameExt.startGame();
			}
		}
	}
}