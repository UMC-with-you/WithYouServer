package UMC.WithYou.service.auth;

import UMC.WithYou.domain.auth.KakaoUserInfo;
import UMC.WithYou.domain.auth.UserInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class OAuth2ProviderService {
    private final RestTemplate restTemplate;

    @Value("${kakao.client-id}")
    private String kakaoClientId;

    @Value("${kakao.redirect-uri}")
    private String kakaoRedirectUri;

    // 각 공급자 API와 통신하는 메소드 구현
    public UserInfo getUserInfo(String provider, String token) {
        return switch (provider) {
            case "google" -> getGoogleUserInfo(token);
            case "apple" -> getAppleUserInfo(token);
            case "kakao" -> getKakaoUserInfo(token);
            default -> throw new IllegalArgumentException("Unsupported provider: " + provider);
        };
    }

    private UserInfo getGoogleUserInfo(String token) {
        // Google API를 호출하여 사용자 정보를 가져옵니다.
        return null;
    }

    private UserInfo getAppleUserInfo(String token) {
        // Apple API를 호출하여 사용자 정보를 가져옵니다.
        return null;
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

