package UMC.WithYou.service.cloud;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.CloudConverter;
import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.repository.cloud.CloudMediaRepository;
import UMC.WithYou.repository.cloud.CloudRepository;
import UMC.WithYou.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CloudServiceImpl implements CloudService{
    private final MemberRepository memberRepository;
    private final CloudRepository cloudRepository;
    private final CloudMediaRepository cloudMediaRepository;

    @Override
    @Transactional
    public Cloud createCloud(CloudRequestDTO.JoinDto request){ //date, pictures
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Cloud cloud = cloudRepository.findByMember(member)
                .orElseGet(() -> {
                    Cloud newCloud = CloudConverter.toChangeCloud(member);
                    return cloudRepository.save(newCloud);
                });

        pictureSave(request,cloud);
        return cloud;
    }

    public static void pictureSave(CloudRequestDTO.JoinDto request, Cloud cloud){

    }

}
