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

        int state;

        String content;
    }

    @Getter
    public static class FixDto{

        @ExistNotices
        Long noticeId;

        int state;

        String content;
    }

}
