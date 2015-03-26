package be.mister.m.jaxb;

import com.sun.jersey.api.view.Viewable;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.apache.velocity.tools.ToolManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;

import javax.annotation.PostConstruct;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.ext.MessageBodyWriter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
@Provider
public class VelocityProvider implements MessageBodyWriter<Object> {

    @Autowired
    @Qualifier(value = "velocityEngineFactory")
    private VelocityEngineFactoryBean engineFactory;

    @Autowired
    @Qualifier(value = "velocityToolManager")
    private ToolManager velocityToolManager;

    private VelocityEngine engine;

    @PostConstruct
    public void postConstruct() throws VelocityException, IOException {
        engine = engineFactory.createVelocityEngine();
    }

    @Override
    public long getSize(Object model, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return -1;
    }
    
    @Override
    public boolean isWriteable(Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType) {
        return mediaType.toString().equals(MediaType.TEXT_HTML) && type.isAssignableFrom(Viewable.class);
    }
    
    @Override
    public void writeTo(Object model, Class<?> type, Type genericType, Annotation[] annotations, MediaType mediaType,
            MultivaluedMap<String, Object> headers, OutputStream outputStream) throws IOException,
            WebApplicationException {
        try {
            Viewable viewable = (Viewable) model;
            VelocityContext context = new VelocityContext(velocityToolManager.createContext());

            Template template = engine.getTemplate(viewable.getTemplateName() + ".vm");
            Writer writer = new OutputStreamWriter(outputStream, "UTF-8");
            template.merge(context, writer);

            writer.flush();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}