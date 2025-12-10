package service.core.booking.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
    public ApiResponse<AppointmentResponseData> bookAppointment(@RequestBody AppointmentRequestForm requestForm) {
        try {
            final AppointmentResponseData newAppointment = appointmentService.bookNewAppointment(requestForm);
            return new ApiResponse<>(
                    HttpStatus.CREATED.value(),
                    "Successfully create an appointment",
                    newAppointment
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/bookings")
    public ApiResponse<List<AppointmentResponseData>> listAppointments() {
        try {
            final List<AppointmentResponseData> appointmentList = appointmentService.listAllAppointments();;
            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Successfully list appointments",
                    appointmentList);

        } catch (Exception e) {;
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/bookings/{id}")
    public ApiResponse<AppointmentResponseData> getAppointment(@PathVariable("id") Long id) {
        try {
            final AppointmentResponseData appointment = appointmentService.getAppointmentById(id);
            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    String.format("Successfully get appointment with Id: %s", id),
                    appointment
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}