package org.everest.mvc.infrastructure;

import org.everest.decorator.Instance;
import org.everest.exception.RouteNotFoundException;
import org.everest.mvc.context.RouteContext;
import org.everest.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Instance
public class RouteDispatcher {
    private Logger logger = LoggerFactory.getLogger(RouteDispatcher.class);
    public RouteModel getCalledRoute(List<RouteModel> routeModels, String url, String verb){
        for (RouteModel routeModel: routeModels){
            url = StringUtils.trim(url, "/");
            //logger.info("mapping: {}", routeModel.getMapping());
            if(isMatch(routeModel.getMapping(), url) && routeModel.getVerbs().toString().equalsIgnoreCase(verb)){
                return routeModel;
            }
        }
        throw new RouteNotFoundException("Aucune route correspondant à " + url + "#" + verb + " n'a été trouvée");
    }

    public RouteContext createRouteContext(RouteModel routeModel, String url){
        RouteContext routeContext = new RouteContext();
        routeContext.setParameterNames(extractParamsName(routeModel.getMapping()));
        routeContext.setParameters(routeParams(routeModel.getMapping(), url));

        return routeContext;

    }

    public List<String> extractParamsName(String route){
        List<String> names = new ArrayList<>();
        String regex = "\\{([\\w]+)\\}";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(route);
        while (m.find()){
            names.add(m.group(1));
        }
        return names;
    }

    public List<String> paramsValues(String route, String url){
        List<String> params = new ArrayList<>();

        String regex = routeToRegex(route);
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(url);
        m.find();
        int c = m.groupCount();
        for (int i = 1; i <= c; i++){
            params.add(m.group(i));
        }
        return params;
    }

    Map<String, String> routeParams(String route, String url){
        List<String> params = extractParamsName(route);
        List<String> values = paramsValues(route, url);
        String message = "Params = {";
        Map<String, String> data = new HashMap<>();
        for (int i = 0; i < params.size(); i++){
            message += "[" + params.get(i) + " = " +values.get(i) + "]";
            data.put(params.get(i), values.get(i));
        }
        logger.info(message + "}");
        return data;
    }

    public String routeToRegex(String route){
        String regex = "\\{[\\w]+\\}";
        String replacement = "([\\\\w]+)";
        return route.replaceAll(regex, replacement);
    }

    public boolean isMatch(String route, String url){
        return url.matches(routeToRegex(route));
    }
}
