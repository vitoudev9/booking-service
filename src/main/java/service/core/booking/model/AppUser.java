package service.core.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import service.core.booking.utils.CustomIdGenerator;

@Table(name = "app_user")
@Entity
@EntityListeners(CustomIdGenerator.class)
@Getter
@Setter
@NoArgsConstructor
public class AppUser {

    @Id
    @CustomIdGenerator(prefix = "USR-")
    @Column(length = 50)
    private String id;

    @Column(nullable = false, unique = true, length = 100)
    private String username;

    @Column(nullable = false, length = 100)
    private String password;

    @Column(nullable = false, length = 50)
    private String role;
}
