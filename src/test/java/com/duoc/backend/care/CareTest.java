package com.duoc.backend.care;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CareTest {

    @Test
    void testCareGettersAndSetters() {
        Care care = new Care("Baño", 15000.0);

        care.setId(1L);
        care.setName("Baño completo");
        care.setCost(20000.0);

        assertEquals(1L, care.getId());
        assertEquals("Baño completo", care.getName());
        assertEquals(20000.0, care.getCost());
    }
}