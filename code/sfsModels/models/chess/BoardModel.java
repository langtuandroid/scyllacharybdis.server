package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	private int[] board;
	
	BoardModel() 
	{
	}

	BoardModel( int[] board )
	{
		this.board = board;
	}
	
	public int[] getBoard() { return this.board; }
	
	public void setBoard(int [] board) 
	{
		this.board = board;
	}
}

