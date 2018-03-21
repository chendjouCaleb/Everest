package org.everest.packageWithFactory;

import org.everest.core.dic.decorator.Bean;

@org.everest.decorator.Factory
public class FactoryClass {

    @Bean
    public ClassOne classOne(){
        return new ClassOne();
    }

    @Bean
    public ClassTwo classTwo(){
        return new ClassTwo();
    }

    @Bean("tn")
    public ClassTree classTree(){
        return new ClassTree();
    }
}
