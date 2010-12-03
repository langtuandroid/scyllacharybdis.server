package com.pikitus.games.chess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;
import com.pikitus.games.chess.ChessBoard;
import com.pikitus.games.chess.handlers.MoveHandler;
import com.pikitus.games.chess.handlers.PlayerJoinedHandler;

public class SFSChess extends SFSExtension 
{
	private final String version = "0.2";
	private ChessBoard mGameBoard;
	private volatile boolean mGameStarted = false;
	private User mWhoseTurn;
	private User mPlayer1;
	private User mPlayer2;
	
	@Override
	public void init() 
	{
		// Trace the version
		trace("SFSChess Extension started, rel. " + version);
		
		// Create a new chess board
		mGameBoard = new ChessBoard();
		
		// Register the player joined
	    addRequestHandler("playerJoined", PlayerJoinedHandler.class);
	    
	    // Register the move piece handler
	    addRequestHandler("movePiece", MoveHandler.class);
	}

	/**
	 * Has the game started yet
	 * @return (Boolean) True if the game has started.
	 */
	public boolean isStarted()
	{
		return mGameStarted;
	}
	
	/**
	 * Get who's turn it is
	 * @return (User) The players id.
	 */
	public User getWhoseTurn()
    {
	    return mWhoseTurn;
    }
	
	/**
	 * Set who's turn it is
	 * @param user (User) The user
	 */
	void setTurn(User user)
	{
		mWhoseTurn = user;
	}
	
	/**
	 * Start the game
	 */
	public void startGame() 
	{
		// Check to make sure the game hasn't already started
		if (mGameStarted) 
		{
			// Throw an exception
			throw new IllegalStateException("Game is already started!");
		}

		// Mark the game started
		mGameStarted = true;
		
		// Reset the board
		mGameBoard.resetBoard();
		
		// Get the users by the location they entered the game
		mPlayer1 = getParentRoom().getUserByPlayerId(1);
		mPlayer2 = getParentRoom().getUserByPlayerId(2);
		
		// If no player is set yet then set it to player 1 ( white )
		if (mWhoseTurn == null)
			mWhoseTurn = mPlayer1;
		
		// Create a response object
		ISFSObject resObj = new SFSObject();
		
		// Send who's turn it is
		resObj.putInt("turn", mWhoseTurn.getPlayerId());
		
		// Send player 1s information
		resObj.putUtfString("player1Name", mPlayer1.getName());
		resObj.putInt("player1Id", mPlayer1.getId());
		
		// Send player 2s information
		resObj.putUtfString("player2Name", mPlayer2.getName());
		resObj.putInt("player2Id", mPlayer2.getId());
		
		// Send information to all the clients
		this.sendSFSObject( "start", resObj );		
	}
	
	/**
	 * Send all the users the player that won.
	 * @param winner (User) The player that won.
	 */
	public void sendWinner( User winner ) 
	{
		ISFSObject resObj = new SFSObject();
		resObj.putInt("winner", winner.getId() );
		send("winner", resObj, getParentRoom().getUserList() );		
		gameOver();
	}
	
	/**
	 * Player left the game
	 * @param user (User) The user that left
	 * @param connectionDroped (boolean) Did the connection drop.
	 */
	public void playerLeft( User user, boolean connectionDroped ) 
	{
		if ( user.getId() == mPlayer1.getId() ) {
			sendWinner( mPlayer2 );
			gameOver();
			return;
		}
		if ( user.getId() == mPlayer2.getId() ) {
			sendWinner( mPlayer1 );
			gameOver();
			return;
		}
	}
	
	/**
	 * Send the user a game over command
	 */
	public void gameOver() 
	{
		this.sendSFSObject( "gameOver", new SFSObject() );				
	}
	
	public void move( String to, String from ) 
	{
		ISFSObject resObj = new SFSObject();
		resObj.putBool("valid", mGameBoard.move( to, from ) );
		send("moveResults", resObj, getParentRoom().getUserList() );		
		
		sendBoard();
	}
	
	public void sendBoard() 
	{
		send("getBoardResults", getBoardArray(), getParentRoom().getUserList());		
		send("getPredictionsResults", getPredictionsArray(), getParentRoom().getUserList() );		
	}

	public void sendBoard(User user) 
	{
		send("getBoardResults", getBoardArray(), user);		
		send("getPredictionsResults", getPredictionsArray(), user);		
	}
	
	public void sendSFSObject(String name, ISFSObject object)
	{
		send( name, object, getParentRoom().getUserList() );
	}

	public void sendSFSObject(String name, ISFSObject object, User user) 
	{
		send( name, object, user );
	}
}	
