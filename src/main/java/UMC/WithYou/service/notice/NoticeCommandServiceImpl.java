package UMC.WithYou.service.notice;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;
import UMC.WithYou.dto.notice.NoticeRequestDTO;
import UMC.WithYou.repository.TravelRepository;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.repository.notice.NoticeCheckRepository;
import UMC.WithYou.repository.notice.NoticeRepository;
import UMC.WithYou.repository.notice.NoticeRepositoryCustom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class NoticeCommandServiceImpl implements NoticeCommandService{

    private final NoticeRepository noticeRepository;
    private final NoticeCheckRepository noticeCheckRepository;
    private final NoticeRepositoryCustom noticeRepositoryCustom;
    private final MemberRepository memberRepository;
    private final TravelRepository travelRepository;

    private static boolean isBetween(LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return dateTime.isAfter(startDateTime) && dateTime.isBefore(endDateTime);
    }

    @Override
    public List<NoticeCheckResponseDTO.ShortResponseDto> getDateNotice(Long travelId){
        Travel travel = travelRepository.findById(travelId)
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND));
        int states=travel.getStatus().ordinal();

        List<NoticeCheckResponseDTO.ShortResponseDto> results = new ArrayList<>();
        List<Notice> notices=noticeRepositoryCustom.findByTravelLogFetchJoinMember(travelId);

        for(Notice notice : notices){
            if (notice.getState()!=states)
                continue;

            List<NoticeCheck> noticeChecks=noticeCheckRepository.findAllByIsCheckedIsTrueAndNotice(notice);

            NoticeCheckResponseDTO.ShortResponseDto check = NoticeConverter.toSearch(notice, noticeChecks.size());
            results.add(check);
        }
        return results;
    }


    @Override
    public List<NoticeCheckResponseDTO.ShortResponseDto> getTravelNotice(Long travelId){
        List<NoticeCheckResponseDTO.ShortResponseDto> results = new ArrayList<>();
        List<Notice> notices=noticeRepositoryCustom.findByTravelLogFetchJoinMember(travelId);

        for(Notice notice : notices){
            List<NoticeCheck> noticeChecks=noticeCheckRepository
                    .findAllByIsCheckedIsTrueAndNotice(notice);

            NoticeCheckResponseDTO.ShortResponseDto check = NoticeConverter.toSearch(notice, noticeChecks.size());
            results.add(check);
        }
        return results;
    }

    @Override
    public Notice createNotice(NoticeRequestDTO.JoinDto request){
        Member member = memberRepository.findById(request.getMemberId())
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
        Travel travel = travelRepository.findById(request.getLogId())
                .orElseThrow(()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND));

        Notice newNotice= NoticeConverter.toNotice(request,member,travel);
        Notice notice=noticeRepository.save(newNotice);

        return notice;
    }

    @Override
    public Notice delete(Long noticeId){
        Notice notice= noticeRepository.findById(noticeId).get();
        noticeRepository.deleteById(noticeId);

        return notice;
    }

    @Override
    public Notice fix(NoticeRequestDTO.FixDto request){
        Notice notice= noticeRepository.findById(request.getNoticeId()).get();
        Notice newNotice=NoticeConverter.toFixNotice(request,notice.getMember(),notice.getTravel());

        noticeRepository.save(newNotice);
        return notice;
    }

    @Override
    public Notice getNotice(Long noticeId){
        Notice notice= noticeRepository.findById(noticeId).get();

        return notice;
    }
}
