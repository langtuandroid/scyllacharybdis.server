package com.pikitus.games.chess;

import java.util.ArrayList;
import java.util.HashMap;

public class UpdateModel 
{
	// Hash map of Arrays of legal moves indexed by the square of the move's from string
	public HashMap<String, ArrayList<MoveModel>> legalMoves;
	
	// Hash map of the 64 bit integers indexed by piece type (wp, wk, wn, bp, bk, bn, etc... )
	public HashMap<String, Long> pieces;
	
	public UpdateModel(HashMap<String, ArrayList<MoveModel>> legalMoves, HashMap<String, Long> pieces)
	{
		this.legalMoves = legalMoves;
		this.pieces = pieces;
	}
}
