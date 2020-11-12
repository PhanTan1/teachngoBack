package be.teachngo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class Appointment {
    private String title;
    private Date startDate;
    private Date endDate;
    private Long id;
    private String location;
}
