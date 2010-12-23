package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class TurnModel  implements SerializableSFSType
{
	int turn;
	
	public TurnModel()
	{
	}
	
	public TurnModel( int turn )
	{
		this.turn = turn;
	}
	
	public int getTurn()
	{
		return this.turn;
	}
	
	public void setTurn( int turn )
	{
		this.turn = turn;
	}

}
