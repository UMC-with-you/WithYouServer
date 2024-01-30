package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.dto.TravelRequestDTO.*;
import UMC.WithYou.dto.TravelResponseDTO.*;
import UMC.WithYou.service.TravelService;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class TravelController {
    private TravelService travelService;

    @PostMapping("api/v1/travels")
    public ApiResponse<ConfigurationResponseDTO> createTravel(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody  ConfigurationRequestDTO request){

        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();

        Long id = travelService.createTravel(token, title, startDate, endDate, url);
        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }

    @GetMapping("api/v1/travels")
    public ApiResponse<List<ThumbnailResponseDTO>> getTravelThumbnails(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token){
        List<Travel> travels = travelService.getThumbnails(token);
        return ApiResponse.onSuccess(
                travels.stream().map(t -> new ThumbnailResponseDTO(t)).toList()
        );
    }



    @PatchMapping("api/v1/travels/{travelId}")
    public ApiResponse<ConfigurationResponseDTO> editTravel(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("travelId") Long travelId,
            @RequestBody @Valid ConfigurationRequestDTO request
    ){
        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();
        Long id = travelService.editTravel(token, travelId, title, startDate, endDate, url);

        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }


    @GetMapping("api/v1/travels/{travelId}/members")
    public ApiResponse<List<TravelerResponseDTO>> getTravelMembers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("travelId") Long travelId
    ){
        List<Member> members = travelService.getMembers(token, travelId);
        return ApiResponse.onSuccess(
                members.stream().map( m -> new TravelerResponseDTO(m)).toList()
        );
    }

    @GetMapping("api/v1/travels/{travelId}/invitation_code")
    public void issueInvitationCode(){

    }

    @PatchMapping("api/v1/travels/{travelId}/members")
    public void joinTravel(){

    }

    @GetMapping("api/v1/travels/{travelId}")
    public void getTravel(){

    }

}
