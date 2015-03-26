package be.mister.m.services;

import javax.websocket.Session;
import java.util.Collection;
import java.util.HashMap;

/**
 * Created by marc on 07/03/15.
 */
public interface ClientService {

    HashMap<String, Session> clients = new HashMap<>();

    Collection<Session> getClients();

    void addClient(String sessionId, Session session);

    void removeClient(String sessionId);
}
