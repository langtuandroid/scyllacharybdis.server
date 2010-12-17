package com.pikitus.login.handlers;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.entities.Room;
import com.smartfoxserver.v2.entities.User;

import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

public class ZoneJoinEventHandler extends BaseServerEventHandler
{
    @Override
    public void handleServerEvent(ISFSEvent event) throws SFSException
    {
    	trace ("Joining the lobby");
        User theUser = (User) event.getParameter(SFSEventParam.USER);
         
        // Join the user
        Room lobby = getParentExtension().getParentZone().getRoomByName("The Lobby");
         
        if (lobby == null)
            throw new SFSException("The Lobby Room was not found! Make sure a Room called 'The Lobby' exists in the Zone to make this example work correctly.");
         
        getApi().joinRoom(theUser, lobby);
    }
}