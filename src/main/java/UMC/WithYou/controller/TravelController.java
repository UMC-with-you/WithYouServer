package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
import UMC.WithYou.domain.travel.Traveler;
import UMC.WithYou.dto.TravelRequestDTO.*;
import UMC.WithYou.dto.TravelResponseDTO.*;
import UMC.WithYou.service.TravelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/travels")
public class TravelController {
    private TravelService travelService;

    @Operation(summary = "트래블 로그 추가")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
    })
    @PostMapping
    public ApiResponse<ConfigurationResponseDTO> createTravel(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody  ConfigurationRequestDTO request){

        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();
        LocalDate localDate = request.getLocalDate();
        Long id = travelService.createTravel(token, title, startDate, endDate, url, localDate);
        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }


    @Operation(summary = "멤버가 포함된 모든 여행 로그 조회")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
    })
    @GetMapping
    public ApiResponse<List<ThumbnailResponseDTO>> getTravelThumbnails(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody RetrieveResponseDTO request){
        LocalDate currentLocalDate = request.getCurrentLocalDate();
        List<Travel> travels = travelService.getTravels(token, currentLocalDate);
        return ApiResponse.onSuccess(
                travels.stream().map(t -> new ThumbnailResponseDTO(t)).toList()
        );
    }



    @Operation(summary = "여행 로그 삭제")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("/{travelId}")
    public ApiResponse<DeletionResponseDTO> deleteTravel(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token, @PathVariable Long travelId
    ){
        Long id = travelService.deleteTravel(token, travelId);

        return ApiResponse.onSuccess(new DeletionResponseDTO(id));
    }


    @Operation(summary = "여행 로그 수정")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PatchMapping("/{travelId}")
    public ApiResponse<ConfigurationResponseDTO> editTravel(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("travelId") Long travelId,
            @RequestBody @Valid ConfigurationRequestDTO request
    ){
        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();
        LocalDate localDate = request.getLocalDate();
        Long id = travelService.editTravel(token, travelId, title, startDate, endDate, url, localDate);

        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }



    @Operation(summary = "여행 로그에 포함된 모든 멤버 조회")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/{travelId}/members")
    public ApiResponse<List<TravelerResponseDTO>> getTravelMembers(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("travelId") Long travelId
    ){
        List<Member> members = travelService.getMembers(token, travelId);
        return ApiResponse.onSuccess(
                members.stream().map( m -> new TravelerResponseDTO(m)).toList()
        );
    }

    @Operation(summary = "여행 로그의 초대 코드 조회")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/{travelId}/invitation_code")
    public ApiResponse<InvitationCodeResponseDTO> getInvitationCode(
            @RequestHeader("Authorization") String token, @PathVariable("travelId") Long travelId){
        String invitationCode = travelService.getInvitationCode(token, travelId);
        return ApiResponse.onSuccess(new InvitationCodeResponseDTO(travelId, invitationCode));
    }


    @Operation(summary = "여행 로그 합류")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
    })
    @PatchMapping("/members")
    public ApiResponse<JoinResponseDTO> join(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody JoinRequestDTO request
    ){
        String invitationCode = request.getInvitationCode();
        Traveler traveler = travelService.join(token, invitationCode);
        return ApiResponse.onSuccess(new JoinResponseDTO(traveler));
    }

}
