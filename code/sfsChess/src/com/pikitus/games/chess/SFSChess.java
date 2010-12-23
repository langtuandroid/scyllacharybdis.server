package com.pikitus.games.chess;

import models.chess.GameOverModel;
import models.chess.PlayersModel;
import models.chess.TurnModel;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.pikitus.games.chess.ChessBoard;
import com.pikitus.games.chess.handlers.MoveHandler;
import com.pikitus.games.chess.handlers.PlayerJoinedHandler;

public class SFSChess extends SFSExtension 
{
	private final String version = "0.1";
	private ChessBoard mGameBoard;
	private PlayersModel mPlayers;
	private TurnModel mTurn;
	
	@Override
	public void init() 
	{
		// Trace the version
		trace("SFSChess Extension started, rel. " + version);
		
		// Add the handlers
	    addRequestHandler("PLAYER_JOINED", PlayerJoinedHandler.class);
	    addRequestHandler("MOVE_PIECE", MoveHandler.class);

	    // Initialize the game
	    initializeGame();
	}
	
	/**
	 * Initialize any game memory
	 */
	public void initializeGame()
	{
		// Create a new chess board
		mGameBoard = new ChessBoard();
		mGameBoard.initializeBoard();
		
		// Create the models
		mPlayers = new PlayersModel();
		mTurn = new TurnModel();		
	}
	
	/**
	 * Get the game board
	 * @return (ChessBoard) 
	 */
	public ChessBoard getGameBoard() 
	{
		return mGameBoard;
	}

	/**
	 * Player has joined the game
	 * @param user (User) The user that joined the game
	 */
	public void playerJoined( User user )
	{
		// Check to make sure something didn't go horrible wrong
		if ( mPlayers.getPlayer1() != null && mPlayers.getPlayer2() != null ) 
		{
			throw new IllegalStateException("Already have 2 players!");
		}

		// Send the board to the player
		sendBoard( user );
		
		if ( mPlayers.getPlayer1() == null ) 
		{
			mPlayers.setPlayer1(user);
			
			// Send the valid moves just to player 1
			sendValidMoves( user );
			
		} 
		else 
		{
			mPlayers.setPlayer2(user);
		}

		// Send the players
		sendPlayers();

		// Check to see if we have 2 players
		if ( mPlayers.getPlayer1() != null && mPlayers.getPlayer2() != null )
		{
			sendTurn();
		}
	}

	/**
	 * Player left the game
	 * @param user (User) The user that left
	 * @param connectionDroped (boolean) Did the connection drop.
	 */
	public void playerLeft( User user, boolean connectionDroped ) 
	{
		if ( user.getId() == mPlayers.getPlayer1().getId() ) 
		{
			sendGameOver( mPlayers.getPlayer2() );
		} 
		else if ( user.getId() == mPlayers.getPlayer2().getId() ) 
		{
			sendGameOver( mPlayers.getPlayer1() );
		}
	}	

	/**
	 * Get who's turn it is
	 * @return (User) The players id.
	 */
	public User getWhoseTurn()
    {
	    return mTurn.getTurn();
    }
	
	/**
	 * Swap turn
	 */
	public void swapTurn()
	{
		if ( mTurn.getTurn() == mPlayers.getPlayer1() )
		{
			mTurn.setTurn( mPlayers.getPlayer2() );
		} 
		else
		{
			mTurn.setTurn( mPlayers.getPlayer1() );
		}
		
		sendBoard( mTurn.getTurn() );
		sendValidMoves( mTurn.getTurn() );
		sendTurn();
	}


	private void sendBoard(User user) 
	{
		ISFSObject board = SFSObject.newInstance();
		board.putClass("BoardModel", mGameBoard.getBoard() );
		sendSFSObject("CHESS_BOARD", board, user);
		
	}

	private void sendValidMoves(User user) 
	{
		ISFSObject validMoves = SFSObject.newInstance();
		validMoves.putClass("ValidMovesModel", mGameBoard.getValidMoves() );
		sendSFSObject("CHESS_VALID_MOVES", validMoves, user);
	}

	private void sendPlayers() 
	{
		ISFSObject players = SFSObject.newInstance();
		players.putClass("PlayersModel", mPlayers );
		sendSFSObject("CHESS_PLAYERS", players );		
	}

	private void sendTurn() 
	{
		ISFSObject turn = SFSObject.newInstance();
		turn.putClass("PlayersModel", mTurn );
		sendSFSObject("CHESS_TURN", turn );			
	}
	
	private void sendGameOver(User winner) 
	{
		ISFSObject winnerObj = SFSObject.newInstance();
		winnerObj.putClass("GameOverModel", new GameOverModel( winner ) );
		sendSFSObject("CHESS_GAME_OVER", winnerObj );			
	}
	
	/**
	 * Send a sfs object to all the clients in the room
	 * @param name (String) Event name the client receives
	 * @param object (ISFSObject) The object being sent to the client
	 */
	public void sendSFSObject(String name, ISFSObject object)
	{
		send( name, object, getParentRoom().getUserList() );
	}

	/**
	 * Send a sfsobject to a user in the room
	 * @param name (String) Event name the client receives
	 * @param object (ISFSObject) The object being sent to the client
	 * @param user (User) The user to send the object too
	 */
	public void sendSFSObject(String name, ISFSObject object, User user) 
	{
		send( name, object, user );
	}
}	
