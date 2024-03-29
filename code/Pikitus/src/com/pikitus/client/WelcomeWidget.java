package com.pikitus.client;

import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomeWidget extends Composite 
{
	private final VerticalPanel panel = new VerticalPanel();
	
    public WelcomeWidget() {
    	
    	HTML html = new HTML(
    			"This is <b>HTML</b>.  It will be interpreted as such if you specify "
    			+ "the <span style='font-family:fixed'>asHTML</span> flag.", true);

    	panel.add(html.asWidget());
        initWidget(panel);
    }
}
