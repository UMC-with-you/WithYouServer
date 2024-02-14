package UMC.WithYou.converter;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.cloud.CloudMedia;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class CloudConverter {
    public static CloudResponseDTO.ResultDto toResultDTO(Cloud cloud){
        return CloudResponseDTO.ResultDto.builder()
                .createdAt(cloud.getCreatedAt())
                .cloudId(cloud.getId())
                .build();
    }

    public static CloudMedia toMedia(CloudRequestDTO.JoinDto request, Cloud cloud, List<String> pictureList){
        return CloudMedia.builder()
                .date(request.getDate())
                .url(pictureList)
                .cloud(cloud)
                .build();
    }

    public static Cloud toChangeCloud(Travel travel){
        return Cloud.builder()
                .travel(travel)
                .build();
    }

    public static CloudResponseDTO.PictureDto toPicture(CloudMedia cloud){
        return CloudResponseDTO.PictureDto.builder()
                .date(cloud.getDate())
                .urlList(cloud.getUrl())
                .build();
    }

}
