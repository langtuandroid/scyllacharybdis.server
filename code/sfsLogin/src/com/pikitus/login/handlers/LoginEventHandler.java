package com.pikitus.login.handlers;

import com.smartfoxserver.v2.core.ISFSEvent;
import com.smartfoxserver.v2.core.SFSEventParam;
import com.smartfoxserver.v2.exceptions.SFSErrorCode;
import com.smartfoxserver.v2.exceptions.SFSErrorData;
import com.smartfoxserver.v2.exceptions.SFSException;
import com.smartfoxserver.v2.exceptions.SFSLoginException;
import com.smartfoxserver.v2.extensions.BaseServerEventHandler;

public class LoginEventHandler extends BaseServerEventHandler 
{
	@Override
	public void handleServerEvent(ISFSEvent event) throws SFSException 
	{ 
		String name = (String) event.getParameter(SFSEventParam.LOGIN_NAME); 
        trace ("Hello " + name.toString());
		if (name.equals("Gonzo") || name.equals("Kermit")) 
		{
     
			// Create the error code to send to the client  
			// SFSErrorCode.LOGIN_BAD_USERNAME or SFSErrorCode.LOGIN_BAD_PASSWORD
			SFSErrorData errData = new SFSErrorData(SFSErrorCode.LOGIN_BAD_USERNAME);
			errData.addParameter(name);
 
			// Fire a Login exception
			throw new SFSLoginException("Gonzo and Kermit are not allowed in this Zone!", errData); 
		}
	} 
}
