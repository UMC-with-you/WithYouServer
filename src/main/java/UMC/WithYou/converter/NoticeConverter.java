package UMC.WithYou.converter;

import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;
import UMC.WithYou.dto.notice.NoticeRequestDTO;
import UMC.WithYou.dto.notice.NoticeResponseDTO;

public class NoticeConverter {

    public static NoticeResponseDTO.JoinResultDto toJoinResultDTO(Notice notice){
        return NoticeResponseDTO.JoinResultDto.builder()
                .noticeId(notice.getId())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    public static NoticeResponseDTO.ResultDto toResultDTO(Notice notice){ //조회용
        return NoticeResponseDTO.ResultDto.builder()
                .startAt(notice.getStartDate())
                .endAt(notice.getEndDate())
                .content(notice.getContent())
                .build();
    }

    public static Notice toFixNotice(NoticeRequestDTO.FixDto request){
        return Notice.builder()
                .id(request.getNoticeId())
                .content(request.getContent())
                .endDate(request.getEndDate())
                .startDate(request.getStartDate())
                .build();
    }

    public static Notice toNotice(NoticeRequestDTO.JoinDto request){ //미완성
        return Notice.builder()
                .content(request.getContent())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

    }

    public static NoticeCheckResponseDTO.ShortResponseDto toSearch(Notice notice, int checkNum){
        return NoticeCheckResponseDTO.ShortResponseDto.builder()
                .content(notice.getContent())
                .checkNum(checkNum)
                .name(notice.getMember().getName())
                .build();
    }

}
