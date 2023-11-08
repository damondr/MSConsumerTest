package org.damon.st.consumer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@IdClass(ContactKey.class)
@Table(name = "t_contact")
public class Contact {
    @Id
    @Column(name="contact_type")
    @NotEmpty(message = "Type is required")
    private String type;

    @Column(name="contact_value")
    @NotEmpty(message = "Value is required")
    private String value;

    @Id
    @Column(name = "user_id")
    private Long user_id;

    @ManyToOne
    @MapsId("user_id")
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
}