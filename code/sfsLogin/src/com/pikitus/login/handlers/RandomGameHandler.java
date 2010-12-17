package com.pikitus.login.handlers;

import java.util.ArrayList;
import java.util.List;

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
	
    @Override
    public void handleClientRequest(User user, ISFSObject params)
    {
    	String game = params.getUtfString("game");
    	String difficulty = params.getUtfString("difficulty");
    	if ( ! findGame( user, difficulty, game ) ) 
    	{
    		createRoom( user, difficulty, game );
    	}
    }
    
    public void createRoom(User user, String difficulty, String game)
    {
    	// Get the game api and zone
    	ISFSGameApi gameApi = SmartFoxServer.getInstance().getAPIManager().getGameApi();
    	Zone zone = getParentExtension().getParentZone();

    	// Create a difficult variable for the room
        List<RoomVariable> roomVariablelist = new ArrayList<RoomVariable>();
        roomVariablelist.add(new SFSRoomVariable("difficulty", difficulty, false, true, false));
        roomVariablelist.add(new SFSRoomVariable("game", game, false, true, false));

        // Create the extension settings
        RoomExtensionSettings extensionSetting;
        if ( game == "sfsGo") 
        {
        	extensionSetting = new RoomExtensionSettings("sfsGo", "com.pikitus.games.go.SFSGo");
        } 
        else if ( game == "sfsChess" )
        {
        	extensionSetting = new RoomExtensionSettings("sfsChess", "com.pikitus.games.chess.SFSChess");
        } 
        else 
        {
        	extensionSetting = new RoomExtensionSettings("sfsChess", "com.pikitus.games.chess.SFSChess");
        }
    	
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
        	// Create the room
        	gameApi.createGame(zone, roomSettings, user);
		} 
        catch (SFSCreateRoomException e) 
		{
			e.printStackTrace();
		}
    }  
    
    public boolean findGame(User user, String difficulty, String game)
    {
    	Zone zone = getParentExtension().getParentZone();
    	
    	MatchExpression exp = new MatchExpression(RoomProperties.IS_GAME, BoolMatch.EQUALS, true)
    		.and(RoomProperties.HAS_FREE_PLAYER_SLOTS, BoolMatch.EQUALS, true)
    		.and("difficulty", StringMatch.EQUALS, difficulty)
    		.and("game", StringMatch.EQUALS, game);
    	List<Room> joinableRooms = getApi().findRooms(zone.getRoomList(), exp, 0);
    	if ( joinableRooms.size() == 0)
    	{
    		return false;
    	}
    	
    	try 
    	{
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
