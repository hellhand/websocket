package be.mister.m.websocket;

import be.mister.m.MessageHandler;

import javax.websocket.ClientEndpoint;
import javax.websocket.CloseReason;
import javax.websocket.ContainerProvider;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;

/**
 * Created by marc on 26/03/15.
 */
@ClientEndpoint
public class WebsocketClientEndpoint {

    private Session userSession;
    private MessageHandler messageHandler;
    private URI uri;
    private WebSocketContainer container;

    public WebsocketClientEndpoint(MessageHandler messageHandler) {
        this.container = ContainerProvider.getWebSocketContainer();
        this.messageHandler = messageHandler;
    }

    public void connect(URI endpointURI) {
        try {
            this.uri = endpointURI;
            container.connectToServer(this, uri);
        }
        catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void sendMessage(String message) {
        userSession.getAsyncRemote().sendText(message);
    }

    public void close() throws IOException {
        userSession.close();
    }

    @OnOpen
    public void onOpen(Session userSession) {
        System.out.println("opening websocket");
        this.userSession = userSession;
    }

    @OnClose
    public void onClose(Session userSession, CloseReason reason) {
        System.out.println("closing websocket");
        this.userSession = null;
    }

    @OnMessage
    public void onMessage(String message) {
        if (this.messageHandler != null) {
            this.messageHandler.handleMessage(message);
        }
    }
}
