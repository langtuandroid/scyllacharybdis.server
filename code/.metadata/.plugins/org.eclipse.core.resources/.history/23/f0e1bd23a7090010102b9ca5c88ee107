package com.pikitus.games.go;

import com.pikitus.games.go.handlers.*;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class SFSGo extends SFSExtension
{
	private final String version = "0.2";
	private GoBoard mGameBoard;
	private volatile boolean mGameStarted = false;
	private User mWhoseTurn;
	private User mPlayer1;
	private User mPlayer2;

	@Override
	public void init()
	{
		// Trace the version
		trace("SFSChess Extension started, rel. " + version);
	
		// Create a new chess board
		mGameBoard = new GoBoard();
	
		addRequestHandler("PLAYER_JOINED", PlayerJoinedHandler.class);
		addRequestHandler("GET_BOARD", BoardHandler.class);
		addRequestHandler("GET_VALID_MOVES", ValidMoveHandler.class);
		addRequestHandler("MOVE_PIECE", MoveHandler.class);
	}

	/**
	* Send a sfs object to all the clients in the room
	* @param name (String) Event name the client receives
	* @param object (ISFSObject) The object being sent to the client
	*/
	public void sendSFSObject(String name, ISFSObject object)
	{
		send( name, object, getParentRoom().getUserList() );
	}

	/**
	* Send a sfsobject to a user in the room
	* @param name (String) Event name the client receives
	* @param object (ISFSObject) The object being sent to the client
	* @param user (User) The user to send the object too
	*/
	public void sendSFSObject(String name, ISFSObject object, User user)
	{
		send( name, object, user );
	}
}
