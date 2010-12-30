package models.go;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	private int[][] mBoard;
	
	public int[][] getBoard()
	{
		return mBoard;
	}
	
	public void setBoard( int[][] board )
	{
		mBoard = board;
	}
}
