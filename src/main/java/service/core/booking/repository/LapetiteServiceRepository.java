package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.LapetiteService;

public interface LapetiteServiceRepository extends JpaRepository<LapetiteService, String> {
}
