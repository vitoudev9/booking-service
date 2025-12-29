package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.Appointment;

public interface AppointmentRepository extends JpaRepository<Appointment, String> {
}
