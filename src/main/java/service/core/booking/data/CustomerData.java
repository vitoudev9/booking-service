package service.core.booking.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class CustomerData {
    private String id;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private boolean existing;
    private String fullName;
    private String phoneNumber;
}
