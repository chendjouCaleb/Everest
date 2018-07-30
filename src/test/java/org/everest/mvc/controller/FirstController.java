package org.everest.mvc.controller;

import org.everest.mvc.decorator.HttpMapping;
import org.everest.mvc.filter.annatotion.FilterOne;

@FilterOne
@HttpMapping("/f")
public class FirstController {

    private void firstAction(){

    }

}
