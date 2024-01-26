package UMC.WithYou.service.auth;

import UMC.WithYou.domain.auth.UserInfo;
import org.springframework.stereotype.Service;

@Service
public class OAuth2ProviderService {
    // 각 공급자 API와 통신하는 메소드 구현
    public UserInfo getUserInfo(String provider, String token) {
        switch (provider) {
            case "google":
                return getGoogleUserInfo(token);
            case "apple":
                return getAppleUserInfo(token);
            case "kakao":
                return getKakaoUserInfo(token);
            default:
                throw new IllegalArgumentException("Unsupported provider: " + provider);
        }
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
        return null;
    }
}
