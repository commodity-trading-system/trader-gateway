package com.cts.fix;

import org.springframework.context.annotation.*;
import org.springframework.stereotype.Service;
import quickfix.*;
import quickfix.field.*;
import quickfix.fix44.NewOrderSingle;

import java.util.Date;

/**
 * Created by fy on 2017/6/2.
 */
public class FixInitiator {
    private static SocketInitiator initiator = null;

    private static SocketInitiator getInitiator(){return initiator;}

    public FixInitiator() throws ConfigError {
        if(initiator!=null){
            return;
        }
        SessionSettings settings = new SessionSettings("receiver.cfg");
        Application myApp = new FixApp();
        FileStoreFactory fileStoreFactory = new FileStoreFactory(settings);
        ScreenLogFactory screenLogFactory = new ScreenLogFactory(settings);
        DefaultMessageFactory msgFactory = new DefaultMessageFactory();
        initiator = new SocketInitiator(myApp, fileStoreFactory, settings, screenLogFactory, msgFactory);
        initiator.start();
    }

    public void startInitiator() throws ConfigError {initiator.start();}

    public void stopInitiator() throws ConfigError {initiator.stop();}

}
