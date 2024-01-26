package UMC.WithYou.service.notice;

import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.dto.NoticeCheckResponseDTO;
import UMC.WithYou.dto.NoticeRequestDTO;
import UMC.WithYou.repository.notice.NoticeCheckRepository;
import UMC.WithYou.repository.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class NoticeCommandServiceImpl implements NoticeCommandService{

    private final NoticeRepository noticeRepository;
    private final NoticeCheckRepository noticeCheckRepository;

    private static boolean isBetween(LocalDateTime dateTime, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        return dateTime.isAfter(startDateTime) && dateTime.isBefore(endDateTime);
    }


    @Override
    public List<NoticeCheckResponseDTO.ShortResponseDto> getDateNotice(Long travelId, LocalDateTime checkDate){
        List<NoticeCheckResponseDTO.ShortResponseDto> results = new ArrayList<>();
//        List<Notice> notices=noticeRepository.findByTravelLogFetchJoinMember(travelId);
//
//        for(Notice notice : notices){
//            if (!isBetween(checkDate,notice.getStartDate(),notice.getEndDate()))
//                break;
//
//            List<NoticeCheck> noticeChecks=noticeCheckRepository
//                    .findAllByIsCheckedIsTrueAndNotice(notice);
//
//            NoticeCheckResponseDTO.ShortResponseDto check = NoticeConverter.toSearch(notice, noticeChecks.size());
//            results.add(check);
//        }
        return results;
    }


    @Override
    public List<NoticeCheckResponseDTO.ShortResponseDto> getTravelNotice(Long travelId){
        List<NoticeCheckResponseDTO.ShortResponseDto> results = new ArrayList<>();
//        List<Notice> notices=noticeRepository.findByTravelLogFetchJoinMember(travelId);
//
//        for(Notice notice : notices){
//            List<NoticeCheck> noticeChecks=noticeCheckRepository
//                    .findAllByIsCheckedIsTrueAndNotice(notice);
//
//            NoticeCheckResponseDTO.ShortResponseDto check = NoticeConverter.toSearch(notice, noticeChecks.size());
//            results.add(check);
//        }
        return results;
    }

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
