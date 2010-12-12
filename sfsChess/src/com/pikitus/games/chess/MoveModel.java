package com.pikitus.games.chess;

public class MoveModel 
{
	public static final byte MOVE = 0;
	public static final byte CAPTURE = 1;
	public static final byte PAWN_MOVE_TWO = 1 << 1;
	public static final byte CASTLE = 1 << 2;
	public static final byte EN_PASSANT = 1 << 3;
	
	public String from;
	public String to;
	
	public byte type;
	
	public MoveModel()
	{
		this.type = 0;
		this.to = "";
		this.from = "";
	}
	
	public MoveModel( String from, String to )
	{
		this.type = 0;
		this.to = to;
		this.from = from;
	}
	
	public MoveModel( String from, String to, byte type )
	{
		this.type = type;
		this.to = to;
		this.from = from;
	}
}
