package sfs2x.extensions.games.chess;

import sfs2x.extensions.games.chess.ReadyHandler;
import sfs2x.extensions.games.chess.ChessBoard;
import sfs2x.extensions.games.chess.MoveHandler;
import sfs2x.extensions.games.chess.OnUserGoneHandler;

import com.smartfoxserver.v2.core.SFSEventType;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSObject;
import com.smartfoxserver.v2.extensions.SFSExtension;

public class SFSChess extends SFSExtension {

	private final String version = "0.5";
	private ChessBoard mGameBoard;
	private volatile boolean mGameStarted = false;
	private User mWhoseTurn;
	private User mPlayer1;
	private User mPlayer2;
	
	
	@Override
	public void init() {
		trace("SFSChess Extension started, rel. " + version);
		
		mGameBoard = new ChessBoard();

	    addRequestHandler("ready", ReadyHandler.class);
	    addRequestHandler("move", MoveHandler.class);
	    
	    addEventHandler(SFSEventType.USER_DISCONNECT, OnUserGoneHandler.class);
	    addEventHandler(SFSEventType.USER_LEAVE_ROOM, OnUserGoneHandler.class);
	}

	boolean isStarted()
	{
		return mGameStarted;
	}
	
	User getWhoseTurn()
    {
	    return mWhoseTurn;
    }
	
	void setTurn(User user)
	{
		mWhoseTurn = user;
	}
	
	public void startGame() 
	{
		if (mGameStarted) 
		{
			throw new IllegalStateException("Game is already started!");
		}

		mGameStarted = true;
		mGameBoard.resetBoard();
		
		mPlayer1 = getParentRoom().getUserByPlayerId(1);
		mPlayer2 = getParentRoom().getUserByPlayerId(2);
		
		// No turn assigned? Let's start with player 1
		if (mWhoseTurn == null)
			mWhoseTurn = mPlayer1;
		
		// Send START event to client
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
		send("start", resObj, getParentRoom().getUserList());		
	}
	
	public void sendWinner( User winner ) 
	{
		ISFSObject resObj = new SFSObject();
		resObj.putInt("winner", winner.getId() );
		send("winner", resObj, getParentRoom().getUserList() );		
		gameOver();
	}
	
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
	
	public void gameOver() 
	{
		send("gameOver", new SFSObject(), getParentRoom().getUserList() );				
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
		send("getBoardResults", mGameBoard.getBoardArray(), getParentRoom().getUserList());		
		send("getPredictionsResults", mGameBoard.getPredictionsArray(), getParentRoom().getUserList() );		
	}

	public void sendBoard(User user) 
	{
		send("getBoardResults", mGameBoard.getBoardArray(), user);		
		send("getPredictionsResults", mGameBoard.getPredictionsArray(), user);		
	}

	public boolean isCheckMate() 
	{
		return mGameBoard.checkMate();
	}
}	
