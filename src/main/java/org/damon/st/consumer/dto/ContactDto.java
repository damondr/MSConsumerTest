package org.damon.st.consumer.dto;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Data
@NoArgsConstructor
public class ContactDto {
    @NotEmpty(message = "Type is required")
    private String type;
    @NotEmpty(message = "Value is required")
    private String value;
}