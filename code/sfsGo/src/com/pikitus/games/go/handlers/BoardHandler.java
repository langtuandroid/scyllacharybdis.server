package com.pikitus.games.go.handlers;

import com.pikitus.games.go.SFSGo;
import com.pikitus.games.go.models.BoardModel;
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
		BoardModel board = gameExt.getGameBoard().getBoard();
		ISFSObject boardObject = SFSObject.newInstance();
		boardObject.putClass("board", board);
		gameExt.sendSFSObject("GET_BOARD_RESULTS", boardObject, user);
	}
}
