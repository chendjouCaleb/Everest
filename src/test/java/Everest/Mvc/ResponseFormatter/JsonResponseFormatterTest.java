package Everest.Mvc.ResponseFormatter;

import Everest.Http.DefaultHttpContext;
import Everest.Http.DefaultHttpResponse;
import org.everest.mvc.service.JsonConverter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringWriter;

import static org.junit.jupiter.api.Assertions.*;

class JsonResponseFormatterTest {
    private JsonConverter jsonConverter;
    private JsonResponseFormatter formatter;

    @BeforeEach
    void setUp() {
        jsonConverter = new JsonConverter();
        formatter = new JsonResponseFormatter(jsonConverter);
    }

    @Test
    void writeResponseBody() {
        Person person = new Person("chendjou", "caleb", 22);
        DefaultHttpContext httpContext = new DefaultHttpContext();

        ResponseFormatContext formatContext = new ResponseFormatContext(httpContext, person);
        formatter.writeResponseBody(formatContext);

        assertEquals(jsonConverter.toJSON(person), httpContext.getResponse().writer().toString());
    }
}

class Person{
    private String name;
    private String surname;
    private int age;

    public Person(String name, String surname, int age) {
        this.name = name;
        this.surname = surname;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}