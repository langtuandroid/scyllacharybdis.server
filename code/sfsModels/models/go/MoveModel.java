package models.go;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class MoveModel implements SerializableSFSType
{
	private boolean mValid;
	private Integer[] mMove = new Integer[2];
	
	public MoveModel()
	{
		mValid = false;
		mMove[0] = 99;
		mMove[1] = 99;
	}
	
	public boolean isValid()
	{
		return mValid;
	}
	
	public void SetValid( boolean valid)
	{
		mValid = true;
	}
		
	public Integer[] getMove()
	{
		return mMove;
	}
		
	public void setBoard( Integer[] move )
	{
		mMove = move;
	}
}
