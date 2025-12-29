package service.core.booking.data;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AppointmentResponseData {
    private String id;
    private CustomerData customer;
    private EmployeeData employee;
    private ServiceData service;
    private String status;
    private OffsetDateTime startTime;
    private OffsetDateTime endTime;
    private String notes;
    private boolean confirmed;
}
