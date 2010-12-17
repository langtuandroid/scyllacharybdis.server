package com.pikitus.games.go.handlers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import com.pikitus.games.go.SFSGo;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class BoardHandler extends BaseClientRequestHandler 
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSGo gameExt = (SFSGo) getParentExtension();
		sendValidMoves( gameExt, user );
	}
	
	public void sendValidMoves(SFSGo sfsChess, User user) 
	{
//		HashMap<String, Long> board = sfsChess.getGameBoard().getPieces();
//
//		ISFSObject boardArray = new SFSObject();
//			
//		Iterator<String> iterator = board.keySet().iterator();
//		while( iterator.hasNext() ) 
//		{
//			String key   = iterator.next();
//			Long value = board.get(key);
//			
//			ArrayList<Long> valueArray = new ArrayList<Long>();
//			valueArray.add( value & -1 );
//			valueArray.add( ( value >> 32 ) & -1 );
//			
//			boardArray.putLongArray(key, valueArray);
//		}
//		
//		ISFSObject obj = new SFSObject();
//		obj.putSFSObject("boardArray", boardArray);
//		sfsChess.sendSFSObject("GET_BOARD_RESULTS", obj, user);
	}
}
