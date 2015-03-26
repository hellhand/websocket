package be.mister.m.services;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.Collection;

/**
 * Created by marc on 07/03/15.
 */
@Service
public class ClientServiceImpl implements ClientService {

    @Override
    public Collection<Session> getClients() {
        return clients.values();
    }

    @Override
    public void addClient(String sessionId, Session session) {
        clients.put(sessionId, session);
    }

    @Override
    public void removeClient(String sessionId) {
        clients.remove(sessionId);
    }
}
