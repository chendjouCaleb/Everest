package org.everest.dic.test.builder;

import org.everest.core.dic.decorator.Bean;
import org.everest.decorator.Instance;
import org.everest.dic.test.repository.IGalaxyRepository;
import org.everest.dic.test.repository.NebularRepository;
import org.everest.dic.test.service.*;

@Instance
public class LuminaryFactory {

    @Instance
    public IGalaxyService galaxyService(IGalaxyRepository galaxyRepository){
        return new GalaxyService();
    }



    @Instance
    public INebularService nebularService(IGalaxyRepository galaxyRepository, NebularRepository nebularRepository){
        System.out.println("Je viens de me faire exécuter");
        return new NebularService();
    }

    @Instance
    public IPlanetService planetService(){
        return new PlanetService();
    }
}
