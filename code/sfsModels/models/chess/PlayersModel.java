package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class PlayersModel implements SerializableSFSType
{
	int player1;
	int player2;
	
	public PlayersModel() 
	{
	}

	public PlayersModel( int player1, int player2)
	{
		this.setPlayer1(player1);
		this.setPlayer2(player2);
	}

	public void setPlayer1(int player1) {
		this.player1 = player1;
	}

	public int getPlayer1() {
		return player1;
	}

	public void setPlayer2(int player2) {
		this.player2 = player2;
	}

	public int getPlayer2() {
		return player2;
	}

}

