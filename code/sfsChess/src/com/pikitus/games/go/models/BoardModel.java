package com.pikitus.games.go.models;

import java.lang.reflect.Array;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	private Array mBoard;
	
	BoardModel( Array board )
	{
		mBoard = board;
	}
	
	public Array GetBoard() { return mBoard; }
}

