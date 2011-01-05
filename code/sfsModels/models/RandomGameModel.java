package models;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class RandomGameModel implements SerializableSFSType
{
	String gameType;
	String gameExtension;
	String difficulty;
	String area;
	
	public RandomGameModel()
	{
	}

	public RandomGameModel(String gameType, String gameExtension, String difficulty, String area)
	{
		this.gameType = gameType;
		this.gameExtension = gameExtension;
		this.difficulty = difficulty;
		this.area = area;
	}
	
	public String getGameType() 
	{
		return this.gameType;
	}

	public void setGameType(String gameType) 
	{
		this.gameType = gameType;
	}
	
	public String getGameExtension() 
	{
		return this.gameExtension;
	}

	public void setGameExtension(String gameExtension) 
	{
		this.gameExtension = gameExtension;
	}
	
	public String getDifficulty() 
	{
		return this.difficulty;
	}

	public void setDifficulty(String difficulty) 
	{
		this.difficulty = difficulty;
	}
	
	public String getArea() 
	{
		return this.area;
	}

	public void setArea(String area) 
	{
		this.area = area;
	}
}
