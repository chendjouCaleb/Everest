package org.everest.mvc.binding;
import org.joda.time.DateTime;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;


public class ModelBinderTest {
    private Logger logger = LoggerFactory.getLogger(ModelBinder.class);
    private ModelBinder modelBinder = new ModelBinder();

    @Test
    public void addConverter() throws Exception {
        String date = "12/12/1996 12:12:12";
        Object o = modelBinder.convert(date, DateTime.class);
        logger.info(o.toString());
    }

    @Test
    public void  bind_With_Simple_Class(){
        Map<String, Object> values = new HashMap<>();
        values.put("distance", "20");
        values.put("name", "voie lactée");

        Luminary luminary = modelBinder.bind(Luminary.class, values);
        Assert.assertEquals(20, luminary.getDistance());
        Assert.assertEquals("voie lactée", luminary.getName());
    }

    @Test
    public void bind_with_Complex_Class(){
        Map<String, Object> values = new HashMap<>();
        values.put("distance", new String[]{"20"});
        values.put("name", "voie lactée");
        values.put("stars", new String[]{"soleil", "VY Canis Majoris"});
        values.put("discoveryDate", "12/12/1996");

        Galaxy galaxy = modelBinder.bind(Galaxy.class, values);
        logger.info(galaxy.toString());
        Assert.assertEquals(20, galaxy.getDistance());
        Assert.assertEquals("voie lactée", galaxy.getName());
        Assert.assertArrayEquals(new String[]{"soleil", "VY Canis Majoris"}, galaxy.getStars());
        DateTime dateTime = new DateTime(1996,12,12,0,0,0);
        Assert.assertEquals(dateTime, galaxy.getDiscoveryDate());

    }

}