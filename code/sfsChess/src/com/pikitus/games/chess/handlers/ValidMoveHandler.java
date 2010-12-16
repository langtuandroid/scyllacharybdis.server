package com.pikitus.games.chess.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.pikitus.games.chess.SFSChess;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
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
		HashMap<String, Long> moves = sfsChess.getGameBoard().getLegalMovesMap();

		ISFSObject movesArray = new SFSObject();
		Iterator<String> iterator = moves.keySet().iterator();
		while( iterator.hasNext() ) 
		{
			String key   = iterator.next();
			long value = moves.get(key);
			
			ArrayList<Long> valueArray = new ArrayList<Long>();
			valueArray.add( value & -1 );
			valueArray.add( ( value >> 32 ) & -1 );
			movesArray.putLongArray( key, valueArray );
		}
		
		sfsChess.sendSFSObject( "GET_VALID_MOVES_RESULTS", movesArray, user );
	}
}
