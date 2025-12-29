package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.EmployeeSchedule;

import java.util.List;

public interface EmployeeScheduleRepository extends JpaRepository<EmployeeSchedule, String> {
    List<EmployeeSchedule> findByEmployeeIdAndActiveTrue(String employeeId);
    List<EmployeeSchedule> findByDayOfWeekAndActiveTrue(int dayOfWeek);
}
