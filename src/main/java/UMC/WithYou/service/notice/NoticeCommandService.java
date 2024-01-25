package UMC.WithYou.service.notice;

import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.NoticeCheckResponseDTO;
import UMC.WithYou.dto.NoticeRequestDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface NoticeCommandService {

    Notice createNotice(NoticeRequestDTO.JoinDto request);

    Notice delete(Long noticeId);

    Notice fix(NoticeRequestDTO.FixDto request);

    Notice getNotice(Long noticeId);

    List<NoticeCheckResponseDTO.ShortResponseDto> getTravelNotice(Long travelId);

    List<NoticeCheckResponseDTO.ShortResponseDto> getDateNotice(Long travelId, LocalDateTime checkDate);
}
