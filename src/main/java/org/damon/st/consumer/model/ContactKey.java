package org.damon.st.consumer.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Data
public class ContactKey implements Serializable {
    private Long user_id;
    private String type;
}