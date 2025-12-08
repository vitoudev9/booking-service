package service.core.booking.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AppointmentRequestForm {
    private Long serviceId;
    private Long employeeId;
    private String customerFullName;
    private String customerPhoneNumber;
    private LocalDateTime startTime;
}
