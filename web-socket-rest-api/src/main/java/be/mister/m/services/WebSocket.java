package be.mister.m.services;

import javax.websocket.Session;

/**
 * Created by marc on 03/02/15.
 */
public interface WebSocket {

    public void onOpen(Session session);

    public void onClose(Session session);

    public void onMessage(Session session, String message);
}
