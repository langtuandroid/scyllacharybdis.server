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
	
	private Map<String, Long> mBoard  = new HashMap<String, Long>();
	
	/**
	 * Constructor
	 */
	public ChessBoard() 
	{
		resetBoard();
	}

	/**
	 * Initialize the chess board
	 */
	public void resetBoard() 
	{
		mBoard.put("whitePawns", (long) ( 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40 | 0x80 ) << 8 );
		mBoard.put("whiteRooks", (long) ( 0x01 | 0x80 ) );
		mBoard.put("whiteKnight", (long) ( 0x02 | 0x40 ) );
		mBoard.put("whiteBishop", (long) ( 0x04 | 0x20 ) );
		mBoard.put("whiteKing", (long) ( 0x10 ) );
		mBoard.put("whiteQueen", (long) ( 0x08 ) );
		
		mBoard.put("blackPawns", (long) ( 0x01 | 0x02 | 0x04 | 0x08 | 0x10 | 0x20 | 0x40 | 0x80 ) >>> 16 );
		mBoard.put("blackRooks", (long) ( 0x01 | 0x80 ) >>> 8 );
		mBoard.put("blackKnight", (long) ( 0x02 | 0x40 ) >>> 8 );
		mBoard.put("blackBishop", (long) ( 0x04 | 0x20 ) >>> 8 );
		mBoard.put("blackQueen", (long) ( 0x08 ) >>> 8 );		
		mBoard.put("blackKing", (long) ( 0x10 ) >>> 8 );
		
		Iterator<String> iterator = mBoard.keySet().iterator();
		while( iterator.hasNext() ) 
		{
		  String key   = iterator.next();
		  Long value = mBoard.get(key);
		  String bitString = Long.toBinaryString(value);		  
		  System.out.print("Array: " + key + " value: " + bitString);
		}
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
