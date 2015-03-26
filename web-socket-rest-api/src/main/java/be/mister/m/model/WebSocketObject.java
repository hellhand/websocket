package be.mister.m.model;

import javax.ws.rs.core.MediaType;

/**
 * Created by marc on 16/02/15.
 */
public class WebSocketObject {

    private String mediaType;
    private String message;

    public WebSocketObject(String message) {
        this(message, MediaType.TEXT_HTML);
    }

    public WebSocketObject(String message, String mediaType) {
        this.message = message;
        this.mediaType = mediaType;
    }

    public String getMediaType() {
        return mediaType;
    }

    public String getMessage() {
        return message;
    }
}
