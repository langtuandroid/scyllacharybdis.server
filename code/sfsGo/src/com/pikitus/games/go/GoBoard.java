package com.pikitus.games.go;

import models.go.BoardModel;
import models.go.MoveModel;


public class GoBoard 
{
	private BoardModel mBoardModel = new BoardModel();
	private int[][] mBoard = new int[19][19];

	public GoBoard() 
	{
		initializeBoard();
	}
	
	public void initializeBoard()
	{
		for (int i = 0; i < 19; i++) 
		{
			for (int j = 0; j < 19; j++) 
			{
				mBoard[i][j] = 0;
			}
		}
	}
	
	public BoardModel getBoard() 
	{
		mBoardModel.setBoard(mBoard);
		return mBoardModel;
	}
	
	public MoveModel movePiece( MoveModel move )
	{
		// set if its a valid move and return the model
		move.SetValid(testBoard(move));
		return move;
	}
	
	public boolean testBoard( MoveModel move )
	{
		return true;
	}
}
