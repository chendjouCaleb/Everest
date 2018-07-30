package org.everest.utils;

import org.junit.Test;

import static org.junit.Assert.*;

public class StringUtilsTest {
    @Test
    public void lowerFirst(){
        assertEquals("bonjour", StringUtils.lowerFist("Bonjour"));
    }

    @Test
    public void ucFirst(){
        assertEquals("Bbb", StringUtils.ucFist("bbb"));
    }

    @Test
    public void addSlashBefore_Without_Slash(){
        assertEquals("/str", StringUtils.addSlashBefore("str"));
    }

    @Test
    public void addSlashBefore_With_Slash(){
        assertEquals("/str", StringUtils.addSlashBefore("/str"));
    }

    @Test
    public void removeSlashBefore_Without_Slash(){
        assertEquals("str", StringUtils.removeSlashBefore("str"));
    }

    @Test
    public void removeSlashBefore_With_Slash(){
        assertEquals("str", StringUtils.removeSlashBefore("/str"));
    }

    @Test
    public void removeSlashAfter_Without_Slash(){
        assertEquals("str", StringUtils.removeSlashAfter("str"));
    }

    @Test
    public void removeSlashAfter_With_Slash(){
        assertEquals("str", StringUtils.removeSlashAfter("str/"));
    }

    @Test
    public void test_Trim(){
        assertEquals("str", StringUtils.trim("/str/", "/"));
    }
}