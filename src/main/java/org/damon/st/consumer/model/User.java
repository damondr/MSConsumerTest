package org.damon.st.consumer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.SourceType;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;
@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @Column(name = "id")
    @NotNull(message = "ID is required")
    private Long id;

    @Column(name = "first_name")
    @NotEmpty(message = "Name is required")
    private String name;

    @Column(name = "last_name")
    @NotEmpty(message = "Surname is required")
    private String surname;

    @Column(name = "age")
    @Min(value = 0, message = "Age must be at least 0")
    private int age;

    @Column(name = "updated_at")
    @UpdateTimestamp(source = SourceType.DB)
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Contact> contacts;
}