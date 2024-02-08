package UMC.WithYou.converter;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;

public class NoticeCheckConverter {

    public static NoticeCheckResponseDTO.ResultDto toResultDTO(NoticeCheck notice){
        return NoticeCheckResponseDTO.ResultDto.builder()
                .noticeId(notice.getId())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    public static NoticeCheck toChangeNoticeCheck(NoticeCheck noticeCheck,Member member,Notice notice){
        boolean checkStatus=false;
        if (!noticeCheck.isChecked())
            checkStatus=true;

        return NoticeCheck.builder()
                .id(noticeCheck.getId())
                .member(member)
                .notice(notice)
                .isChecked(checkStatus)
                .build();
    }

    public static NoticeCheck toJoinDTO(Notice notices,Member member){
        return NoticeCheck.builder()
                .isChecked(true)
                .member(member)
                .notice(notices)
                .build();
    }
}
