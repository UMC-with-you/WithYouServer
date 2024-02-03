package UMC.WithYou.converter;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.NoticeCheckResponseDTO;
import UMC.WithYou.dto.NoticeRequestDTO;
import UMC.WithYou.dto.NoticeResponseDTO;

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

    public static Notice toNotice(NoticeRequestDTO.JoinDto request, Member member, Travel travel){
        return Notice.builder()
                .content(request.getContent())
                .member(member)
                .travel(travel)
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .build();

    }

    public static NoticeCheckResponseDTO.ShortResponseDto toSearch(Notice notice, int checkNum){
        return NoticeCheckResponseDTO.ShortResponseDto.builder()
                .content(notice.getContent())
                .url(notice.getMember().getImageUrl())
                .checkNum(checkNum)
                .name(notice.getMember().getName())
                .build();
    }

}
