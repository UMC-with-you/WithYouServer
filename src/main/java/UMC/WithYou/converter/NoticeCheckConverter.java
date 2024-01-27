package UMC.WithYou.converter;

import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.dto.NoticeCheckResponseDTO;
import UMC.WithYou.dto.NoticeResponseDTO;

public class NoticeCheckConverter {

    public static NoticeCheckResponseDTO.ResultDto toResultDTO(NoticeCheck notice){ //조회용
        return NoticeCheckResponseDTO.ResultDto.builder()
                .noticeId(notice.getId())
                .createdAt(notice.getCreatedAt())
                .build();
    }

    public static NoticeCheck toChangeNoticeCheck(NoticeCheck noticeCheck){
        boolean checkStatus=false;
        if (!noticeCheck.isChecked())
            checkStatus=true;

        return NoticeCheck.builder()
                .id(noticeCheck.getId())
                .notice(noticeCheck.getNotice())
                .isChecked(checkStatus)
                .build();
    }

    public static NoticeCheck toJoinDTO(Notice notices){
        return NoticeCheck.builder()
                .isChecked(false)
                .notice(notices)
                .build();
    }
}
