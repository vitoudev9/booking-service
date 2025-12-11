package service.core.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import service.core.booking.model.AppUser;

import java.util.Optional;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
