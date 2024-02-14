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
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import UMC.WithYou.repository.TravelRepository;
import UMC.WithYou.repository.cloud.CloudCustomRepository;
import UMC.WithYou.repository.cloud.CloudMediaRepository;
import UMC.WithYou.repository.cloud.CloudRepository;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CloudServiceImpl implements CloudService{
    private final CloudRepository cloudRepository;
    private final TravelRepository travelRepository;
    private final CloudMediaRepository cloudMediaRepository;
    private final CloudCustomRepository cloudCustomRepository;
    private final S3Service s3Service;

    @Override
    @Transactional
    public Cloud createCloud(CloudRequestDTO.JoinDto request, List<MultipartFile> files){
        Travel travel = travelRepository.findById(request.getTravelId())
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND));

        Cloud cloud = cloudRepository.findByTravel(travel)
                .orElseGet(() -> {
                    Cloud newCloud = CloudConverter.toChangeCloud(travel);
                    return cloudRepository.save(newCloud);
                });

        List<String> pictureList=files.stream()
                .map(picture->{
                    return s3Service.uploadImg(picture);
                }).collect(Collectors.toList());

        CloudMedia cloudMedia=CloudConverter.toMedia(request,cloud,pictureList);
        List<CloudMedia> medias=cloudMediaRepository.findAllByCloud(cloud);
        for (int i=0;i<medias.size();i++){
            if (request.getDate().equals(medias.get(i).getDate())) {
                cloudMedia=medias.get(i);
                cloudMedia.addUrl(pictureList);
            }
        }
        cloudMediaRepository.save(cloudMedia);

        return cloud;
    }


    @Override
    public List<CloudResponseDTO.PictureDto> getPictures(Long travelLog){
        List<CloudResponseDTO.PictureDto> pictureList=new ArrayList<>();

        Travel travel = travelRepository.findById(travelLog)
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND));

        Optional<Cloud> cloud=cloudRepository.findByTravel(travel);
        if (cloud.isEmpty()) return pictureList;
        Cloud cloud1 = cloudCustomRepository.findByTravelFetchJoinCloud(cloud.get().getId());

        pictureList=cloud1.getPictureDate().stream()
                .map(picture->{
                    return CloudConverter.toPicture(picture);
                }).collect(Collectors.toList());
        return pictureList;
    }

}
