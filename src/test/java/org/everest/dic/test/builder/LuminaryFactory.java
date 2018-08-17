package org.everest.dic.test.builder;

import org.everest.core.dic.decorator.Bean;
import org.everest.decorator.Instance;
import org.everest.dic.test.repository.GalaxyRepository;
import org.everest.dic.test.repository.NebularRepository;
import org.everest.dic.test.service.*;
import org.everest.mvc.variableResolver.decorator.Component;

@Instance
public class LuminaryFactory {

    @Bean
    public IGalaxyService galaxyService(GalaxyRepository galaxyRepository){
        return new GalaxyService();
    }



    @Instance
    public INebularService nebularService(GalaxyRepository galaxyRepository, NebularRepository nebularRepository){
        System.out.println("Je viens de me faire exécuter");
        return new NebularService();
    }

    @Instance
    public IPlanetService planetService(){
        return new PlanetService();
    }
}
