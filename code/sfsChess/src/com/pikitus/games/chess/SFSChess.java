package com.pikitus.games.chess;

import models.chess.BoardModel;
import models.chess.GameOverModel;
import models.chess.MoveModel;
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
		if ( mPlayers.getPlayer1() != 0 && mPlayers.getPlayer2() != 0 ) 
		{
			throw new IllegalStateException("Already have 2 players!");
		}

		// Send the board to the player
		trace("ATTEMPTING TO SEND THE BOARD");
		
		sendBoard( user.getId() );
		
		if ( mPlayers.getPlayer1() == 0 ) 
		{
			mPlayers.setPlayer1(user.getId());
			
			// Send the valid moves just to player 1
			//sendValidMoves( user.getId() );
			
		} 
		else 
		{
			mPlayers.setPlayer2(user.getId());
		}

		// Send the players
		sendPlayers();

		// Check to see if we have 2 players
		if ( mPlayers.getPlayer1() != 0 && mPlayers.getPlayer2() != 0 )
		{
			sendTurn();
		}
	}
	
	public void movePiece( User user, MoveModel move ) 
	{
		boolean valid = getGameBoard().movePiece( move );
		move.setValid( valid );

		ISFSObject resObj = new SFSObject();
		resObj.putClass("MoveModel", move);

		// Is it a valid move
		if ( valid ) 
		{
			// Send the move back to the player if there is a problem
			sendSFSObject("CHESS_MOVE_RESULTS", resObj);
		} 
		else 
		{
			// Send the move results
			sendSFSObject("CHESS_MOVE_RESULTS", resObj, user);
			
			// Swap player turns
			swapTurn();
		}
	}		

	/**
	 * Player left the game
	 * @param user (User) The user that left
	 * @param connectionDroped (boolean) Did the connection drop.
	 */
	public void playerLeft( User user, boolean connectionDroped ) 
	{
		if ( user.getId() == mPlayers.getPlayer1() ) 
		{
			sendGameOver( mPlayers.getPlayer2() );
		} 
		else if ( user.getId() == mPlayers.getPlayer2() ) 
		{
			sendGameOver( mPlayers.getPlayer1() );
		}
	}	

	/**
	 * Get who's turn it is
	 * @return (int) The players id.
	 */
	public int getWhoseTurn()
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


	private void sendBoard(int userId) 
	{
		trace("SENDING BOARD");
		User user = getParentRoom().getUserById(userId);
		ISFSObject board = SFSObject.newInstance();
		
		BoardModel model = mGameBoard.getBoard();
		//trace("BOARD CONTENTS: " + model.getBoard() );
		
		board.putClass("BoardModel", model );
		
		//trace("USER CONTENTS: " + user.toString());
		sendSFSObject("CHESS_BOARD", board, user);
		
	}

	private void sendValidMoves(int userId) 
	{
		User user = getParentRoom().getUserById(userId);
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
	
	private void sendGameOver(int winnerId) 
	{
		ISFSObject winnerObj = SFSObject.newInstance();
		winnerObj.putClass("GameOverModel", new GameOverModel( winnerId ) );
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
