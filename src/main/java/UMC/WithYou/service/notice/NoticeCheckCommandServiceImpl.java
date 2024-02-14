package UMC.WithYou.service.notice;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.repository.notice.NoticeCheckRepository;
import UMC.WithYou.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCheckCommandServiceImpl implements NoticeCheckCommandService{

    private final NoticeCheckRepository noticeCheckRepository;
    private final NoticeRepository noticeRepository;
    private final MemberRepository memberRepository;

    @Override
    public NoticeCheck checkBox(Long noticeId, Long memberId){
        Notice notice= noticeRepository.findById(noticeId)
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus._NOTICE_NOT_FOUND));
        Member member=memberRepository.findById(memberId)
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));

        Optional<NoticeCheck> noticeCheck=noticeCheckRepository.findByMemberAndNotice(member,notice);
        if (noticeCheck.isPresent()){
            noticeCheck.get().changeStatus();
            return noticeCheckRepository.save(noticeCheck.get());
        }
        else {
            NoticeCheck newNoticeCheck = NoticeCheckConverter.toJoinDTO(notice, member);
            return noticeCheckRepository.save(newNoticeCheck);
        }
    }
}
