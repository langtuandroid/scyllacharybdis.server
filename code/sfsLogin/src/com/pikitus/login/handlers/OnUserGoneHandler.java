package com.pikitus.login.handlers;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

public class OnUserGoneHandler extends BaseServerEventHandler 
{
    @Override
	public void handleServerEvent(ISFSEvent event) throws SFSException
	{
    	//User user = (User) event.getParameter(SFSEventParam.USER);
	}
}
