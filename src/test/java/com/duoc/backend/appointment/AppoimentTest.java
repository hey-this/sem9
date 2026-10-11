package com.duoc.backend.appointment;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class AppoimentTest {

    @Test
    void testAppointmentGettersAndSetters() {
        Appointment appointment = new Appointment();

        LocalDate date = LocalDate.of(2026, 10, 10);
        LocalTime time = LocalTime.of(15, 30);

        appointment.setId(1L);
        appointment.setDate(date);
        appointment.setTime(time);
        appointment.setReason("Control veterinario");
        appointment.setVeterinarian("Dr. Pérez");

        assertEquals(1L, appointment.getId());
        assertEquals(date, appointment.getDate());
        assertEquals(time, appointment.getTime());
        assertEquals("Control veterinario", appointment.getReason());
        assertEquals("Dr. Pérez", appointment.getVeterinarian());
    }
}