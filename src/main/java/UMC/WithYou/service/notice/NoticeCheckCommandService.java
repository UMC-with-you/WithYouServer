package UMC.WithYou.service.notice;

import UMC.WithYou.domain.notice.NoticeCheck;

public interface NoticeCheckCommandService {
    NoticeCheck checkBox(Long noticeId, Long memberId);
}
