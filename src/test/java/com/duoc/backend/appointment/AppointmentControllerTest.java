package com.duoc.backend.appointment;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AppointmentControllerTest {

    @Mock
    private AppointmentService appointmentService;

    @InjectMocks
    private AppointmentController appointmentController;

    @Test
    void testGetAllAppointments() {
        Appointment appointment = new Appointment();

        when(appointmentService.getAllAppointments())
                .thenReturn(Arrays.asList(appointment));

        List<Appointment> result =
                appointmentController.getAllAppointments();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(appointmentService).getAllAppointments();
    }

    @Test
    void testGetAppointmentById() {
        Appointment appointment = new Appointment();
        appointment.setId(1L);

        when(appointmentService.getAppointmentById(1L))
                .thenReturn(appointment);

        Appointment result =
                appointmentController.getAppointmentById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(appointmentService).getAppointmentById(1L);
    }

    @Test
    void testSaveAppointment() {
        Appointment appointment = new Appointment();

        when(appointmentService.saveAppointment(appointment))
                .thenReturn(appointment);

        Appointment result =
                appointmentController.saveAppointment(appointment);

        assertNotNull(result);
        assertEquals(appointment, result);
        verify(appointmentService).saveAppointment(appointment);
    }

    @Test
    void testDeleteAppointment() {
        appointmentController.deleteAppointment(1L);

        verify(appointmentService).deleteAppointment(1L);
    }
}
