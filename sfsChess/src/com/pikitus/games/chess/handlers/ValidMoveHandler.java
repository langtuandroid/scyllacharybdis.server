package com.pikitus.games.chess.handlers;

import com.pikitus.games.chess.SFSChess;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ValidMoveHandler extends BaseClientRequestHandler 
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		sendBoard( gameExt );
	}
	
	public void sendBoard(SFSChess sfsChess) 
	{
		sfsChess.sendSFSObject( "VALID_MOVES", sfsChess.getGameBoard().getValidMoveArray() );
	}
}
