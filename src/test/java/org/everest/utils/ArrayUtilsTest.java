package org.everest.utils;


import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ArrayUtilsTest {
    @Test
    public void findFirstDuplicate_With_No_Duplicate() throws Exception {
        String[] strings = new String[]{ "boj", "fg", "fgh"};
        assertNull(ArrayUtils.findFirstDuplicate(strings));
    }

    @Test
    public void findFirstDuplicate_With_Duplicate() throws Exception {
        String[] strings = new String[]{ "boj", "fg", "fg", "fgh"};
        assertEquals("fg", ArrayUtils.findFirstDuplicate(strings));
    }

}