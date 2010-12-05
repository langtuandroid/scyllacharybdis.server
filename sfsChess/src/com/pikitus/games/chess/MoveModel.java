package com.pikitus.games.chess;

public class MoveModel 
{
	public String from;
	public String to;
	
	public MoveModel()
	{
		
	}
	
	public MoveModel( String to, String from )
	{
		this.to = to;
		this.from = from;
	}
}
