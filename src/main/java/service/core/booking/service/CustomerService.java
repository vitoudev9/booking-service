package service.core.booking.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.core.booking.data.CustomerData;
import service.core.booking.mapper.CustomerMapper;
import service.core.booking.repository.CustomerRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper mapper;

    @Transactional(readOnly = true)
    public List<CustomerData> listAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    @Transactional(readOnly = true)
    public CustomerData getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }

    @Transactional(readOnly = true)
    public CustomerData getCustomerByFullNameOrPhoneNumber(String fullName, String phoneNumber) {
        return customerRepository.findByFullNameOrPhoneNumber(fullName, phoneNumber)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Customer not found"));
    }
}
