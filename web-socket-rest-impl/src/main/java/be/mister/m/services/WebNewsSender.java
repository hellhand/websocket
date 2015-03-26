package be.mister.m.services;

import be.mister.m.model.WebSocketObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.websocket.EncodeException;
import javax.websocket.Session;
import java.io.IOException;
import java.util.Collection;

/**
 * Created by marc on 14/02/15.
 */
@Service
public class WebNewsSender {

    @Autowired
    private ClientService clientService;

    @Scheduled(fixedRate = 5000)
    public void sendTextMessage() {
        try {
            Collection<Session> clients = clientService.getClients();
            for (Session session : clients) {
                session.getBasicRemote().sendObject(new WebSocketObject(Messages.HELLO_WORLD));
            }
        }
        catch (IOException | EncodeException e) {
            e.printStackTrace();
        }
    }
}
