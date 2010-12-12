package com.pikitus.games.chess.handlers;

import java.util.HashMap;
import java.util.Iterator;
import com.pikitus.games.chess.SFSChess;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
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
		HashMap<String, Long> board = sfsChess.getGameBoard().getPieces();

		ISFSObject boardArray = new SFSObject();
			
		Iterator<String> iterator = board.keySet().iterator();
		while( iterator.hasNext() ) 
		{
			String key   = iterator.next();
			Long value = board.get(key);
			boardArray.putLong(key, value);
		}
		
		ISFSObject obj = new SFSObject();
		obj.putSFSObject("boardArray", boardArray);
		sfsChess.sendSFSObject("GET_BOARD_RESULTS", obj, user);
	}
}