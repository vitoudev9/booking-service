package service.core.booking.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import service.core.booking.utils.CustomIdGenerator;

import java.time.OffsetDateTime;

@Table(name = "employee")
@Entity
@EntityListeners(CustomIdGenerator.class)
@Getter
@Setter
@NoArgsConstructor
public class Employee {

    @Id
    @CustomIdGenerator(prefix = "EMP-")
    @Column(length = 50)
    private String id;

    @Column(name = "full_name", nullable = false)
    private String fullName;

    @Column(name = "phone_number", nullable = false, unique = true, length = 50)
    private String phoneNumber;

    @Column(name = "specialization")
    private String specialization;

    @Column(nullable = false)
    private boolean active = true;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;
}
