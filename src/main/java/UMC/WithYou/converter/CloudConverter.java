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

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

public class CloudConverter {
    public static CloudResponseDTO.ResultDto toResultDTO(Cloud cloud){
        return CloudResponseDTO.ResultDto.builder()
                .createdAt(cloud.getCreatedAt())
                .cloudId(cloud.getId())
                .build();
    }

    public static CloudMedia toMedia(CloudRequestDTO.CloudJoinDto request, Cloud cloud, String picture){
        return CloudMedia.builder()
                .date(request.getDate())
                .url(picture)
                //.url(Collections.singletonList(pictureList))
                .cloud(cloud)
                .build();
    }

    public static Cloud toChangeCloud(Travel travel){
        return Cloud.builder()
                .travel(travel)
                .build();
    }

    public static CloudResponseDTO.PictureDto toPicture(LocalDate dates, List<String> urls){
        return CloudResponseDTO.PictureDto.builder()
                .date(dates)
                .urlList(urls)
                .build();
    }

}
