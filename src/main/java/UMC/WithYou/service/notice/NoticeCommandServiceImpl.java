package UMC.WithYou.service.notice;

import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.dto.NoticeRequestDTO;
import UMC.WithYou.repository.notice.NoticeCheckRepository;
import UMC.WithYou.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommandServiceImpl implements NoticeCommandService{

    private final NoticeRepository noticeRepository;
    private final NoticeCheckRepository noticeCheckRepository;

    @Override
    @Transactional
    public Notice createNotice(NoticeRequestDTO.JoinDto request){
        Notice newNotice= NoticeConverter.toNotice(request);
        Notice notice=noticeRepository.save(newNotice);

        NoticeCheck newNoticeCheck= NoticeCheckConverter.toJoinDTO(notice);
        noticeCheckRepository.save(newNoticeCheck);

        return notice;
    }

    @Override
    public Notice delete(Long noticeId){
        Notice notice= noticeRepository.findById(noticeId).get();
        noticeRepository.deleteById(noticeId);

        return notice;
    }

    @Override
    @Transactional
    public Notice fix(NoticeRequestDTO.FixDto request){
        Notice notice= noticeRepository.findById(request.getNoticeId()).get();
        Notice newNotice=NoticeConverter.toFixNotice(request);

        noticeRepository.save(newNotice);
        return notice;
    }

    @Override
    public Notice getNotice(Long noticeId){
        Notice notice= noticeRepository.findById(noticeId).get();

        return notice;
    }
}
