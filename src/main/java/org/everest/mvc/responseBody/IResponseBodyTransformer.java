package org.everest.mvc.responseBody;

import javax.validation.constraints.Null;
import java.util.List;

public interface IResponseBodyTransformer {
    String getBody(Object object);
    List<String> getMediaType();
}
