package com.duoc.backend.care;

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
class CareServiceTest {

    @Mock
    private CareRepository careRepository;

    @InjectMocks
    private CareService careService;

    @Test
    void testGetAllCares() {
        Care care = new Care("Baño", 15000.0);

        when(careRepository.findAll())
                .thenReturn(Arrays.asList(care));

        var result = careService.getAllCares();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(careRepository).findAll();
    }

    @Test
    void testGetCareById() {
        Care care = new Care("Baño", 15000.0);
        care.setId(1L);

        when(careRepository.findById(1L))
                .thenReturn(Optional.of(care));

        Care result = careService.getCareById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(careRepository).findById(1L);
    }

    @Test
    void testSaveCare() {
        Care care = new Care("Baño", 15000.0);

        when(careRepository.save(care))
                .thenReturn(care);

        Care result = careService.saveCare(care);

        assertNotNull(result);
        assertEquals(care, result);
        verify(careRepository).save(care);
    }

    @Test
    void testDeleteCare() {
        careService.deleteCare(1L);

        verify(careRepository).deleteById(1L);
    }
}