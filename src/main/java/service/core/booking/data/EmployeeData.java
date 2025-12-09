package service.core.booking.data;

import lombok.Data;

@Data
public class EmployeeData {
    private Long id;
    private String fullName;
    private String specialty;
    private boolean active;
}
