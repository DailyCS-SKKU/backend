package cse.skku.edu.dailycs.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthCodeRequestDto {

    @NotNull(message = "Authorization code is required.")
    private String code;
}
