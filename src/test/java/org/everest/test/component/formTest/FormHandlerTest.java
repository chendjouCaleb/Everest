package org.everest.test.component.formTest;

import org.apache.commons.beanutils.BeanUtils;
import org.everest.component.form.FormHandler;
import org.everest.test.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FormHandlerTest {
    private FormHandler<User> formHandler;
    private User user = new User();

    private Map<String, Object> properties = new HashMap<>();
    List<String> books = new ArrayList<>(2);

    @Before
    public void init(){
        user.setId(10);

        properties.put("name", "Chendjou");
        properties.put("id", 100);
        properties.put("surname", "Caleb");
        properties.put("old", 17);
        String[] options = new String[]{"a", "B", "B"};

        books.add("clean code");
        books.add("clean architecture");
        properties.put("books", books);
        properties.put("options", options);

        formHandler = new FormHandler(properties, user);
    }

    @Test
    public void TestEqualityOfModelRefrence(){
        Assert.assertNotNull(user);
        Assert.assertEquals(formHandler.getModel(), user);
    }

    @Test
    public void TestEqualityOfModelProperties(){
        formHandler.handle();
        User model = formHandler.getModel();

        Assert.assertEquals(model.getName(), "Chendjou");
        Assert.assertEquals(model.getSurname(), "Caleb");
        Assert.assertEquals(model.getOld(), 17);
        Assert.assertEquals(model.getOptions(), new String[]{"a", "B", "B"});
        Assert.assertEquals(model.getBooks(), books);
    }

    @Test
    public void countErrorsTest(){
        formHandler.handle();
        formHandler.doValidation();

        Assert.assertEquals(formHandler.getErrors().size(), 2);
    }
    @Test
    public void testModel(){
        formHandler.handle();
        System.out.println(formHandler.getModel());
        formHandler.resetModel();
        System.out.println("Init: " + formHandler.getModel());
    }

    @Test
    public void SimpleTest(){
        try {
            BeanUtils.populate(user, properties);
            System.out.println(user);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

    }
}
