package service.core.booking.data;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.Instant;

@AllArgsConstructor
public class ApiResponse<T> {
    private HttpStatus status;
    private String message;
    private T data;
    private Instant timestamp;

    public ApiResponse(HttpStatus status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.timestamp = Instant.now();
    }
}
