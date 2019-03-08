package Everest.Http;

import org.junit.jupiter.api.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import static org.junit.jupiter.api.Assertions.*;


class QueryCollectionBuilderTest {

    @Test
    void null_And_Empty_QueryString(){
        QueryCollection collection = new QueryCollectionBuilder(null).parse().getCollection();
        assertEquals(0, collection.size());

        collection = new QueryCollectionBuilder("").parse().getCollection();
        assertEquals(0, collection.size());

        collection = new QueryCollectionBuilder("?").parse().getCollection();
        assertEquals(0, collection.size());

        collection = new QueryCollectionBuilder("?=").parse().getCollection();
        assertEquals(0, collection.size());
    }

    @Test
    void simpleKeyValue(){
        QueryCollection collection = new QueryCollectionBuilder("?name1=value1&name2=value2")
                .parse().getCollection();
        assertEquals(2, collection.size());

        assertTrue(collection.keySet().contains("name1"));
        assertTrue(collection.keySet().contains("name2"));

        assertEquals(1, collection.get("name1").size());
        assertEquals(1, collection.get("name2").size());

        assertEquals("value1", collection.get("name1").get(0));
        assertEquals("value2", collection.get("name2").get(0));
    }

    @Test
    void simpleKeyValueWithEncoding(){

        QueryCollection collection = new QueryCollectionBuilder("?name1=value1&na+me2=val+ue2")
                .parse().getCollection();
        assertEquals(2, collection.size());

        assertTrue(collection.keySet().contains("name1"));
        assertTrue(collection.keySet().contains("na me2"));
        assertFalse(collection.keySet().contains("na+me2"));

        assertEquals(1, collection.get("na me2").size());

        assertEquals("value1", collection.get("name1").get(0));
        assertEquals("val ue2", collection.get("na me2").get(0));
    }

    @Test
    void KeyWithMultipleValue(){
        QueryCollection collection = new QueryCollectionBuilder("?name1=value1&name2=value2&name2=value3")
                .parse().getCollection();
        assertEquals(2, collection.size());

        assertEquals(1, collection.get("name1").size());
        assertEquals(2, collection.get("name2").size());

        assertTrue(collection.get("name2").contains("value2"));
        assertTrue(collection.get("name2").contains("value3"));
    }

    @Test
    void key_Without_value(){
        QueryCollection collection = new QueryCollectionBuilder("?name1=value1&name2=")
                .parse().getCollection();

        assertEquals(2, collection.size());
        assertTrue(collection.containsKey("name2"));
        assertEquals(0, collection.get("name2").size());
    }

    @Test
    void Equals_Symbol_WithoutKeyValue(){
        QueryCollection collection = new QueryCollectionBuilder("?name1=value1&=")
                .parse().getCollection();
        assertEquals(1, collection.size());
    }

    @Test
    void parse() {
        new QueryCollectionBuilder("?=").parse();
        new QueryCollectionBuilder("?name2=value2").parse();
        new QueryCollectionBuilder("?name2=").parse();
        new QueryCollectionBuilder("?name1=value1&name2=value2").parse();
        new QueryCollectionBuilder("?name1=value1&na+me2=val+ue2").parse();
        new QueryCollectionBuilder("?name1=value1&=").parse();
        new QueryCollectionBuilder("?name1=value1&=").parse();
        new QueryCollectionBuilder("?name1=value1&name2=").parse();
        new QueryCollectionBuilder("?name1=value1&name2=").parse();
    }
}