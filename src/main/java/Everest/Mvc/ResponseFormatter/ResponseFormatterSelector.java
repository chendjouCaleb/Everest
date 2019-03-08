package Everest.Mvc.ResponseFormatter;

import org.everest.decorator.Instance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import java.util.*;

@Instance
public class ResponseFormatterSelector {
    private Map<String, IResponseFormatter> formatters = new HashMap<>();
    private Logger logger = LoggerFactory.getLogger(ResponseFormatterSelector.class);

    public void addFormatter(IResponseFormatter formatter){
        for(String mediaType: formatter.getMediaTypes()){
            logger.info("New formatter:[type= {}, class= {}]", mediaType, formatter.getClass().getSimpleName());
            formatters.put(mediaType, formatter);
        }
    }

    IResponseFormatter getFormatter(@Nonnull String contentType){
        IResponseFormatter formatter = formatters.get(contentType);
        if(formatter == null){
            throw new NoSuchElementException("Aucun transformateur trouvé pour le type de média: '" + contentType + "'");
        }
        return formatter;
    }

    Collection<IResponseFormatter> getFormatters(){
        return formatters.values();
    }
}
