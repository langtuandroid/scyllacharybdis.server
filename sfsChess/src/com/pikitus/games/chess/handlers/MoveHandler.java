package com.pikitus.games.chess.handlers;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
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
	
		if ( gameExt.getWhoseTurn() != user ) {
			return ;
		}

		if ( ! gameExt.isStarted() ) {
			
		}
		
		String to = params.getUtfString("to");
		String from = params.getUtfString("from");
		
		movePiece(gameExt, user, to, from); 
	}
	
	/**
	 * Move a piece and send the results
	 * @param to (String) Alpha Numeric ( A1 )
	 * @param from (String) Alpha Numeric ( A1 )
	 */
	public void movePiece( SFSChess sfsChess, User user, String to, String from ) 
	{
		ISFSObject resObj = new SFSObject();
		boolean valid = sfsChess.getGameBoard().movePiece( to, from );
		resObj.putBool("valid", valid );
		
		// Is it a valid move
		if ( valid ) 
		{
			sfsChess.sendSFSObject("MOVE_RESULTS", resObj);
		} 
		else 
		{
			sfsChess.sendSFSObject("MOVE_RESULTS", resObj, user);
		}
	}	
}