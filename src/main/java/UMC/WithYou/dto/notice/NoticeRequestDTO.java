package UMC.WithYou.dto.notice;

import UMC.WithYou.common.validation.annotation.ExistNotices;
import lombok.Getter;

import java.time.LocalDateTime;

public class NoticeRequestDTO {

    @Getter
    public static class JoinDto{

        //@ExistMember
        Long memberId;

        //@ExistLog
        Long logId;

        LocalDateTime startDate;

        LocalDateTime endDate;

        String content;
    }

    @Getter
    public static class FixDto{

        @ExistNotices
        Long noticeId;

        LocalDateTime startDate;

        LocalDateTime endDate;

        String content;
    }

}
