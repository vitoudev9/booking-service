package service.core.booking.data;

import lombok.Data;

@Data
public class EmployeeData {
    private String id;
    private String fullName;
    private String phoneNumber;
    private String specialization;
    private boolean active;
}
