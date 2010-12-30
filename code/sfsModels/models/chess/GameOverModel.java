package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class GameOverModel  implements SerializableSFSType
{
	int winner;
	
	public GameOverModel()
	{
	}
	
	public GameOverModel( int winner )
	{
		this.winner = winner;
	}
	
	public int getWinner()
	{
		return this.winner;
	}
	
	public void setWinner( int winner )
	{
		this.winner = winner;
	}

}
