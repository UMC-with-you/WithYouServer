package UMC.WithYou.service.notice;

import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.NoticeRequestDTO;

public interface NoticeCommandService {

    Notice createNotice(NoticeRequestDTO.JoinDto request);

    Notice delete(Long noticeId);

    Notice fix(NoticeRequestDTO.FixDto request);

    Notice getNotice(Long noticeId);
}
