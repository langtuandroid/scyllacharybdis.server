package com.pikitus.login.models;

import com.smartfoxserver.v2.protocol.serialization.SerializableSFSType;

public class RandomGameModel implements SerializableSFSType
{
	private String mGameType;
	private String mGameExtension;
	private String mDifficulty;
	private String mArea;
	
	public void setGameType(String gameType) {
		mGameType = gameType;
	}
	
	public String getGameType() {
		return mGameType;
	}
	
	public void setGameExtension(String gameExtension) {
		mGameExtension = gameExtension;
	}
	
	public String getGameExtension() {
		return mGameExtension;
	}
	
	public void setDifficulty(String difficulty) {
		mDifficulty = difficulty;
	}
	
	public String getDifficulty() {
		return mDifficulty;
	}
	
	public void setArea(String area) {
		mArea = area;
	}
	
	public String getArea() {
		return mArea;
	}
	
	
}
