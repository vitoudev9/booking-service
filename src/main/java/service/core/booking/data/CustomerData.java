package service.core.booking.data;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class CustomerData {
    @JsonIgnore
    private boolean existing;
    private String fullName;
    private String phoneNumber;
}
