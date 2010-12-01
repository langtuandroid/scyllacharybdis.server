package com.pikitus.games.chess.handlers;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.pikitus.games.chess.SFSChess;
import com.pikitus.games.chess.ChessBoard;

public class MoveHandler extends BaseClientRequestHandler
{
	ChessBoard mBoard;
	
	public MoveHandler(ChessBoard board)
	{
		mBoard = board;
	}
	
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
		
		mBoard.movePiece(to, from); 
	}
}