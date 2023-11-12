package org.damon.st.consumer.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContactKey implements Serializable {

    @Column(name = "user_id")
    private Long userId;

    @Column(name="contact_type")
    @NotEmpty(message = "Type is required")
    private String type;
}