package sfs2x.extensions.games.chess;

import sfs2x.extensions.games.chess.SFSChess;

import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class ReadyHandler extends BaseClientRequestHandler
{
	@Override
	public void handleClientRequest(User user, ISFSObject params)
	{
		SFSChess gameExt = (SFSChess) getParentExtension();
		
		if (user.isPlayer())
		{
			gameExt.sendBoard(user);

			// Checks if two players are available and start game
			if (gameExt.getParentRoom().getSize().getUserCount() == 2)
				gameExt.startGame();
		}
	}
}