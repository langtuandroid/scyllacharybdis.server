package models.chess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class PlayersModel implements SerializableSFSType
{
	User player1;
	User player2;
	
	public PlayersModel() 
	{
	}

	public PlayersModel( User player1, User player2)
	{
		this.setPlayer1(player1);
		this.setPlayer2(player2);
	}

	public void setPlayer1(User player1) {
		this.player1 = player1;
	}

	public User getPlayer1() {
		return player1;
	}

	public void setPlayer2(User player2) {
		this.player2 = player2;
	}

	public User getPlayer2() {
		return player2;
	}

}

