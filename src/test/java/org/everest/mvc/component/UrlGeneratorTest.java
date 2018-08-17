package org.everest.mvc.component;

import org.junit.Test;

import static org.junit.Assert.*;

public class UrlGeneratorTest {

    @Test
    public void generateWithPathParam(){
        String model = "area/{areaId}/user/{userId}/";
        String url = "/area/10/user/20";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addPathParam("areaId", "10")
                .addPathParam("userId", "20");
        assertEquals(url, generator.generate());
    }

    @Test
    public void generateWithPathParam_And_Query_Path(){
        String model = "/area/{areaId}/user/{userId}";
        String url = "/area/10/user/20?year=2010&name=myName";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addPathParam("areaId", "10")
                .addPathParam("userId", "20")
                .addQueryParam("year", "2010")
                .addQueryParam("name", "myName");

        assertEquals(url, generator.generate());
    }

    @Test
    public void generateWithPathParam_And_Query_Path_And_Matrix_Path(){
        String model = "/area/{areaId}/user/{userId}";
        String url = "/area/10/user/20?year=2010&name=myName;country=england;city=london";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addPathParam("areaId", "10")
                .addPathParam("userId", "20")
                .addQueryParam("year", "2010")
                .addQueryParam("name", "myName")
                .addMatrixParam("country", "england")
                .addMatrixParam("city", "london");

        assertEquals(url, generator.generate());
    }

    @Test
    public void generateWithParam(){
        String model = "/area/{areaId}/user/{userId}";
        String url = "/area/10/user/20?year=2010&name=myName";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addParam("areaId", "10")
                .addParam("userId", "20")
                .addParam("year", "2010")
                .addParam("name", "myName");

        assertEquals(url, generator.generate());
    }

    @Test
    public void generateWithParam_And_Host(){
        String model = "/area/{areaId}/user/{userId}";
        String url = "localhost/area/10/user/20";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addParam("areaId", "10")
                .addParam("userId", "20")
                .host("localhost");

        assertEquals(url, generator.generate());
    }

    @Test
    public void generateWithParam_And_Host_And_PORT(){
        String model = "area/{areaId}/user/{userId}";
        String url = "localhost:50/area/10/user/20";
        UrlGenerator generator = new UrlGenerator(model);
        generator.addParam("areaId", "10")
                .addParam("userId", "20")
                .port(50)
                .host("localhost");

        UrlGenerator urlGenerator = new UrlGenerator("school/{id}")
                .addParam("id", "10")
                .host("localhost")
                .port(8080);
        System.out.println("URL: " + urlGenerator.generate());

        System.out.println(generator.generate());
        assertEquals(url, generator.generate());
    }

}