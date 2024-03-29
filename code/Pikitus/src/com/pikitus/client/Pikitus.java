package com.pikitus.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class Pikitus implements EntryPoint
{
	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() 
	{
		// set widget on "content" element
		RootPanel content = RootPanel.get();
        if (content != null) {
            content.add(new WelcomeWidget());
        }
	}
}
