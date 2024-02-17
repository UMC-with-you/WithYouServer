package UMC.WithYou.service.auth;

import UMC.WithYou.domain.auth.AppleUserInfo;
import UMC.WithYou.domain.auth.GoogleUserInfo;
import UMC.WithYou.domain.auth.KakaoUserInfo;
import UMC.WithYou.domain.auth.UserInfo;
import UMC.WithYou.dto.auth.LoginRequest;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuth2ProviderService {
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final AppleTokenVerifier appleTokenVerifier;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    // 각 공급자 API와 통신하는 메소드 구현
    public UserInfo getUserInfo(LoginRequest request) throws Exception {
        String token = request.getAccessToken();
        return switch (request.getProvider()) {
            case "google" -> getGoogleUserInfo(token);
            case "apple" -> getAppleUserInfo(token, request.getEmail(), request.getName());
            case "kakao" -> getKakaoUserInfo(token);
            default -> throw new IllegalArgumentException("Unsupported provider: " + request.getProvider());
        };
    }

    private UserInfo getGoogleUserInfo(String token) {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://www.googleapis.com/oauth2/v3/userinfo",
                HttpMethod.GET,
                entity,
                Map.class
        );
        Map<String, Object> attributes = response.getBody();
        return new GoogleUserInfo(attributes);
    }

    private UserInfo getAppleUserInfo(String token, String email, String name) throws Exception {
//        DecodedJWT jwt = appleTokenVerifier.verifyToken(token);
//
//        String userId = jwt.getSubject(); // 'sub' claim
        String userId = "apple-user-id";
        return new AppleUserInfo(userId, email, name);
    }

    private UserInfo getKakaoUserInfo(String token) {
        // Kakao API를 호출하여 사용자 정보를 가져옵니다.
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<Map> response = restTemplate.exchange(
                "https://kapi.kakao.com/v2/user/me",
                HttpMethod.GET,
                entity,
                Map.class
        );

        Map<String, Object> attributes = response.getBody();

        return new KakaoUserInfo(attributes);
    }
}

