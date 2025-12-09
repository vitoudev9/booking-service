package service.core.booking.data;

import lombok.Data;

@Data
public class AppointmentResponseData {
    private Long id;
    private CustomerData customer;
    private EmployeeData employee;
    private LapetiteServiceData service;
    private String status;
    private String startTime;
    private String endTime;
}
