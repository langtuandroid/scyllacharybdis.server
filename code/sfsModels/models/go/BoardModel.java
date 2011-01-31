package models.go;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	private Integer[][] mBoard;
	
	public Integer[][] getBoard()
	{
		return mBoard;
	}
	
	public void setBoard( Integer[][] board )
	{
		mBoard = board;
	}
}
