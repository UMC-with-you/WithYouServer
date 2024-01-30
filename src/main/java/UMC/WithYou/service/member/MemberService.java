package UMC.WithYou.service.member;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );
    }
    public Member findByMemberIdToken(String token){

        return this.findMemberById(Long.parseLong(token));
    }
}
