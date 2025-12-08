package service.core.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import service.core.booking.dto.AppointmentRequestForm;
import service.core.booking.model.Appointment;
import service.core.booking.service.AppointmentService;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final AppointmentService appointmentService;

    @GetMapping("/health")
    public ResponseEntity<String> getHealthCheck() {
        return ResponseEntity.ok("System is up and running as ICE COOL!");
    }

    @PostMapping("/booking/submit")
    public ResponseEntity<?> bookAppointment(@RequestBody AppointmentRequestForm requestForm) {
        try {
            Appointment newAppointment = appointmentService.bookNewAppointment(requestForm);
            return new ResponseEntity<>(newAppointment, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Something went wrong uh oh");
        }
    }

}