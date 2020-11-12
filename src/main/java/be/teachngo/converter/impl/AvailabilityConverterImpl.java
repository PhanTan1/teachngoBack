package be.teachngo.converter.impl;

import be.teachngo.converter.AvailabilityConverter;
import be.teachngo.data.Availability;
import be.teachngo.dto.Appointment;
import org.springframework.stereotype.Component;

@Component
public class AvailabilityConverterImpl implements AvailabilityConverter {
    @Override
    public Appointment convertToAppointment(Availability availability) {

        Appointment appointment = new Appointment();
        appointment.setId(availability.getId());
        appointment.setStartDate(availability.getStartTime());
        appointment.setEndDate(availability.getEndTime());
        appointment.setTitle("temporary");
        appointment.setLocation("Online");

        return appointment;

    }
}
