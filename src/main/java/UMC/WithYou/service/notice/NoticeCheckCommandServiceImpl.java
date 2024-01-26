package UMC.WithYou.service.notice;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.repository.notice.NoticeCheckRepository;
import UMC.WithYou.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCheckCommandServiceImpl implements NoticeCheckCommandService{

    private final NoticeCheckRepository noticeCheckRepository;
    private final NoticeRepository noticeRepository;

    @Override
    @Transactional
    public NoticeCheck checkBox(Long noticeId){
        Notice notice= noticeRepository.findById(noticeId)
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus._NOTICE_NOT_FOUND));
        NoticeCheck noticeCheck=noticeCheckRepository.findByNotice(notice).get();

        NoticeCheck newNoticeCheck= NoticeCheckConverter.toChangeNoticeCheck(noticeCheck);
        noticeCheckRepository.save(newNoticeCheck);
        return newNoticeCheck;
    }
}
