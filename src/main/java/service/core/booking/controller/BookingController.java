package service.core.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.core.booking.data.ApiResponse;
import service.core.booking.data.AppointmentRequestForm;
import service.core.booking.data.AppointmentResponseData;
import service.core.booking.service.AppointmentService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final AppointmentService appointmentService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealthCheck() {
        return ResponseEntity.ok("System is up and running as ICE COOL!");
    }

    @PostMapping("/bookings/submit")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequestForm requestForm) {
        try {
            final AppointmentResponseData newAppointment = appointmentService.bookNewAppointment(requestForm);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong uh oh");
        }
    }

    @GetMapping("/bookings")
    public ApiResponse<List<AppointmentResponseData>> listAppointments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "startTime") String sortBy
    ) {
        try {
            //final Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy).descending());
            final List<AppointmentResponseData> appointmentList = appointmentService.listAllAppointments();
            //return new ResponseEntity<>(appointmentList, HttpStatus.OK);
            System.out.println(appointmentList);
            return new ApiResponse<>(
                    HttpStatus.OK,
                    "Successfully list appointments",
                    appointmentList);

        } catch (Exception e) {;
            throw new RuntimeException(e);
        }
    }

}