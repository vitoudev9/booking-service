package service.core.booking.data;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class LapetiteServiceData {
    private Long id;
    private String name;
    private int duration;
    private BigDecimal price;
}
