package UMC.WithYou.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "accessToken is mandatory")
    private String accessToken;
    @NotBlank(message = "provider is mandatory")
    private String provider;
}