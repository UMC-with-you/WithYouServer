package UMC.WithYou.service.cloud;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.CloudConverter;
import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.cloud.CloudMedia;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.repository.TravelRepository;
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
    private final TravelRepository travelRepository;
    private final CloudMediaRepository cloudMediaRepository;

    @Override
    @Transactional
    public Cloud createCloud(CloudRequestDTO.JoinDto request){ //date, pictures
        Travel travel = travelRepository.findById(request.getTravelId())
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND));

        Cloud cloud = cloudRepository.findByTravel(travel)
                .orElseGet(() -> {
                    Cloud newCloud = CloudConverter.toChangeCloud(travel);
                    return cloudRepository.save(newCloud);
                });

        CloudMedia cloudMedia=CloudConverter.toMedia(request,cloud);//url 로 바꾸기
        cloudMediaRepository.save(cloudMedia);

        return cloud;
    }


}
