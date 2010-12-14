package com.pikitus.login.handlers;

import com.smartfoxserver.v2.api.CreateRoomSettings;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;
import com.smartfoxserver.v2.entities.Zone;
import com.smartfoxserver.v2.entities.data.ISFSObject;
import com.smartfoxserver.v2.exceptions.SFSCreateRoomException;
import com.smartfoxserver.v2.extensions.BaseClientRequestHandler;

public class RandomGameHandler extends BaseClientRequestHandler
{
    @Override
    public void handleClientRequest(User user, ISFSObject params)
    {
        Zone zone = getParentExtension().getParentZone();
        CreateRoomSettings room = new CreateRoomSettings();
        boolean joinIt = true; 
        Room roomToLeave = user.getLastJoinedRoom();
        boolean fireClientEvent = true; 
        boolean fireServerEvent = true;
        try 
        {
			getApi().createRoom(zone, room, user, joinIt, roomToLeave , fireClientEvent, fireServerEvent);
		} 
        catch (SFSCreateRoomException e) 
		{
			e.printStackTrace();
		}
    }
}
