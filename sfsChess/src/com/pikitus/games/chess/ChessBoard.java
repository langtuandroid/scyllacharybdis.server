package com.pikitus.games.chess;



import java.util.HashMap;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ChessBoard 
{
	
	// Indices for mPieceBoards
	private final String WHITE_PAWNS = "white_pawns";
	private final String WHITE_ROOKS = "white_rooks";
	private final String WHITE_KNIGHTS = "white_knights";
	private final String WHITE_BISHOPS = "white_bishops";
	private final String WHITE_QUEEN = "white_queen";
	private final String WHITE_KING = "white_king";
	private final String BLACK_PAWNS = "black_pawns";
	private final String BLACK_ROOKS = "black_rooks";
	private final String BLACK_KNIGHTS = "black_knights";
	private final String BLACK_BISHOPS = "black_bishops";
	private final String BLACK_QUEEN = "black_queen";
	private final String BLACK_KING = "black_king";
	private final String WHITE_PIECES = "white_pieces";
	private final String BLACK_PIECES = "black_pieces";
	private final String ALL_SQUARES = "all_squares";
	
	// An array of bit boards, each representing a single square
	private ArrayList<Long> mSquareArray = new ArrayList<Long>();
	
	// An array of string labels that runs parallel to mSquareArray
	private String[] mSquareLabels = {  "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
										"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
										"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
										"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
										"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
										"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
										"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
										"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"
									 };
	
	// A hash map of bit boards indexed by label, each representing a single square
	private HashMap<String, Long> mSquareMap = new HashMap<String, Long>();
	
	// A has map of square labels indexed by square
	private HashMap<Long, String> mLabelMap = new HashMap<Long, String>();
		
	// A hash table of bit boards, each representing a set of pieces
	private HashMap<String, Long> mPieceBoards  = new HashMap<String, Long>();
	
	private long[] mRows = new long[8];
	
	private long[] mColumns = new long[8];
	
	// Current player, 0 is white, 1 is black
	private int currentPlayer = 0;
	
	// Constants for current player
	private final int WHITE = 0;
	private final int BLACK = 1;
	
	public ChessBoard() 
	{
	}
	
	public void initializeBoard() 
	{
		for( int i = 0; i < mRows.length; i++ )
		{
			mRows[i] = 0L;
			mColumns[i] = 0L;
		}
		
		// Setup mSquareArray
	    for( int i = 0; i < 64; i++ )
	    {
	      // Push in a 1 bit on the right hand side and shift it left i times
	      mSquareArray.add(( 1L << i ));
	      mSquareMap.put( mSquareLabels[i], mSquareArray.get(i) );
	      mLabelMap.put( mSquareArray.get(i), mSquareLabels[i] );
	      
	      mRows[ i / 8 ] = mRows[ i / 8 ] | mSquareArray.get(i);
	      mColumns[ i % 8 ] = mColumns[ i % 8 ] | mSquareArray.get(i);
	    }
	    
	    // All squares are represented by -1 (Java uses signed 64-bit integers)
	    mPieceBoards.put(ALL_SQUARES, -1L);
	    
	    // Setup the boards for the pieces of each color 
	    mPieceBoards.put(WHITE_ROOKS, 	mSquareMap.get("a1") |
	                            		mSquareMap.get("h1") );
	    
	    mPieceBoards.put(WHITE_KNIGHTS, mSquareMap.get("b1") |
	    								mSquareMap.get("g1") );
	    
	    mPieceBoards.put(WHITE_BISHOPS, mSquareMap.get("c1") |
                				  		mSquareMap.get("f1") );
	    
	    mPieceBoards.put(WHITE_QUEEN, 	mSquareMap.get("d1"));
        
	    mPieceBoards.put(WHITE_KING,	mSquareMap.get("e1"));
	    
	    mPieceBoards.put(WHITE_PAWNS, 	mSquareMap.get("a2") | 
							    		mSquareMap.get("b2") |
							    		mSquareMap.get("c2") |
							    		mSquareMap.get("d2") |
							    		mSquareMap.get("e2") |
							    		mSquareMap.get("f2") |
							    		mSquareMap.get("g2") |
							    		mSquareMap.get("h2") );
	      
	    mPieceBoards.put(BLACK_ROOKS, 	mSquareMap.get("a8") |
	    								mSquareMap.get("h8"));

	    mPieceBoards.put(BLACK_KNIGHTS, mSquareMap.get("b8") |
										mSquareMap.get("g8"));

	    mPieceBoards.put(BLACK_BISHOPS, mSquareMap.get("c8") |
		  								mSquareMap.get("f8"));

	    mPieceBoards.put(BLACK_QUEEN, 	mSquareMap.get("d8"));

	    mPieceBoards.put(BLACK_KING,	mSquareMap.get("e8"));

	    mPieceBoards.put(BLACK_PAWNS, 	mSquareMap.get("a7") | 
							    		mSquareMap.get("b7") |
							    		mSquareMap.get("c7") |
							    		mSquareMap.get("d7") |
							    		mSquareMap.get("e7") |
							    		mSquareMap.get("f7") |
							    		mSquareMap.get("g7") |
							    		mSquareMap.get("h7") );
							    
	   updateSummaryBoards();
	    
	}
	
	public void printBoard()
	{
		System.out.println( "|--|--|--|--|--|--|--|--|" );
		
		for( int i = 7; i >= 0; i-- )
		{
			String line = "|";
			
			for( int j = 0; j < 8; j++ )
			{
				int index = i * 8 + j;
				if( (mSquareArray.get(index) & mPieceBoards.get( WHITE_PAWNS )) != 0 )
				{
					line += "WP|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( WHITE_ROOKS )) != 0 )
				{
					line += "WR|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( WHITE_KNIGHTS )) != 0 )
				{
					line += "WN|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( WHITE_BISHOPS )) != 0 )
				{
					line += "WB|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( WHITE_QUEEN )) != 0 )
				{
					line += "WQ|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( WHITE_KING )) != 0 )
				{
					line += "WK|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_PAWNS )) != 0 )
				{
					line += "BP|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_ROOKS )) != 0 )
				{
					line += "BR|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_KNIGHTS )) != 0 )
				{
					line += "BN|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_BISHOPS )) != 0 )
				{
					line += "BB|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_QUEEN )) != 0 )
				{
					line += "BQ|";
				}
				else if ( (mSquareArray.get(index) & mPieceBoards.get( BLACK_KING )) != 0 )
				{
					line += "BK|";
				}
				else
				{
					line += "  |";
				}
			}
			
			System.out.println(line);
			System.out.println( "|--|--|--|--|--|--|--|--|" );
		}
	}
	
	public void printMoves( ArrayList<MoveModel> moves )
	{
		System.out.println( "|--|--|--|--|--|--|--|--|" );
		
		for( int i = 7; i >= 0; i-- )
		{
			String line = "|";
			
			for( int j = 0; j < 8; j++ )
			{
				boolean isMove = false;
				
				for ( MoveModel move:moves )
				{
					if ( mSquareLabels[ i*8 + j ] == move.to )
					{
						line += "**|";
						isMove = true;
						break;
					}
				}
				
				if ( isMove == false )
				{
					line += "  |";
				}
			}
			
			System.out.println(line);
			System.out.println( "|--|--|--|--|--|--|--|--|" );
		}	
	}
	
	/**
	 * Move a piece
	 * @param (MoveModel) Model representing the move
	 * @return (boolean) Returns true if its a valid move.
	 */
	public boolean movePiece( MoveModel move ) 
	{
		long to = mSquareMap.get(move.to);
		long from = mSquareMap.get(move.from);
		
		// Get the label of the piece to move
		String pieceToMove = getPieceLabel( from, currentPlayer );
		
		if ( pieceToMove != null )
		{
			// Zero out the piece's current position
			mPieceBoards.put( pieceToMove, (~from) & mPieceBoards.get(pieceToMove) );
			
			// Set the piece's new position to 1
			mPieceBoards.put( pieceToMove, to | mPieceBoards.get(pieceToMove) );
		}
		else
		{
			return false;
		}
		
		// Get the piece to take ( if any )
		String pieceBeingTaken = getPieceLabel( to, (currentPlayer + 1) % 2 );
		
		// If there's a piece to take
		if ( pieceBeingTaken != null )
		{
			// Zero out the piece's current position
			mPieceBoards.put( pieceBeingTaken, (~to) & mPieceBoards.get(pieceBeingTaken) );
		}
			
		// Update the current player
		currentPlayer = ( currentPlayer + 1 ) % 2;
		
		// Update the white and black piece boards
		updateSummaryBoards();
		
		return true;
	}
	
	private String getPieceLabel( long to, int player )
	{
		if ( player == WHITE )
		{
			return getWhitePieceLabel( to );
		}
		else if ( player == BLACK )
		{
			return getBlackPieceLabel( to );
		}
		else
		{
			return null;
		}
	}

	private String getWhitePieceLabel( long square )
	{
		if ( (square & mPieceBoards.get(WHITE_PAWNS)) != 0 )
		{
			return WHITE_PAWNS;
		}
		else if( (square & mPieceBoards.get(WHITE_ROOKS)) != 0 )
		{
			return WHITE_ROOKS;
		}
		else if( (square & mPieceBoards.get(WHITE_KNIGHTS)) != 0 )
		{
			return WHITE_KNIGHTS;
		}
		else if( (square & mPieceBoards.get(WHITE_BISHOPS)) != 0 )
		{
			return WHITE_BISHOPS;
		}
		else if( (square & mPieceBoards.get(WHITE_QUEEN)) != 0 )
		{
			return WHITE_QUEEN;
		}
		else if( (square & mPieceBoards.get(WHITE_KING)) != 0 )
		{
			return WHITE_KING;
		}
		else
		{
			return null;
		}
	}
	
	private String getBlackPieceLabel( long square )
	{
		if ( (square & mPieceBoards.get(BLACK_PAWNS)) != 0 )
		{
			return BLACK_PAWNS;
		}
		else if( (square & mPieceBoards.get(BLACK_ROOKS)) != 0 )
		{
			return BLACK_ROOKS;
		}
		else if( (square & mPieceBoards.get(BLACK_KNIGHTS)) != 0 )
		{
			return BLACK_KNIGHTS;
		}
		else if( (square & mPieceBoards.get(BLACK_BISHOPS)) != 0 )
		{
			return BLACK_BISHOPS;
		}
		else if( (square & mPieceBoards.get(BLACK_QUEEN)) != 0 )
		{
			return BLACK_QUEEN;
		}
		else if( (square & mPieceBoards.get(BLACK_KING)) != 0 )
		{
			return BLACK_KING;
		}
		else
		{
			return null;
		}
	}
	
	private void updateSummaryBoards()
	{
		 mPieceBoards.put(BLACK_PIECES,  mPieceBoards.get(BLACK_PAWNS) |
					mPieceBoards.get(BLACK_ROOKS) |
					mPieceBoards.get(BLACK_KNIGHTS) |
					mPieceBoards.get(BLACK_BISHOPS) |
					mPieceBoards.get(BLACK_QUEEN) |
					mPieceBoards.get(BLACK_KING) );

		 mPieceBoards.put(WHITE_PIECES,  mPieceBoards.get(WHITE_PAWNS) |
					mPieceBoards.get(WHITE_ROOKS) |
					mPieceBoards.get(WHITE_KNIGHTS) |
					mPieceBoards.get(WHITE_BISHOPS) |
					mPieceBoards.get(WHITE_QUEEN) |
					mPieceBoards.get(WHITE_KING) );
	}

	/**
	 * Are we in check
	 * @return
	 */
	public boolean isCheck() 
	{
		return false;
	}
	
	/**
	 * Are we in check mate
	 * @return
	 */
	public boolean isCheckMate() 
	{
		return false;
	}	
	
	public HashMap<String, ArrayList<MoveModel>> getLegalMoves()
	{
		return new MoveGenerator().getLegalMoves(currentPlayer);
	}
	
	/**
	 * Get the board array
	 * @return (ISFSObject) I should name each one
	 */
	/*
	public ISFSObject getBoardArray() 
	{
		ISFSArray boardArray = new SFSArray();
		
		Iterator<String> iterator = mBoard.keySet().iterator();
		while( iterator.hasNext() ) {
		  String key   = iterator.next();
		  Long value = mBoard.get(key);
		  boardArray.addLong(value);
		}
		ISFSObject obj = new SFSObject();
		obj.putSFSArray("boardArray", boardArray);
		return obj;
	}
	*/
	
	/**
	 * Get valid move array
	 * @return
	 */
	/*
	public ISFSObject getValidMoveArray()
	{
		ISFSArray validMoveArray = new SFSArray();
		for ( int i = 0; i < 16; i++ ) {
			validMoveArray.addLong((long) 0);
		}
		ISFSObject obj = new SFSObject();
		obj.putSFSArray("predictionsArray", validMoveArray);
		return obj;
	}
	*/
	
	private class MoveGenerator
	{
		public MoveGenerator()
		{
			
		}
		
		public HashMap<String, ArrayList<MoveModel>> getLegalMoves( int player )
		{
			return (player == WHITE) ? getWhiteLegalMoves() : getBlackLegalMoves();	
		}
		
		private HashMap<String, ArrayList<MoveModel>> getWhiteLegalMoves()
		{
			HashMap<String, ArrayList<MoveModel>> legalMoves = new HashMap<String, ArrayList<MoveModel>>();
			
			legalMoves.put(WHITE_PAWNS, getWhitePawnAttacks( ) );
			legalMoves.put(WHITE_ROOKS, getWhiteRookAttacks( ) );
			//legalMoves.put(WHITE_KNIGHTS, getWhiteKnightAttacks( ) );
			legalMoves.put(WHITE_BISHOPS, getWhiteBishopAttacks( ) );
			//legalMoves.put(WHITE_QUEEN, getWhiteQueenAttacks( ) );
			//legalMoves.put(WHITE_KING, getWhiteKingAttacks( ) );
			
			return legalMoves;
		}
		
		private HashMap<String, ArrayList<MoveModel>> getBlackLegalMoves()
		{
			HashMap<String, ArrayList<MoveModel>> legalMoves = new HashMap<String, ArrayList<MoveModel>>();
			
			return legalMoves;
		}
		
		private ArrayList<MoveModel> getWhitePawnAttacks()
		{
			ArrayList<MoveModel> moves = new ArrayList<MoveModel>();
			
			for ( long square:mSquareArray )
			{
				if( (square & mPieceBoards.get(WHITE_PAWNS)) != 0 )
				{
					long moveNorth = square << 8;
					if ( (moveNorth & mPieceBoards.get(WHITE_PIECES)) == 0 )
					{
						moves.add( new MoveModel( mLabelMap.get(square), mLabelMap.get(moveNorth) ) );
					}
					
					long moveNorthWest = square << 9;
				    if ( (moveNorthWest & mPieceBoards.get(BLACK_PIECES)) != 0 )
					{
						moves.add( new MoveModel( mLabelMap.get(square), mLabelMap.get(moveNorthWest) ) );
					}
				    
				    long moveNorthEast = square << 7;
				    if ( (moveNorthEast & mPieceBoards.get(BLACK_PIECES)) != 0 )
					{
						moves.add( new MoveModel( mLabelMap.get(square), mLabelMap.get(moveNorthEast) ) );
					}
					
				    if ( (square & mRows[1]) != 0 )
				    {
				    	moves.add( new MoveModel( mLabelMap.get(square), mLabelMap.get( (moveNorth << 8) ) ) );
				    }
				}
			}
			
			return moves;
		}
		
		private ArrayList<MoveModel> getWhiteRookAttacks()
		{
			return getRookAttacks(mPieceBoards.get(WHITE_ROOKS), WHITE);
		}
		
		private ArrayList<MoveModel> getBlackRookAttacks()
		{
			return getRookAttacks(mPieceBoards.get(BLACK_ROOKS), BLACK);
		}
		
		private ArrayList<MoveModel> getRookAttacks( long piece, int color )
		{
			ArrayList<MoveModel> moves = new ArrayList<MoveModel>();
			
			// Get piece boards for each team
			long opposingPieces = ( color == WHITE ) ? mPieceBoards.get(BLACK_PIECES) : mPieceBoards.get(WHITE_PIECES);
			long allyPieces = ( color == BLACK ) ? mPieceBoards.get(BLACK_PIECES) : mPieceBoards.get(WHITE_PIECES);
			
			// Iterate through each square
			for ( long square:mSquareArray )
			{
				// If the square has a rook
				if( (square & piece) != 0 )
				{
					// Get the index of the square
					int squareIndex = mSquareArray.indexOf(square);
					
					// Go left from position until you hit something or the end of the board
					for ( int i = squareIndex - 1; i >= (squareIndex / 8) * 8; i-- )
					{
						// If we're not colliding with an ally piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing an opposing piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go right from position until you hit something or the end of the board
					for ( int i = squareIndex + 1; i < (squareIndex / 8 + 1) * 8; i++ )
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go up from position until you hit something or the end of the board
					for ( int i = squareIndex + 8; i < 64; i += 8)
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go down from position until you hit something or the end of the board
					for ( int i = squareIndex - 8; i >= 0; i -= 8)
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
				}
			}

			return moves;

		}
		
		private ArrayList<MoveModel> getWhiteBishopAttacks()
		{
			return getBishopAttacks( mPieceBoards.get(WHITE_BISHOPS), WHITE );
		}
		
		private ArrayList<MoveModel> getBlackBishopAttacks()
		{
			return getBishopAttacks( mPieceBoards.get(BLACK_BISHOPS), BLACK );
		}
		
		private ArrayList<MoveModel> getBishopAttacks( long piece, int color )
		{
			ArrayList<MoveModel> moves = new ArrayList<MoveModel>();
			
			// Get piece boards for each team
			long opposingPieces = ( color == WHITE ) ? mPieceBoards.get(BLACK_PIECES) : mPieceBoards.get(WHITE_PIECES);
			long allyPieces = ( color == BLACK ) ? mPieceBoards.get(BLACK_PIECES) : mPieceBoards.get(WHITE_PIECES);
			
			// Iterate through each square
			for ( long square:mSquareArray )
			{
				// If the square has a rook
				if( (square & piece) != 0 )
				{
					// Get the index of the square
					int squareIndex = mSquareArray.indexOf(square);
					
					// Go northeast from position until you hit something or the end of the board
					for ( int i = squareIndex + 9; i % 8 != 0 && i / 8 < 8 && i < 64; i += 9 )
					{
						// If we're not colliding with an ally piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing an opposing piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go northwest from position until you hit something or the end of the board
					for ( int i = squareIndex + 7; i % 8 != 7 && i / 8 < 8 && i < 64; i += 7 )
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go southeast from position until you hit something or the end of the board
					for ( int i = squareIndex - 7; i % 8 != 0 && i / 8 >= 0 && i >= 0; i -= 7)
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
					
					// Go southwest from position until you hit something or the end of the board
					for ( int i = squareIndex - 9; i % 8 != 7 && i / 8 >= 0 && i >= 0; i -= 9)
					{
						// If we're not colliding with a white piece
						if ( (mSquareArray.get(i) & allyPieces) == 0 )
						{
							// Add the move
							moves.add( new MoveModel( mSquareLabels[squareIndex], mSquareLabels[i] ) );
							
							// If we're capturing a black piece
							if ( (mSquareArray.get(i) & opposingPieces) != 0 )
							{
								// Terminate loop since we're stopping to capture the piece
								break;
							}
						}
						else
						{
							break;
						}
					}
				}
			}

			return moves;

		}
	}
}



