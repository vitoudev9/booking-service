package service.core.booking.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import service.core.booking.dto.AppointmentRequestForm;
import service.core.booking.model.Appointment;
import service.core.booking.model.Customer;
import service.core.booking.model.Employee;
import service.core.booking.model.LapetiteService;
import service.core.booking.repository.AppointmentRepository;
import service.core.booking.repository.CustomerRepository;
import service.core.booking.repository.EmployeeRepository;
import service.core.booking.repository.LapetiteServiceRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final LapetiteServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;

    @Transactional
    public Appointment bookNewAppointment(AppointmentRequestForm requestForm) {

        LapetiteService service = serviceRepository.findById(requestForm.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));

        Employee employee = employeeRepository.findById(requestForm.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Customer customer = new Customer();
        customer.setFullName(requestForm.getCustomerFullName());
        customer.setPhone(requestForm.getCustomerPhoneNumber());

        final LocalDateTime startTime = requestForm.getStartTime();
        final LocalDateTime endTime = startTime.plusMinutes(service.getDuration());

        Appointment newAppointment = new Appointment();
        newAppointment.setLapetiteService(service);
        newAppointment.setCustomer(customer);
        newAppointment.setEmployee(employee);
        newAppointment.setStartTime(startTime);
        newAppointment.setEndTime(endTime);

        return appointmentRepository.save(newAppointment);
    }
}
