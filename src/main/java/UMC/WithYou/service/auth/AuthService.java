package UMC.WithYou.service.auth;

import UMC.WithYou.domain.auth.RefreshToken;
import UMC.WithYou.domain.auth.UserInfo;
import UMC.WithYou.domain.member.Email;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.member.MemberType;
import UMC.WithYou.dto.auth.LoginRequest;
import UMC.WithYou.dto.auth.LoginResponse;
import UMC.WithYou.repository.auth.RefreshTokenRepository;
import UMC.WithYou.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final MemberRepository memberRepository;
    private final OAuth2ProviderService oAuth2ProviderService;
    private final RefreshTokenRepository refreshTokenRepository;
    private final TokenProvider tokenProvider;

    public LoginResponse authenticateOrRegisterUser(LoginRequest request) {
        // 외부 OAuth 공급자를 통해 사용자 정보를 가져옵니다.
        UserInfo userInfo = oAuth2ProviderService.getUserInfo(request.getProvider(), request.getAccessToken());
        Email emailObject = new Email(userInfo.getEmail());

        // 이메일을 기반으로 기존 사용자를 찾거나 새로 등록합니다.
        Member member = memberRepository.findByEmail(emailObject)
                .map(existingMember -> updateExistingMember(existingMember, userInfo))
                .orElseGet(() -> registerNewMember(userInfo));

        // JWT 토큰 발급
        String accessToken = tokenProvider.createToken(member.getEmail());
        RefreshToken refreshToken = tokenProvider.createRefreshToken(member.getEmail());

        // RefreshToken 저장
        refreshTokenRepository.save(refreshToken);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken.getValue())
                .build();
    }

    private Member updateExistingMember(Member existingMember, UserInfo userInfo) {
        // 기존 멤버 정보 업데이트 (필요한 경우)
        if (!existingMember.getName().equals(userInfo.getName())) {
            existingMember.changeName(userInfo.getName());
        }
        // 기타 필요한 정보 업데이트 로직 추가

        return memberRepository.save(existingMember);
    }

    private Member registerNewMember(UserInfo userInfo) {
        // 새 멤버 등록
        return memberRepository.save(Member.builder()
                .email(userInfo.getEmail())
                .name(userInfo.getName())
                .memberType(MemberType.BASIC_USER)
                .build());
    }
}
