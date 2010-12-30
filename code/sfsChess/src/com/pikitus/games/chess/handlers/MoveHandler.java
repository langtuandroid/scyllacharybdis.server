package com.pikitus.games.chess.handlers;

import models.chess.MoveModel;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.pikitus.games.chess.SFSChess;

public class MoveHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		
		if ( !user.isPlayer() ) {
			return ;
		}
	
		if ( gameExt.getWhoseTurn() != user.getId() ) {
			return ;
		}
		
		gameExt.movePiece(user, (MoveModel) params.getClass("MoveModel")); 
	}
}