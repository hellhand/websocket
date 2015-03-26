package be.mister.m.converters;

import be.mister.m.model.WebSocketObject;
import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

/**
 * Created by marc on 16/02/15.
 */
public class WebSocketModelConverter implements Encoder.Text<WebSocketObject> {

    @Override
    public String encode(WebSocketObject object) throws EncodeException {
        Gson gson = new Gson();
        return gson.toJson(object);
    }

    @Override
    public void init(EndpointConfig config) {
        System.out.println(config.getUserProperties());
    }

    @Override
    public void destroy() {

    }
}
