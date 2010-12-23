package models.chess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class GameOverModel  implements SerializableSFSType
{
	User winner;
	
	public GameOverModel()
	{
	}
	
	public GameOverModel( User winner )
	{
		this.winner = winner;
	}
	
	public User getWinner()
	{
		return this.winner;
	}
	
	public void setWinner( User winner )
	{
		this.winner = winner;
	}

}