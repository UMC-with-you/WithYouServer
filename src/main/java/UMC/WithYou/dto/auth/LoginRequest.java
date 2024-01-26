package UMC.WithYou.dto.auth;

import lombok.Data;

@Data
public class LoginRequest {
    private String accessToken;
    private String refreshToken;
}
