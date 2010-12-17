package com.pikitus.games.go.handlers;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.pikitus.games.go.SFSGo;
import com.pikitus.games.go.models.MoveModel;
//import com.pikitus.games.go.models.MoveModel;

public class MoveHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSGo gameExt = (SFSGo) getParentExtension();
		
		if ( !user.isPlayer() ) 
		{
			return ;
		}
	
		if ( gameExt.getWhoseTurn() != user ) {
			return ;
		}

		if ( ! gameExt.isStarted() ) 
		{
			
		}

		MoveModel move = (MoveModel) params.getClass("move");
		movePiece(gameExt, user, move); 
	}
	
	/**
	 * Move a piece and send the results
	 */
	public void movePiece( SFSGo sfsGo, User user, MoveModel move ) 
	{
		
		move = sfsGo.getGameBoard().movePiece( move );
		ISFSObject resObj = SFSObject.newInstance();
		resObj.putClass("move", move);
		if ( move.isValid() )
		{
			sfsGo.sendSFSObject("MOVE_PIECE_RESULTS", resObj);
		}
		else 
		{
			sfsGo.sendSFSObject("MOVE_PIECE_RESULTS", resObj, user);
		}
	}	
}