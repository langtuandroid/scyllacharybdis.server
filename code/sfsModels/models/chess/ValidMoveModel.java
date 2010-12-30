package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class ValidMoveModel implements SerializableSFSType
{
	int[] validMoves;
	
	public ValidMoveModel() 
	{
	}

	public ValidMoveModel( int[] validMoves )
	{
		this.validMoves = validMoves;
	}
	
	public int[] getValidMoves() { return this.validMoves; }
	
	public void setValidMoves( int[] validMoves ) 
	{
		this.validMoves = validMoves;
	}
}

