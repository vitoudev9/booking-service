package service.core.booking.data;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestForm {
    private Long serviceId;
    private Long employeeId;
    private LocalDateTime startTime;
    private CustomerData customer;
}
