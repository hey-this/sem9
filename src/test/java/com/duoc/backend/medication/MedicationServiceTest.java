package com.duoc.backend.medication;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicationServiceTest {

    @Mock
    private MedicationRepository medicationRepository;

    @InjectMocks
    private MedicationService medicationService;

    @Test
    void testGetAllMedications() {
        Medication medication1 = new Medication("Antibiótico", 30.0);
        Medication medication2 = new Medication("Analgésico", 20.0);

        when(medicationRepository.findAll())
                .thenReturn(Arrays.asList(medication1, medication2));

        var result = medicationService.getAllMedications();

        assertEquals(2, result.size());
        verify(medicationRepository).findAll();
    }

    @Test
    void testGetMedicationById() {
        Medication medication = new Medication("Antibiótico", 30.0);

        when(medicationRepository.findById(1L))
                .thenReturn(Optional.of(medication));

        Medication result = medicationService.getMedicationById(1L);

        assertNotNull(result);
        assertEquals("Antibiótico", result.getName());
        verify(medicationRepository).findById(1L);
    }

    @Test
    void testSaveMedication() {
        Medication medication = new Medication("Antibiótico", 30.0);

        when(medicationRepository.save(medication))
                .thenReturn(medication);

        Medication result = medicationService.saveMedication(medication);

        assertNotNull(result);
        assertEquals("Antibiótico", result.getName());
        verify(medicationRepository).save(medication);
    }

    @Test
    void testDeleteMedication() {
        medicationService.deleteMedication(1L);

        verify(medicationRepository).deleteById(1L);
    }
}
