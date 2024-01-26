package UMC.WithYou.service.rewind;

import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.repository.rewind.RewindRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RewindQueryServiceImpl implements RewindQueryService{

    private final RewindRepository rewindRepository;

    @Override
    public List<Rewind> retrieveRewindsInTravel(String token, Long travelId, Integer day) {
//        //임시 로직 -> member check with token parsing
//        Long memberId = Long.parseLong(token);
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        //travel check
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.TRAVEL_NOT_FOUND));
//        // traveler check
//        Traveler traveler = travel.getTravelers().stream()
//                .filter(traveler -> member.equals(traveler.getMember()))
//                .findAny()
//                .orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_IN_TRAVELER));
//        if(day != null) {
//          check valid travel's day
//          // Duration.between으로 두 LocalDateTime 사이의 차이를 계산
//           Duration duration = Duration.between(travel.getStartDateTime(), travel.getStartEndTime());
//          // 차이를 일로 변환
//           long daysDifference = Math.abs(duration.toDays());
//           if (daysDifference < day) throw new CommonErrorHandler(ErrorStatus.TRAVEL_DAY_NOT_VALID);
//            return rewindRepository.findAllByTravelAndDay(travel,day);
//          }

//        return rewindRepository.findAllByTravel(travel);

        return null;
    }

    @Override
    public Rewind retrieveRewindById(String token, Long travelId, Long rewindId) {
//        //임시 로직 -> member check with token parsing
//        Long memberId = Long.parseLong(token);
//        Member member = memberRepository.findById(memberId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND));
//        //travel check
//        Travel travel = travelRepository.findById(travelId).orElseThrow(() -> new CommonErrorHandler(ErrorStatus.TRAVEL_NOT_FOUND));
//        // traveler check
//        Traveler traveler = travel.getTravelers().stream()
//                .filter(traveler -> member.equals(traveler.getMember()))
//                .findAny()
//                .orElseThrow(() -> new CommonErrorHandler(ErrorStatus.MEMBER_NOT_IN_TRAVELER));
        return rewindRepository.findById(rewindId).get();
    }

    @Override
    public boolean checkRewindIdExist(Long rewindId) {
        Optional<Rewind> rewind = rewindRepository.findById(rewindId);
        return rewind.isPresent();
    }
}
