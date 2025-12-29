package service.core.booking.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ServiceData {
    private String id;
    private String name;
    private int durationMinutes;
    private BigDecimal price;
    private String description;
    private boolean active;
}
