package UMC.WithYou.dto.auth;

import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

}
