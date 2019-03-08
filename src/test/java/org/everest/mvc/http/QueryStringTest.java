package org.everest.mvc.http;

import Everest.Http.QueryString;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.*;

class QueryStringTest {

    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @Test
    void CtorThrows_IfQueryDoesNotHaveLeadingQuestionMark()
    {
        // Act and Assert
        assertThrows(IllegalArgumentException.class, () -> new QueryString("hello"),
                "The leading '?' must be included for a non-empty query.");
    }

    @Test
    void CtorNullOrEmpty_Success()
    {
        QueryString query = new QueryString(null);
        assertFalse(query.hasValue());
        assertNull(query.value());

        query = new QueryString(StringUtils.EMPTY);
        assertFalse(query.hasValue());
        assertEquals(StringUtils.EMPTY, query.value());
    }

    @Test
    void CtorJustAQuestionMark_Success()
    {
        QueryString query = new QueryString("?");
        assertTrue(query.hasValue());
        assertEquals("?", query.value());
    }

    @Test
    void ToString_EncodesHash()
    {
        QueryString query = new QueryString("?Hello=Wor#ld");
        assertEquals("?Hello=Wor%23ld", query.toString());
    }


    @ParameterizedTest
    @MethodSource("CreateNameValue_Success_TestValues")
    public void CreateNameValue_Success(String name, String value, String expected)
    {
        QueryString query = QueryString.create(name, value);
        assertEquals(expected, query.value());
    }

    public static String[][] CreateNameValue_Success_TestValues(){
        return new String[][]{
                new String[]{"name", "value", "?name=value"},
                new String[] {"na me", "val ue", "?na+me=val+ue"},
                new String[] {"name", "", "?name="},
                new String[] {"name", null, "?name="},
                new String[] {"", "value", "?=value"},
                new String[] {"", "", "?="},
                new String[] {"", null, "?="}
        };
    }

    @Test
    void CreateFromList_Success()
    {
        Map<String, String> values = new TreeMap<>();
        values.put("key1", "value1");
        values.put("key2", "value2");
        values.put("key3", "value3");
        values.put("key4", null);
        values.put("key5", "");
        QueryString query = QueryString.createFromMap(values);
        assertEquals("?key1=value1&key2=value2&key3=value3&key4=&key5=", query.value());
    }

    @ParameterizedTest
    @MethodSource("AddQueryString_Success_TestValues")
    public void AddQueryString_Success(String query1, String query2, String expected)
    {
        QueryString q1 = new QueryString(query1);
        QueryString q2 = new QueryString(query2);
        assertEquals(expected, q1.add(q2).value());
    }

    static String[][] AddQueryString_Success_TestValues(){
            return new String[][]{
                   new String[]{null, null, null},
                    new String[]{"", "", ""},
                    new String[]{null, "?name2=value2", "?name2=value2"},
                    new String[]{"", "?name2=value2", "?name2=value2"},
                    new String[]{"?", "?name2=value2", "?name2=value2"},
                    new String[]{"?name1=value1", null, "?name1=value1"},
                    new String[]{"?name1=value1", "", "?name1=value1"},
                    new String[]{"?name1=value1", "?", "?name1=value1"},
                    new String[]{"?name1=value1", "?name2=value2", "?name1=value1&name2=value2"}

            };
    }

    @ParameterizedTest
    @MethodSource("AddNameValue_Success")
    void AddNameValue_Success(String query1, String name2, String value2, String expected)
    {
        QueryString q1 = new QueryString(query1);
        QueryString q2 = q1.add(name2, value2);
        assertEquals(expected, q2.value());
    }
    static String[][] AddNameValue_Success(){
        return new String[][]{
            new String[] {"", "", "", "?="},
            new String[] {"", "", null, "?="},
            new String[] {"?", "", "", "?="},
            new String[] {"?", "", null, "?="},
            new String[] {"?", "name2", "value2", "?name2=value2"},
            new String[] {"?", "name2", "", "?name2="},
            new String[] {"?", "name2", null, "?name2="},
            new String[] {"?name1=value1", "name2", "value2", "?name1=value1&name2=value2"},
            new String[] {"?name1=value1", "na me2", "val ue2", "?name1=value1&na+me2=val+ue2"},
            new String[] {"?name1=value1", "", "", "?name1=value1&="},
            new String[] {"?name1=value1", "", null, "?name1=value1&="},
            new String[] {"?name1=value1", "name2", "", "?name1=value1&name2="},
            new String[] {"?name1=value1", "name2", null, "?name1=value1&name2="}
        };
    }

    @Test
    void Get_Correct_Segment(){
        assertEquals("", new QueryString(null).segment());
        assertEquals("", new QueryString("").segment());
        assertEquals("", new QueryString("?").segment());
        assertEquals("name1=value1", new QueryString("?name1=value1").segment());
    }

    @Test
     void NotEquals_EmptyQueryStringAndNonNullQueryString()
    {
        // Arrange
        QueryString queryString = new QueryString("?foo=1");

        // Act and Assert
        assertNotEquals(queryString, QueryString.Empty);
    }
}