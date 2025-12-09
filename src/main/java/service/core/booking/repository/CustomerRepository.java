package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> findByFullNameOrPhoneNumber(String fullName, String phoneNumber);
}
