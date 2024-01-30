package UMC.WithYou.service.rewind;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.converter.RewindConverter;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.domain.rewind.RewindQna;
import UMC.WithYou.dto.rewind.RewindRequest;
import UMC.WithYou.repository.rewind.RewindQnaRepository;
import UMC.WithYou.repository.rewind.RewindQuestionRepository;
import UMC.WithYou.repository.rewind.RewindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RewindCommandServiceImpl implements RewindCommandService {

    private final RewindRepository rewindRepository;
    private final RewindQnaRepository rewindQnaRepository;
    private final RewindQuestionRepository rewindQuestionRepository;


    @Override
    public Rewind createRewind(String token, Long travelId, RewindRequest.CreateRewindDto requestDto) {
//        //임시 로직 -> member check with token parsing
//        Long memberId = Long.parseLong(token);
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        //travel check
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.TRAVEL_NOT_FOUND));
//        //check valid travel's day
//        // Duration.between으로 두 LocalDateTime 사이의 차이를 계산
//        Duration duration = Duration.between(travel.getStartDateTime(), travel.getStartEndTime());
//        // 차이를 일로 변환
//        long daysDifference = Math.abs(duration.toDays());
//        if (daysDifference < requestDto.getDay()) throw new CommonErrorHandler(ErrorStatus.TRAVEL_DAY_NOT_VALID);
//        //traveler check
//        Traveler traveler = travel.getTravelers().stream()
//              .filter(traveler -> member.equals(traveler.getMember()))
//              .findAny()
//              .orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_IN_TRAVELER));
//        //check rewind already exists
//        rewindRepository.findByWriterAndDay(member, requestDto.getDay())
//                .map(entity -> { throw new CommonErrorHandler(ErrorStatus.REWIND_ALREADY_EXISTS); });

        //변환&저장
        Rewind rewind = RewindConverter.toRewind(requestDto);
//        rewind.setWriter(member);
        requestDto.getQnaList().stream()
                .map(createRewindQnaDto -> {
                    RewindQna rewindQna = RewindQna.builder()
                            .answer(createRewindQnaDto.getAnswer())
                            .rewindQuestion(rewindQuestionRepository.findById(createRewindQnaDto.getQuestionId()).get())
                            .build();
                    rewindQna.setRewind(rewind);
                    return rewindQna;
                })
                .forEach(rewindQnaRepository::save);
        return rewindRepository.save(rewind);
    }

    @Override
    public Rewind updateRewindById(String token, Long travelId, Long rewindId, RewindRequest.UpdateRewindDto requestDto) {
//        //임시 로직 -> member check with token parsing
//        Long memberId = Long.parseLong(token);
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        //travel check
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.TRAVEL_NOT_FOUND));
        Rewind rewind = rewindRepository.findById(rewindId).get();
//        //writer check
//        if(rewind.getWriter() != member) throw new CommonErrorHandler(ErrorStatus.NOT_VALID_WRITER);
        rewind.updateRewind(requestDto.getMvpCandidateId(), requestDto.getMood(), requestDto.getComment());
        requestDto.getQnaList().stream()
                .map(rewindQnaDto -> {
                    RewindQna rewindQna = rewindQnaRepository.findById(rewindQnaDto.getQnaId()).get();
                    rewindQna.updateRewindQna(rewindQnaDto.getAnswer());
                    rewindQna.setRewind(rewind);
                    return rewindQna;
                })
                .forEach(rewindQnaRepository::save);
        return rewindRepository.save(rewind);
    }

    @Override
    public void deleteRewindById(String token, Long travelId, Long rewindId) {
//        //임시 로직 -> member check with token parsing
//        Long memberId = Long.parseLong(token);
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        //travel check
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.TRAVEL_NOT_FOUND));
        Rewind rewind = rewindRepository.findById(rewindId).get();
//        //writer check
//        if(rewind.getWriter() != member) throw new CommonErrorHandler(ErrorStatus.NOT_VALID_WRITER);
        rewindRepository.deleteById(rewind.getId());
    }
}
