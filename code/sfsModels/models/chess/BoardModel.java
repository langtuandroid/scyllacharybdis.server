package models.chess;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class BoardModel implements SerializableSFSType
{
	//private int[] board;
	
	// White
	private int lowerWhitePawns;
	private int higherWhitePawns;
	private int lowerWhiteRooks;
	private int higherWhiteRooks;
	private int lowerWhiteKnights;
	private int higherWhiteKnights;
	private int lowerWhiteBishops;
	private int higherWhiteBishops;
	private int lowerWhiteQueen;
	private int higherWhiteQueen;
	private int lowerWhiteKing;
	private int higherWhiteKing;

	// Black
	private int lowerBlackPawns;
	private int higherBlackPawns;
	private int lowerBlackRooks;
	private int higherBlackRooks;
	private int lowerBlackKnights;
	private int higherBlackKnights;
	private int lowerBlackBishops;
	private int higherBlackBishops;
	private int lowerBlackQueen;
	private int higherBlackQueen;
	private int lowerBlackKing;
	private int higherBlackKing;
	
	public BoardModel() 
	{
	}

	public void setLowerWhitePawns(int lowerWhitePawns) {
		this.lowerWhitePawns = lowerWhitePawns;
	}

	public int getLowerWhitePawns() {
		return lowerWhitePawns;
	}

	public void setHigherWhitePawns(int higherWhitePawns) {
		this.higherWhitePawns = higherWhitePawns;
	}

	public int getHigherWhitePawns() {
		return higherWhitePawns;
	}

	public void setLowerWhiteRooks(int lowerWhiteRooks) {
		this.lowerWhiteRooks = lowerWhiteRooks;
	}

	public int getLowerWhiteRooks() {
		return lowerWhiteRooks;
	}

	public void setHigherWhiteRooks(int higherWhiteRooks) {
		this.higherWhiteRooks = higherWhiteRooks;
	}

	public int getHigherWhiteRooks() {
		return higherWhiteRooks;
	}

	public void setLowerWhiteKnights(int lowerWhiteKnights) {
		this.lowerWhiteKnights = lowerWhiteKnights;
	}

	public int getLowerWhiteKnights() {
		return lowerWhiteKnights;
	}

	public void setHigherWhiteKnights(int higherWhiteKnights) {
		this.higherWhiteKnights = higherWhiteKnights;
	}

	public int getHigherWhiteKnights() {
		return higherWhiteKnights;
	}

	public void setLowerWhiteBishops(int lowerWhiteBishops) {
		this.lowerWhiteBishops = lowerWhiteBishops;
	}

	public int getLowerWhiteBishops() {
		return lowerWhiteBishops;
	}

	public void setHigherWhiteBishops(int higherWhiteBishops) {
		this.higherWhiteBishops = higherWhiteBishops;
	}

	public int getHigherWhiteBishops() {
		return higherWhiteBishops;
	}

	public void setLowerWhiteQueen(int lowerWhiteQueen) {
		this.lowerWhiteQueen = lowerWhiteQueen;
	}

	public int getLowerWhiteQueen() {
		return lowerWhiteQueen;
	}

	public void setHigherWhiteQueen(int higherWhiteQueen) {
		this.higherWhiteQueen = higherWhiteQueen;
	}

	public int getHigherWhiteQueen() {
		return higherWhiteQueen;
	}

	public void setLowerWhiteKing(int lowerWhiteKing) {
		this.lowerWhiteKing = lowerWhiteKing;
	}

	public int getLowerWhiteKing() {
		return lowerWhiteKing;
	}

	public void setHigherWhiteKing(int higherWhiteKing) {
		this.higherWhiteKing = higherWhiteKing;
	}

	public int getHigherWhiteKing() {
		return higherWhiteKing;
	}

	public void setLowerBlackPawns(int lowerBlackPawns) {
		this.lowerBlackPawns = lowerBlackPawns;
	}

	public int getLowerBlackPawns() {
		return lowerBlackPawns;
	}

	public void setHigherBlackPawns(int higherBlackPawns) {
		this.higherBlackPawns = higherBlackPawns;
	}

	public int getHigherBlackPawns() {
		return higherBlackPawns;
	}

	public void setLowerBlackRooks(int lowerBlackRooks) {
		this.lowerBlackRooks = lowerBlackRooks;
	}

	public int getLowerBlackRooks() {
		return lowerBlackRooks;
	}

	public void setHigherBlackRooks(int higherBlackRooks) {
		this.higherBlackRooks = higherBlackRooks;
	}

	public int getHigherBlackRooks() {
		return higherBlackRooks;
	}

	public void setLowerBlackKnights(int lowerBlackKnights) {
		this.lowerBlackKnights = lowerBlackKnights;
	}

	public int getLowerBlackKnights() {
		return lowerBlackKnights;
	}

	public void setHigherBlackKnights(int higherBlackKnights) {
		this.higherBlackKnights = higherBlackKnights;
	}

	public int getHigherBlackKnights() {
		return higherBlackKnights;
	}

	public void setLowerBlackBishops(int lowerBlackBishops) {
		this.lowerBlackBishops = lowerBlackBishops;
	}

	public int getLowerBlackBishops() {
		return lowerBlackBishops;
	}

	public void setHigherBlackBishops(int higherBlackBishops) {
		this.higherBlackBishops = higherBlackBishops;
	}

	public int getHigherBlackBishops() {
		return higherBlackBishops;
	}

	public void setLowerBlackQueen(int lowerBlackQueen) {
		this.lowerBlackQueen = lowerBlackQueen;
	}

	public int getLowerBlackQueen() {
		return lowerBlackQueen;
	}

	public void setHigherBlackQueen(int higherBlackQueen) {
		this.higherBlackQueen = higherBlackQueen;
	}

	public int getHigherBlackQueen() {
		return higherBlackQueen;
	}

	public void setLowerBlackKing(int lowerBlackKing) {
		this.lowerBlackKing = lowerBlackKing;
	}

	public int getLowerBlackKing() {
		return lowerBlackKing;
	}

	public void setHigherBlackKing(int higherBlackKing) {
		this.higherBlackKing = higherBlackKing;
	}

	public int getHigherBlackKing() {
		return higherBlackKing;
	}

	/*
	public BoardModel( int[] board )
	{
		this.board = board;
	}
	*/

	public BoardModel( 	
		int lowerWhitePawns, 
		int higherWhitePawns, 
		int lowerWhiteRooks,
		int higherWhiteRooks,
		int lowerWhiteKnights,
		int higherWhiteKnights,
		int lowerWhiteBishops,
		int higherWhiteBishops,
		int lowerWhiteQueen,
		int higherWhiteQueen,
		int lowerWhiteKing,
		int higherWhiteKing,
	
		// Black
		int lowerBlackPawns,
		int higherBlackPawns,
		int lowerBlackRooks,
		int higherBlackRooks,
		int lowerBlackKnights,
		int higherBlackKnights,
		int lowerBlackBishops,
		int higherBlackBishops,
		int lowerBlackQueen,
		int higherBlackQueen,
		int lowerBlackKing,
		int higherBlackKing
	)
	{
		this.lowerWhitePawns = lowerWhitePawns; 
		this.higherWhitePawns = higherWhitePawns; 
		this.lowerWhiteRooks = lowerWhiteRooks;
		this.higherWhiteRooks =higherWhiteRooks;
		this.lowerWhiteKnights = lowerWhiteKnights;
		this.higherWhiteKnights = higherWhiteKnights;
		this.lowerWhiteBishops = lowerWhiteBishops;
		this.higherWhiteBishops = higherWhiteBishops;
		this.lowerWhiteQueen = lowerWhiteQueen;
		this.higherWhiteQueen = higherWhiteQueen;
		this.lowerWhiteKing = lowerWhiteKing;
		this.higherWhiteKing = higherWhiteKing;
	
		// Black
		this.lowerBlackPawns = lowerBlackPawns;
		this.higherBlackPawns = higherBlackPawns;
		this.lowerBlackRooks = lowerBlackRooks;
		this.higherBlackRooks = higherBlackRooks;
		this.lowerBlackKnights = lowerBlackKnights;
		this.higherBlackKnights = higherBlackKnights;
		this.lowerBlackBishops = lowerBlackBishops;
		this.higherBlackBishops = higherBlackBishops;
		this.lowerBlackQueen = lowerBlackQueen;
		this.higherBlackQueen = higherBlackQueen;
		this.lowerBlackKing = lowerBlackKing;
		this.higherBlackKing = higherBlackKing;
	}

	
	/*
	public int[] getBoard() { return this.board; }
	
	public void setBoard(int [] board) 
	{
		this.board = board;
	}
	*/
	
	
}

