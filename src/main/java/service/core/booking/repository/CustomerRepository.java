package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
