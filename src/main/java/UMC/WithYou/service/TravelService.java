package UMC.WithYou.service;

import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.TravelRequestDTO.ConfigurationRequestDTO;
import UMC.WithYou.repository.TravelRepository;
import UMC.WithYou.service.member.MemberService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class TravelService {
    private final TravelRepository travelRepository;
    private final MemberService memberService;

    public Long createTravel(String token, String title, LocalDate startDate, LocalDate endDate, String url) {
        Member member = memberService.findByMemberIdToken(token);

        Travel travel =  new Travel(member, title, startDate, endDate, url);

        travelRepository.save(travel);

        return travel.getId();
    }

    public List<Travel> getThumbnails(String token) {
        Member member = memberService.findByMemberIdToken(token);
        return member.getTravels();
    }

    public Long editTravel(String token, Long travelId, String title, LocalDate startDate, LocalDate endDate, String url) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        validateTraveler(member, travel);

        travel.edit(title, startDate, endDate, url);
        return travel.getId();
    }

    public List<Member> getMembers(String token, Long travelId) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        validateTraveler(member, travel);

        return travel.getTravelMembers();
    }





    private void validateTraveler(Member member, Travel travel){
        if (!travel.isTraveler(member)){
            // throw exception that notifies this travel doesn't belong to the member identified with given token
        }
    }
    private Travel findTravelById(Long travelId){
        return travelRepository.findById(travelId).orElseThrow(

        );
    }


}
