package com.duoc.backend.appointment;

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
class AppointmentServiceTest {

    @Mock
    private AppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();

        when(appointmentRepository.findAll())
                .thenReturn(Arrays.asList(appointment));

        Iterable<Appointment> result =
                appointmentService.getAllAppointments();

        assertNotNull(result);
        verify(appointmentRepository).findAll();
    }

    @Test
    void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentRepository.findById(1L))
                .thenReturn(Optional.of(appointment));

        Appointment result =
                appointmentService.getAppointmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(appointmentRepository).findById(1L);
    }

    @Test
    void testSaveAppointment() {
        Appointment appointment = new Appointment();

        when(appointmentRepository.save(appointment))
                .thenReturn(appointment);

        Appointment result =
                appointmentService.saveAppointment(appointment);

        assertNotNull(result);
        assertEquals(appointment, result);
        verify(appointmentRepository).save(appointment);
    }

    @Test
    void testDeleteAppointment() {
        appointmentService.deleteAppointment(1L);

        verify(appointmentRepository).deleteById(1L);
    }
}