package com.pikitus.games.chess.handlers;

import com.pikitus.games.chess.SFSChess;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class BoardHandler extends BaseClientRequestHandler 
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		sendValidMoves( gameExt, user );
	}
	
	public void sendValidMoves(SFSChess sfsChess, User user) 
	{
		sfsChess.sendSFSObject("CHESS_BOARD", sfsChess.getGameBoard().getBoardArray(), user);
	}
}
