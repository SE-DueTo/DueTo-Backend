package de.dueto.backend.model.registration;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.ObjectError;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegistrationResponseDTO {

    private boolean successful;
    private List<ObjectError> errorMessage;

}
