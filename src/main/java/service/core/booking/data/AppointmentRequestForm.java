package service.core.booking.data;

import lombok.Data;

import java.time.OffsetDateTime;

@Data
public class AppointmentRequestForm {
    private String serviceId;
    private String employeeId;
    private OffsetDateTime startTime;
    private CustomerData customer;
}
