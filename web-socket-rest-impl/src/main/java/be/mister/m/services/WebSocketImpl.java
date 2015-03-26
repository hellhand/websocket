package be.mister.m.services;

import be.mister.m.converters.WebSocketModelConverter;
import be.mister.m.model.WebSocketObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.socket.server.standard.SpringConfigurator;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by marc on 03/02/15.
 */
@ServerEndpoint(value = "/web-socket", encoders = {WebSocketModelConverter.class}, configurator = SpringConfigurator.class)
public class WebSocketImpl implements WebSocket {

    private ClientService clientService;

    @Autowired
    public WebSocketImpl(ClientService clientService) {
        this.clientService = clientService;
    }

    @OnOpen
    public void onOpen(Session session) {
        try {
            session.getBasicRemote().sendObject(new WebSocketObject(Messages.CONNECTION_ESTABLISHED, MediaType.APPLICATION_JSON));
            clientService.addClient(session.getId(), session);
        }
        catch (EncodeException | IOException ex) {
            ex.printStackTrace();
        }
    }

    @OnClose
    public void onClose(Session session) {
        clientService.removeClient(session.getId());
    }

    @OnMessage
    public void onMessage(Session session, String message) {
        try {
            session.getBasicRemote().sendObject(new WebSocketObject(message));
        }
        catch (IOException | EncodeException ex) {
            ex.printStackTrace();
        }
    }
}
