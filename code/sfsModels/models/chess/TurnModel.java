package models.chess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class TurnModel  implements SerializableSFSType
{
	User turn;
	
	public TurnModel()
	{
	}
	
	public TurnModel( User turn )
	{
		this.turn = turn;
	}
	
	public User getTurn()
	{
		return this.turn;
	}
	
	public void setTurn( User turn )
	{
		this.turn = turn;
	}

}