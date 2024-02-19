package UMC.WithYou.service.member;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.dto.member.MemberResponse;
import UMC.WithYou.dto.member.NameRequest;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.service.S3Service;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final S3Service s3Service;
    public Member findMemberById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );
    }
    public MemberResponse getMember(Member member){
        return MemberResponse.builder()
                .imageUrl(member.getImageUrl())
                .name(member.getName()).build();
    }
    @Transactional
    public void updateImage(Member member, MultipartFile imageFile){
        String imageUrl = s3Service.uploadImg(imageFile);
        member.updateImage(imageUrl);
    }
    @Transactional
    public void updateName(Member member, NameRequest request){
        member.updateName(request.getName());
    }
}
