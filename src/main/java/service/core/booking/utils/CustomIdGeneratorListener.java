package service.core.booking.utils;

import jakarta.persistence.PrePersist;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
public class CustomIdGeneratorListener {

    @PrePersist
    public void generateId(Object entity) {
        try {
            Field[] fields = entity.getClass().getDeclaredFields();
            for (Field field : fields) {
                if (field.isAnnotationPresent(CustomIdGenerator.class)) {
                    field.setAccessible(true);

                    // Only generate if ID is null
                    if (field.get(entity) == null) {
                        CustomIdGenerator annotation = field.getAnnotation(CustomIdGenerator.class);
                        String id = annotation.prefix() + generateTimestampId();
                        field.set(entity, id);
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error generating custom ID", e);
        }
    }

    private String generateTimestampId() {
        long timestamp = System.currentTimeMillis();
        String timestampPart = Long.toString(timestamp, 36).toUpperCase();

        if (timestampPart.length() >= 8) {
            return timestampPart.substring(timestampPart.length() - 8);
        }

        return String.format("%8s", timestampPart).replace(' ', '0');
    }
}
