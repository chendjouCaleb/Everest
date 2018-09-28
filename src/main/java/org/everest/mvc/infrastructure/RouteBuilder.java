package org.everest.mvc.infrastructure;

import org.everest.decorator.Instance;
import org.everest.mvc.component.UrlGenerator;
import org.everest.utils.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Instance
public class RouteBuilder {
    private List<RouteModel> routeModels = new ArrayList<>();

    public String url(String name, Map<String, String> params){
        RouteModel route = getRouteModel(name);
        UrlGenerator generator = new UrlGenerator(route.getMapping(), params);
        return generator.generate();
    }

    public String url(String name){
        RouteModel route = getRouteModel(name);
        UrlGenerator generator = new UrlGenerator(route.getMapping(), new HashMap<>());
        return generator.generate();
    }

    public String url(String name, Integer... params){
        RouteModel model = getRouteModel(name);
        String mapping = model.getMapping();
        for (Object param:params){
            mapping = mapping.replaceFirst("\\{[\\w]+\\}", param.toString());
        }
        return "/"+mapping;
    }

    public String url(String name, Object[] params){
        RouteModel model = getRouteModel(name);
        String mapping = model.getMapping();
        for (Object param:params){
            mapping = mapping.replaceFirst("\\{[\\w]+\\}", param.toString());
        }
        return "/"+mapping;
    }

    private RouteModel getRouteModel(String name) {
        RouteModel route  = routeModels.stream().filter(routeModel -> routeModel.getName().equals(name))
                .findFirst().orElse(null);
        if(route == null){
            route  = routeModels.stream().filter(routeModel -> routeModel.getName().equals(name + "#GET"))
                    .findFirst().orElse(null);
        }
        if(route == null){
            String regex = name.replaceFirst(".","\\.") + "#[A-Z]+";
            route  = routeModels.stream().filter(routeModel -> routeModel.getName().matches(regex))
                    .findFirst().orElse(null);
        }
        Assert.notNull(route, "Aucune route trouvé avec le nom '" + name + "' ou '" + name + "#(verbs)' n'a été trouvé");
        return route;
    }


    public void setRouteModels(List<RouteModel> routeModels) {
        this.routeModels = routeModels;
    }
}
