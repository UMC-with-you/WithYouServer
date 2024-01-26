package UMC.WithYou.service.auth;

import UMC.WithYou.domain.auth.KakaoUserInfo;
import UMC.WithYou.domain.auth.UserInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Map;

@Service
public class OAuth2ProviderService {
    private final WebClient webClient;

    public OAuth2ProviderService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

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
        Map attributes = webClient.get()
                .uri("https://kapi.kakao.com/v2/user/me")
                .header("Authorization", "Bearer " + token)
                .retrieve()
                .bodyToMono(Map.class)
                .block(); // 리액티브 타입의 결과를 동기적으로 대기하고 결과를 받습니다.

        return new KakaoUserInfo(attributes);
    }
}
