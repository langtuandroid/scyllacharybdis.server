package com.pikitus.games.chess.handlers;

import com.pikitus.games.chess.SFSChess;
import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

public class OnUserGoneHandler extends BaseServerEventHandler 
{
    @Override
	public void handleServerEvent(ISFSEvent event) throws SFSException
	{
    	SFSChess gameExt = (SFSChess) getParentExtension();

    	User user = (User) event.getParameter(SFSEventParam.USER);
    	
    	// Did they disconnect or leave
		if (event.getType() == SFSEventType.USER_DISCONNECT)
		{
			gameExt.playerLeft(user, true);
		}
		// User just left the room
		else
		{
			gameExt.playerLeft(user, false);
		}
	}
}