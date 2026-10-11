package com.duoc.backend.medication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicationControllerTest {

    @Mock
    private MedicationService medicationService;

    @InjectMocks
    private MedicationController medicationController;

    @Test
    void testGetAllMedications() {
        Medication medication1 = new Medication("Antibiótico", 30.0);
        Medication medication2 = new Medication("Analgésico", 20.0);

        when(medicationService.getAllMedications())
                .thenReturn(Arrays.asList(medication1, medication2));

        var result = medicationController.getAllMedications();

        assertEquals(2, result.size());
        verify(medicationService).getAllMedications();
    }

    @Test
    void testGetMedicationById() {
        Medication medication = new Medication("Antibiótico", 30.0);

        when(medicationService.getMedicationById(1L))
                .thenReturn(medication);

        Medication result = medicationController.getMedicationById(1L);

        assertNotNull(result);
        assertEquals("Antibiótico", result.getName());
        verify(medicationService).getMedicationById(1L);
    }

    @Test
    void testSaveMedication() {
        Medication medication = new Medication("Antibiótico", 30.0);

        when(medicationService.saveMedication(medication))
                .thenReturn(medication);

        Medication result = medicationController.saveMedication(medication);

        assertNotNull(result);
        assertEquals("Antibiótico", result.getName());
        verify(medicationService).saveMedication(medication);
    }

    @Test
    void testDeleteMedication() {
        medicationController.deleteMedication(1L);

        verify(medicationService).deleteMedication(1L);
    }
}