package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {
}
