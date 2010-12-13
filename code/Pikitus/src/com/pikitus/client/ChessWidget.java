package com.pikitus.client;

import pl.rmalinowski.gwt2swf.client.ui.SWFWidget;
import com.google.gwt.user.client.ui.Composite;

public class ChessWidget extends Composite 
{
    public ChessWidget() 
    {
		SWFWidget swfWidget = new SWFWidget("chess.swf", 800, 600);
        initWidget(swfWidget);
    }
}
