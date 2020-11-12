package be.teachngo.converter;

import be.teachngo.data.Availability;
import be.teachngo.dto.Appointment;

public interface AvailabilityConverter {

    Appointment convertToAppointment(Availability availability);
}
