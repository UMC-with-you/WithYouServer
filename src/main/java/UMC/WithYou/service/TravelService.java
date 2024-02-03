package UMC.WithYou.service;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.domain.travel.Traveler;
import UMC.WithYou.repository.TravelRepository;
import UMC.WithYou.service.member.MemberService;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Transactional
public class TravelService {
    private final TravelRepository travelRepository;
    private final MemberService memberService;

    public Long createTravel(String token, String title, LocalDate startDate, LocalDate endDate, String url, LocalDate localDate) {
        Member member = memberService.findByMemberIdToken(token);

        Travel travel =  new Travel(member, title, startDate, endDate, url);

        travel.setTravelStatus(localDate);

        Traveler traveler = new Traveler(travel, member);
        travel.addTravelMember(traveler);
        travelRepository.save(travel);

        return travel.getId();
    }

    public List<Travel> getTravels(String token, LocalDate currentLocalDate) {
        Member member = memberService.findByMemberIdToken(token);
        List<Traveler> travelers = member.getTravelers();
        List<Travel> travels = new ArrayList<>();

        for (Traveler traveler: travelers){
            Travel travel = traveler.getTravel();
            travel.setTravelStatus(currentLocalDate);
            travels.add(travel);
        }

        return travels;
    }

    public Long deleteTravel(String token, Long travelId) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        if (travel.validateOwnership(member)){
            throw new CommonErrorHandler(ErrorStatus.UNAUTHORIZED_ACCESS_TO_TRAVEL);
        };

        travelRepository.delete(travel);
        return travelId;
    }

    public Long editTravel(String token, Long travelId, String title,
                           LocalDate startDate, LocalDate endDate, String url, LocalDate localDate) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        validateTraveler(member, travel);
        travel.edit(title, startDate, endDate, url);
        travel.setTravelStatus(localDate);
        return travel.getId();
    }

    public List<Member> getMembers(String token, Long travelId) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        validateTraveler(member, travel);

        return travel.getTravelMembers();
    }

    public Traveler join(String token, String invitationCode){
        Member member = memberService.findByMemberIdToken(token);

        Travel travel = travelRepository.findByInvitationCode(invitationCode).orElseThrow(
            ()->new CommonErrorHandler(ErrorStatus.INVITATION_CODE_NOT_FOUND)
        );

        Traveler traveler = new Traveler(travel, member);

        if (travel.isTraveler(member)){
            return traveler;
        }

        travel.addTravelMember(traveler);
        member.addTraveler(traveler);
        return traveler;
    }

    public String getInvitationCode(String token, Long travelId) {
        Member member = memberService.findByMemberIdToken(token);
        Travel travel = findTravelById(travelId);

        validateTraveler(member, travel);

        if (travel.hasInvitationCode()){
            return travel.getInvitationCode();
        }

        String invitationCode = UUID.randomUUID().toString();

        while (travelRepository.findByInvitationCode(invitationCode).isPresent()){
            invitationCode = UUID.randomUUID().toString();
        }
        travel.setInvitationCode(invitationCode);
        return invitationCode;

    }





    private void validateTraveler(Member member, Travel travel){
        if (!travel.isTraveler(member)){
            throw new CommonErrorHandler(ErrorStatus.UNAUTHORIZED_ACCESS_TO_TRAVEL);
        }
    }
    private Travel findTravelById(Long travelId){
        return travelRepository.findById(travelId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND)
        );
    }



}
