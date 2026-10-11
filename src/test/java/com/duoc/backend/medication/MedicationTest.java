package com.duoc.backend.medication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicationTest {

    @Test
    void testMedicationGettersAndSetters() {
        Medication medication = new Medication("Antibiotico", 30.0);

        medication.setId(1L);
        medication.setName("Antibiotico actualizado");
        medication.setCost(35.0);

        assertEquals(1L, medication.getId());
        assertEquals("Antibiotico actualizado", medication.getName());
        assertEquals(35.0, medication.getCost());
    }
}