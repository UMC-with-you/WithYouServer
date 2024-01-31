package UMC.WithYou.converter;

import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;

public class CloudConverter {
    public static CloudResponseDTO.ResultDto toResultDTO(Cloud cloud){
        return CloudResponseDTO.ResultDto.builder()
                .createdAt(cloud.getCreatedAt())
                .cloudId(cloud.getId())
                .build();
    }

    public static Cloud toChangeCloud(Member member){
        return Cloud.builder()
                .member(member)
                .build();
    }
}
