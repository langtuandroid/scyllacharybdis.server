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
		SFSChess gameExt = (SFSChess) getParentExtension();
		
		if (user.isPlayer())
		{
			//gameExt.sendBoard(user);

			// Checks if two players are available and start game
			//if (gameExt.getParentRoom().getSize().getUserCount() == 2){
			if (gameExt.getParentRoom().getSize().getUserCount() == 1){
				gameExt.startGame();
			}
			
		}
	}
}