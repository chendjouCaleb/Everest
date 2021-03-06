package org.everest.mvc.binding;

import org.everest.core.dic.Container;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class ModelValidatorTest {
    private Logger logger = LoggerFactory.getLogger(ModelValidatorTest.class);
    private ModelValidator modelValidator;
    private BindingConfiguration configuration = new BindingConfiguration();

    @BeforeEach
    public void setUp() throws Exception {
        modelValidator = new ModelValidator(configuration.validatorFactory(new EverestConstraintValidatorFactory(new Container())));
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