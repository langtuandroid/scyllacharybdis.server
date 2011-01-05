package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	int[][] board;
	
	public BoardModel() 
	{
	}

	public BoardModel( int[][] board )
	{
		this.board = board;
	}
	
	public int[][] getBoard() { return this.board; }
	
	public void setBoard(int [][] board) 
	{
		this.board = board;
	}
}

