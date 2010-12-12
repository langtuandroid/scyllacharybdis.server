package com.pikitus.games.chess.handlers;

import java.util.ArrayList;
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
		ArrayList<MoveModel> moves = sfsChess.getGameBoard().getLegalMovesArray();
		ISFSObject movesArray = new SFSObject();
		Iterator<MoveModel> iterator = moves.iterator();
		while( iterator.hasNext() ) 
		{
			MoveModel model = iterator.next();
			if ( ! movesArray.containsKey(model.from) )
			{
				movesArray.putSFSArray(model.from, new SFSArray() );
			}
			movesArray.getSFSArray(model.from).addUtfString(model.to);
			
		}
		sfsChess.sendSFSObject( "GET_VALID_MOVES_RESULTS", movesArray, user );
	}
}