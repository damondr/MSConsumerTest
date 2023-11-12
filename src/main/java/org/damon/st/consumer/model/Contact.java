package org.damon.st.consumer.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "t_contact")
public class Contact {
    @EmbeddedId
    private ContactKey id;

    @Column(name="contact_type", insertable=false, updatable=false)
    @NotEmpty(message = "Type is required")
    private String type;

    @Column(name="contact_value")
    @NotEmpty(message = "Value is required")
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false)
    private User user;
}