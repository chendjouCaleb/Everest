package org.everest.dic.test.controller;

import org.everest.decorator.Instance;
import org.everest.dic.test.component.PulsarComponent;

import javax.inject.Inject;

public class LuminaryController {
    @Inject
    PulsarComponent pulsarComponent;
}
