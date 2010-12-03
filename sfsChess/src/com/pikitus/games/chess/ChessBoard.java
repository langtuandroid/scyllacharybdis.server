package com.pikitus.games.chess;

import java.util.HashMap;

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
	private long[] mSquareBoards = new long[64];
	
	// An array of string labels that runs parallel to mSquareBoards
	private String[] mSquareLabels = {  "a1", "b1", "c1", "d1", "e1", "f1", "g1", "h1",
										"a2", "b2", "c2", "d2", "e2", "f2", "g2", "h2",
										"a3", "b3", "c3", "d3", "e3", "f3", "g3", "h3",
										"a4", "b4", "c4", "d4", "e4", "f4", "g4", "h4",
										"a5", "b5", "c5", "d5", "e5", "f5", "g5", "h5",
										"a6", "b6", "c6", "d6", "e6", "f6", "g6", "h6",
										"a7", "b7", "c7", "d7", "e7", "f7", "g7", "h7",
										"a8", "b8", "c8", "d8", "e8", "f8", "g8", "h8"
									 };
	
	// A map of square indices indexed by label
	private HashMap<String, Int> mSquareIndices = new HashMap<String, Int>();
	
	// A hash table of bit boards, each representing a set of pieces
	private HashMap<String, Long> mPieceBoards  = new HashMap<String, Long>();
	
	public ChessBoard() 
	{
	}
	
	public void initializeBoard() 
	{
		// Setup mSquareBoards
	    for( int i = 0; i < mSquareBoards.length; i++ )
	    {
	      // Push in a 1 bit on the right hand side and shift it left i times
	      mSquareBoards[ i ] = ( 1L << i );
	    }
	    
	    // Setup mSquareLabels
	    for( int i = 0; i < mSquareLabels.length; i++ )
	    {
	    	mSquareIndices.put(mSquareLabels[i], i);
	    }
	    
	    // All squares are represented by -1 (Java uses signed 64-bit integers)
	    mPieceBoards.put(ALL_SQUARES, -1L);
	    
	    // Setup the boards for the pieces of each color 
	    mPieceBoards.put(WHITE_ROOKS, 	mSquareBoards[ mSquareIndices.get("a1") ] |
	                            		mSquareBoards[ mSquareIndices.get("h1") ]);
	    
	    mPieceBoards.put(WHITE_KNIGHTS, mSquareBoards[ mSquareIndices.get("b1") ] |
	    								mSquareBoards[ mSquareIndices.get("g1") ]);
	    
	    mPieceBoards.put(WHITE_BISHOPS, mSquareBoards[ mSquareIndices.get("c1") ] |
                				  		mSquareBoards[ mSquareIndices.get("f1") ]);
	    
	    mPieceBoards.put(WHITE_QUEEN, 	mSquareBoards[ mSquareIndices.get("d1") ]);
        
	    mPieceBoards.put(WHITE_KING,	mSquareBoards[ mSquareIndices.get("e1") ]);
	    
	    mPieceBoards.put(WHITE_PAWNS, 	mSquareBoards[ mSquareIndices.get("a2") ] | 
							    		mSquareBoards[ mSquareIndices.get("b2") ] |
							    		mSquareBoards[ mSquareIndices.get("c2") ] |
							    		mSquareBoards[ mSquareIndices.get("d2") ] |
							    		mSquareBoards[ mSquareIndices.get("e2") ] |
							    		mSquareBoards[ mSquareIndices.get("f2") ] |
							    		mSquareBoards[ mSquareIndices.get("g2") ] |
							    		mSquareBoards[ mSquareIndices.get("h2") ] );
	    
	    mPieceBoards.put(WHITE_PIECES,  mPieceBoards.get(WHITE_PAWNS) |
	    								mPieceBoards.get(WHITE_ROOKS) |
	    								mPieceBoards.get(WHITE_KNIGHTS) |
	    								mPieceBoards.get(WHITE_BISHOPS) |
	    								mPieceBoards.get(WHITE_QUEEN) |
	    								mPieceBoards.get(WHITE_KING) );
	    
	    mPieceBoards.put(BLACK_ROOKS, 	mSquareBoards[ mSquareIndices.get("a8") ] |
	    								mSquareBoards[ mSquareIndices.get("h8") ]);

	    mPieceBoards.put(BLACK_KNIGHTS, mSquareBoards[ mSquareIndices.get("b8") ] |
										mSquareBoards[ mSquareIndices.get("g8") ]);

	    mPieceBoards.put(BLACK_BISHOPS, mSquareBoards[ mSquareIndices.get("c8") ] |
		  								mSquareBoards[ mSquareIndices.get("f8") ]);

	    mPieceBoards.put(BLACK_QUEEN, 	mSquareBoards[ mSquareIndices.get("d8") ]);

	    mPieceBoards.put(BLACK_KING,	mSquareBoards[ mSquareIndices.get("e8") ]);

	    mPieceBoards.put(BLACK_PAWNS, 	mSquareBoards[ mSquareIndices.get("a7") ] | 
							    		mSquareBoards[ mSquareIndices.get("b7") ] |
							    		mSquareBoards[ mSquareIndices.get("c7") ] |
							    		mSquareBoards[ mSquareIndices.get("d7") ] |
							    		mSquareBoards[ mSquareIndices.get("e7") ] |
							    		mSquareBoards[ mSquareIndices.get("f7") ] |
							    		mSquareBoards[ mSquareIndices.get("g7") ] |
							    		mSquareBoards[ mSquareIndices.get("h7") ] );
							    
	    mPieceBoards.put(BLACK_PIECES,  mPieceBoards.get(BLACK_PAWNS) |
										mPieceBoards.get(BLACK_ROOKS) |
										mPieceBoards.get(BLACK_KNIGHTS) |
										mPieceBoards.get(BLACK_BISHOPS) |
										mPieceBoards.get(BLACK_QUEEN) |
										mPieceBoards.get(BLACK_KING) );
	    
	    
	}
	
	public void printBoard()
	{
		System.out.println( "-------------------------" );
		
		for( int i = 7; i >= 0; i-- )
		{
			String line = "|";
			
			for( int j = 0; j < 8; j++ )
			{
				int index = i * 8 + j;
				if( mSquareBoards[index] & mPieceBoards.get( WHITE_PAWNS ) != 0 )
				{
					line += "WP|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( WHITE_ROOKS ) != 0 )
				{
					line += "WR|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( WHITE_KNIGHTS ) != 0 )
				{
					line += "WN|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( WHITE_BISHOPS ) != 0 )
				{
					line += "WB|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( WHITE_QUEEN ) != 0 )
				{
					line += "WQ|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( WHITE_KING ) != 0 )
				{
					line += "WK|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_PAWNS ) != 0 )
				{
					line += "BP|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_ROOKS ) != 0 )
				{
					line += "BR|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_KNIGHTS ) != 0 )
				{
					line += "BN|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_BISHOPS ) != 0 )
				{
					line += "BB|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_QUEEN ) != 0 )
				{
					line += "BQ|";
				}
				else if ( mSquareBoards[index] & mPieceBoards.get( BLACK_KING ) != 0 )
				{
					line += "BK|";
				}
				else
				{
					line += "  |";
				}
			}
			
			System.out.println(line);
		}
		
		System.out.println( "-------------------------" );
		
	}
	
	/**
	 * Move a piece
	 * @param to (String) Alpha Numeric ( A1 )
	 * @param from (String) Alpha Numeric ( A1 )
	 * @return (boolean) Returns true if its a valid move.
	 */
	public boolean movePiece(String to, String from) 
	{
		int toRow = (int) convertToNumber( to.substring(0, 1) );
		int toCol = (int) convertToNumber( to.substring(1, 2) );

		int fromRow = (int) convertToNumber( from.substring(0, 1) );
		int fromCol = (int) convertToNumber( from.substring(1, 2) );
		
		return true;
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
	
	/**
	 * Get the board array
	 * @return (ISFSObject) I should name each one
	 */
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

	/**
	 * Get valid move array
	 * @return
	 */
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
	
	/**
	 * Hacky convert statement
	 * @param substring
	 * @return
	 */
	private int convertToNumber(String substring) 
	{
		substring = substring.toLowerCase();
		if ( substring == "a" ) { return 1; }
		if ( substring ==  "b" ) { return 2; }
		if ( substring ==  "c" ) { return 3; }
		if ( substring ==  "d" ) { return 4; }
		if ( substring ==  "e" ) { return 5; }
		if ( substring ==  "f" ) { return 6; }
		if ( substring ==  "g" ) { return 7; }
		if ( substring ==  "h" ) { return 8; }

		if ( substring ==  "1" ) { return 1; }
		if ( substring ==  "2" ) { return 2; }
		if ( substring ==  "3" ) { return 3; }
		if ( substring ==  "4" ) { return 4; }
		if ( substring ==  "5" ) { return 5; }
		if ( substring ==  "6" ) { return 6; }
		if ( substring ==  "7" ) { return 7; }
		if ( substring ==  "8" ) { return 8; }
		return 0;
	}
}
