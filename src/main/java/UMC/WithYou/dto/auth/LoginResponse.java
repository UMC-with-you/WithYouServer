package UMC.WithYou.dto.auth;

import lombok.Builder;
import lombok.Data;

@Data
public class LoginResponse {
    private String accessToken;

    private String refreshToken;

    @Builder
    public LoginResponse(String accessToken, String refreshToken) {
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }
}
