package com.pikitus.games.chess.handlers;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

import com.pikitus.games.chess.SFSChess;

public class PlayerJoinedHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		trace("!!!!PLAYER JOINED HANDLER RUNNING!!!!");
		
		SFSChess gameExt = (SFSChess) getParentExtension();
		
		if (user.isPlayer())
		{
			gameExt.playerJoined(user);
		}
	}
}