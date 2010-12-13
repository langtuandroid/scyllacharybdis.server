package com.pikitus.games.chess.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.pikitus.games.chess.SFSChess;
import com.pikitus.games.chess.models.MoveModel;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ValidMoveHandler extends BaseClientRequestHandler 
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		sendPossibleMoves( gameExt, user );
	}
	
	public void sendPossibleMoves(SFSChess sfsChess, User user) 
	{
		HashMap<Long, Long> moves = sfsChess.getGameBoard().getLegalMovesMap();

		ISFSObject movesArray = new SFSObject();
		Iterator<Long> iterator = moves.keySet().iterator();
		while( iterator.hasNext() ) 
		{
			Long key   = iterator.next();
			Long value = moves.get(key);
			movesArray.putLong(key, value);
			
		}
		sfsChess.sendSFSObject( "GET_VALID_MOVES_RESULTS", movesArray, user );
	}
}
