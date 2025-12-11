package service.core.booking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import service.core.booking.data.AppointmentRequestForm;
import service.core.booking.data.AppointmentResponseData;
import service.core.booking.mapper.AppointmentMapper;
import service.core.booking.model.Appointment;
import service.core.booking.model.Customer;
import service.core.booking.model.Employee;
import service.core.booking.model.LapetiteService;
import service.core.booking.repository.AppointmentRepository;
import service.core.booking.repository.CustomerRepository;
import service.core.booking.repository.EmployeeRepository;
import service.core.booking.repository.LapetiteServiceRepository;
import service.core.booking.utils.ObjectMapperUtil;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;
    private final LapetiteServiceRepository serviceRepository;
    private final CustomerRepository customerRepository;
    private final EmployeeRepository employeeRepository;
    private final AppointmentMapper mapper;

    @Transactional(readOnly = true)
    public Page<AppointmentResponseData> listAllAppointments(Pageable pageable) {
        return appointmentRepository.findAll(pageable)
                .map(mapper::toDTO);
    }

    @Transactional(readOnly = true)
    public List<AppointmentResponseData> listAllAppointments() {
        return appointmentRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public AppointmentResponseData getAppointmentById(Long id) {
        return appointmentRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Appointment not found"));
    }

    @Transactional
    public AppointmentResponseData bookNewAppointment(AppointmentRequestForm requestForm) {

        final LapetiteService service = serviceRepository.findById(requestForm.getServiceId())
                .orElseThrow(() -> new EntityNotFoundException("Service not found"));
        final Employee employee = employeeRepository.findById(requestForm.getEmployeeId())
                .orElseThrow(() -> new EntityNotFoundException("Employee not found"));

        Customer customer = null;
        final boolean isExistingCustomer = requestForm.getCustomer().isExisting();
        final String customerFullName = requestForm.getCustomer().getFullName();
        final String customerPhoneNumber = requestForm.getCustomer().getPhoneNumber();

        if (isExistingCustomer) {
            if (StringUtils.hasLength(customerPhoneNumber) || StringUtils.hasLength(customerFullName)) {
                customer = customerRepository.findByFullNameOrPhoneNumber(customerFullName, customerPhoneNumber)
                        .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
            }
        } else {
            customer = new Customer();
            customer.setFullName(customerFullName);
            customer.setPhoneNumber(customerPhoneNumber);
        }

        final LocalDateTime startTime = requestForm.getStartTime();
        final LocalDateTime endTime = startTime.plusMinutes(service.getDuration());

        final Appointment newAppointment = new Appointment();
        newAppointment.setLapetiteService(service);
        newAppointment.setCustomer(customer);
        newAppointment.setEmployee(employee);
        newAppointment.setStartTime(startTime);
        newAppointment.setEndTime(endTime);
        return mapper.toDTO(appointmentRepository.save(newAppointment));
    }
}
