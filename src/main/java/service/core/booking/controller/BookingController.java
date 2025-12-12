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
import service.core.booking.data.CustomerData;
import service.core.booking.service.AppointmentService;
import service.core.booking.service.CustomerService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookingController {

    private final AppointmentService appointmentService;
    private final CustomerService customerService;

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
    public ApiResponse<AppointmentResponseData> getAppointmentById(@PathVariable("id") Long id) {
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

    @GetMapping("/customers")
    public ApiResponse<List<CustomerData>> listCustomers() {
        try {
            final List<CustomerData> customerList = customerService.listAllCustomers();
            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    "Successfully list customers",
                    customerList
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping("/customers/{id}")
    public ApiResponse<CustomerData> getCustomerById(@PathVariable("id") Long id) {
        try {
            final CustomerData customer = customerService.getCustomerById(id);
            return new ApiResponse<>(
                    HttpStatus.OK.value(),
                    String.format("Successfully get customer with Id: %s", id),
                    customer
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}