package com.pikitus.login.handlers;

import java.util.ArrayList;
import java.util.List;

import models.RandomGameModel;

import com.smartfoxserver.v2.SmartFoxServer;
import com.smartfoxserver.v2.api.CreateRoomSettings.RoomExtensionSettings;
import com.smartfoxserver.v2.api.ISFSGameApi;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.SFSRoomRemoveMode;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.entities.match.BoolMatch;
import com.smartfoxserver.v2.entities.match.MatchExpression;
import com.smartfoxserver.v2.entities.match.RoomProperties;
import com.smartfoxserver.v2.entities.match.StringMatch;
import com.smartfoxserver.v2.entities.variables.RoomVariable;
import com.smartfoxserver.v2.entities.variables.SFSRoomVariable;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.exceptions.SFSJoinRoomException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;
import com.smartfoxserver.v2.game.CreateSFSGameSettings;

public class RandomGameHandler extends BaseClientRequestHandler
{
	static volatile int mNextGameID = 1;
	private RandomGameModel mRandomGameModel = new RandomGameModel();
	
    @Override
    public void handleClientRequest(User user, ISFSObject params)
    {
    	mRandomGameModel = (RandomGameModel) params.getClass("JOIN_RANDOM_ROOM");
    	if ( ! findGame( user ) ) 
    	{
    		createRoom( user );
    	}
    }
    
    public void createRoom(User user)
    {
    	// Get the game api and zone
    	ISFSGameApi gameApi = SmartFoxServer.getInstance().getAPIManager().getGameApi();
    	Zone zone = getParentExtension().getParentZone();

    	// Create a difficult variable for the room
        List<RoomVariable> roomVariablelist = new ArrayList<RoomVariable>();
        roomVariablelist.add(new SFSRoomVariable("gameType", mRandomGameModel.getGameType(), false, true, false));
        roomVariablelist.add(new SFSRoomVariable("gameDifficulty", mRandomGameModel.getDifficulty(), false, true, false));
        roomVariablelist.add(new SFSRoomVariable("gameArea", mRandomGameModel.getArea(), false, true, false));

        // Create the extension settings
        //RoomExtensionSettings extensionSetting = new RoomExtensionSettings("sfsGo", "com.pikitus.games.go.SFSGo");
        RoomExtensionSettings extensionSetting = new RoomExtensionSettings(mRandomGameModel.getGameType(), mRandomGameModel.getGameExtension());
    	
        // Create the game settings
        CreateSFSGameSettings roomSettings = new CreateSFSGameSettings();
        roomSettings.setName("Game" + mNextGameID++); 
        roomSettings.setInvitationExpiryTime(30); 
        roomSettings.setDynamic(true);
        roomSettings.setGame(true);
        roomSettings.setGamePublic(true); 
        roomSettings.setMinPlayersToStartGame(1); 
        roomSettings.setMaxUsers(2); 
        roomSettings.setMaxVariablesAllowed(10); 
        roomSettings.setMaxSpectators(0);
        roomSettings.setRoomVariables(roomVariablelist);
        roomSettings.setExtension(extensionSetting); 
        roomSettings.setAutoRemoveMode(SFSRoomRemoveMode.WHEN_EMPTY); 
        
        try 
        {
        	trace("creating the game room");
        	gameApi.createGame(zone, roomSettings, user);
		} 
        catch (SFSCreateRoomException e) 
		{
			e.printStackTrace();
		}
    }  
    
    public boolean findGame(User user)
    {
    	Zone zone = getParentExtension().getParentZone();
    	
    	MatchExpression exp = new MatchExpression(RoomProperties.IS_GAME, BoolMatch.EQUALS, true)
    		.and(RoomProperties.HAS_FREE_PLAYER_SLOTS, BoolMatch.EQUALS, true)
    		.and("gameType", StringMatch.EQUALS, mRandomGameModel.getGameType())
    		.and("gameDifficulty", StringMatch.EQUALS, mRandomGameModel.getDifficulty())
    		.and("gameArea", StringMatch.EQUALS, mRandomGameModel.getArea());
    	
    	// Get a list of rooms
    	List<Room> joinableRooms = getApi().findRooms(zone.getRoomList(), exp, 0);
    	if ( joinableRooms.size() == 0)
    	{
    		trace("No games found");
    		return false;
    	}

    	// Try to join the room
    	try 
    	{
    		trace("Joining the first game");
			getApi().joinRoom(user, joinableRooms.get(0));
		} 
    	catch (SFSJoinRoomException e) 
    	{
			e.printStackTrace();
    		return false;
		}
    	return true;
    }
}
