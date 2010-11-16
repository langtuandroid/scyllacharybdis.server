package sfs2x.extensions.games.chess;

import sfs2x.extensions.games.chess.SFSChess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class MoveHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		
		if ( !user.isPlayer() ) {
			return ;
		}
	
		if ( gameExt.getWhoseTurn() != user ) {
			return ;
		}

		if ( ! gameExt.isStarted() ) {
			
		}
		
		String to = params.getUtfString("to");
		String from = params.getUtfString("from");
		
		gameExt.move(to, from); 
		
		if ( gameExt.isCheckMate() ) 
		{
			// Send a game over command with the winner
			gameExt.sendWinner( user );
		}
	}
}