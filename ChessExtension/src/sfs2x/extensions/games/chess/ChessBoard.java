package sfs2x.extensions.games.chess;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.smartfoxserver.v2.entities.data.ISFSArray;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.data.SFSArray;
import com.smartfoxserver.v2.entities.data.SFSObject;

public class ChessBoard {
	
	private Map<String, Long> mBoard  = new HashMap<String, Long>();
	
	public ChessBoard() 
	{
		initializeBoard();
		resetBoard();
	}
	
	public void initializeBoard() {
		mBoard.put("whitePieces", (long) 255);
		mBoard.put("blackPieces", (long) 255 >>> 4);
	}
	
	public void resetBoard() 
	{
		mBoard.put("whitePieces", (long) 255);
		mBoard.put("blackPieces", (long) 255 >>> 4);
	}
	
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

	public ISFSObject getPredictionsArray()
	{
		ISFSArray predictionsArray = new SFSArray();
		for ( int i = 0; i < 16; i++ ) {
			predictionsArray.addLong((long) 0);
		}
		ISFSObject obj = new SFSObject();
		obj.putSFSArray("predictionsArray", predictionsArray);
		return obj;
	}

	public boolean move(String to, String from) 
	{
		int toRow = (int) convertToNumber( to.substring(0, 1) );
		int toCol = (int) convertToNumber( to.substring(1, 2) );

		int fromRow = (int) convertToNumber( from.substring(0, 1) );
		int fromCol = (int) convertToNumber( from.substring(1, 2) );
		
		return move( toRow, toCol, fromRow, fromCol );
		
	}
	
	private boolean move(int toRow, int toCol, int fromRow, int fromCol) 
	{
		return true;
	}

	// Hacky lookup table
	private int convertToNumber(String substring) {
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

	public boolean checkMate() 
	{
		return false;
	}
}
