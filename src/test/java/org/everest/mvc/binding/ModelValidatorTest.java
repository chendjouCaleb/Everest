package org.everest.mvc.binding;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.Assert.*;

public class ModelValidatorTest {
    private Logger logger = LoggerFactory.getLogger(ModelValidatorTest.class);
    private ModelValidator modelValidator = new ModelValidator();

    @Before
    public void setUp() throws Exception {
        modelValidator.initialize(modelValidator.validatorFactory(modelValidator.constraintValidatorFactory()));
    }

    @Test
    public void validateModel() throws Exception {

    }

    @Test
    public void validate() throws Exception {
        Luminary luminary = new Luminary();
        luminary.setDistance(0);
        luminary.setName("li");

        Map<String, String> errors = modelValidator.validate(luminary);
        logger.info("Errors:" + errors);
    }

}