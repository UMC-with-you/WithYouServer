package UMC.WithYou.controller;

import UMC.WithYou.common.annotation.AuthorizedMember;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/travels")
public class TravelController {
    private TravelService travelService;

    @Operation(summary = "트래블 로그 추가")

    @PostMapping
    public ApiResponse<ConfigurationResponseDTO> createTravel(
            @AuthorizedMember Member member,
            @RequestBody  ConfigurationRequestDTO request){


        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();
        LocalDate localDate = request.getLocalDate();

        Long id = travelService.createTravel(member, title, startDate, endDate, url, localDate);

        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }


    @Operation(summary = "멤버가 포함된 모든 여행 로그 조회")
    @Parameters({
            @Parameter(name = "Local Time", description = "사용자의 현 위치의 Local Time", required = true),
    })
    @GetMapping
    public ApiResponse<List<ThumbnailResponseDTO>> getTravelThumbnails(
            @AuthorizedMember Member member,
            @RequestParam LocalDate localDate){
        List<Travel> travels = travelService.getTravels(member, localDate);
        return ApiResponse.onSuccess(
                travels.stream().map(t -> new ThumbnailResponseDTO(t)).toList()
        );
    }



    @Operation(summary = "여행 로그 삭제")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("/{travelId}")
    public ApiResponse<DeletionResponseDTO> deleteTravel(
            @AuthorizedMember Member member, @PathVariable Long travelId
    ){
        Long id = travelService.deleteTravel(member, travelId);

        return ApiResponse.onSuccess(new DeletionResponseDTO(id));
    }


    @Operation(summary = "여행 로그 수정")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PatchMapping("/{travelId}")
    public ApiResponse<ConfigurationResponseDTO> editTravel(
            @AuthorizedMember Member member,
            @PathVariable("travelId") Long travelId,
            @RequestBody @Valid ConfigurationRequestDTO request
    ){
        String title = request.getTitle();
        LocalDate startDate = request.getStartDate();
        LocalDate endDate = request.getEndDate();
        String url = request.getUrl();
        LocalDate localDate = request.getLocalDate();
        Long id = travelService.editTravel(member, travelId, title, startDate, endDate, url, localDate);

        return ApiResponse.onSuccess(new ConfigurationResponseDTO(id));
    }



    @Operation(summary = "여행 로그에 포함된 모든 멤버 조회")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/{travelId}/members")
    public ApiResponse<List<TravelerResponseDTO>> getTravelMembers(
            @AuthorizedMember Member member,
            @PathVariable("travelId") Long travelId
    ){
        List<Member> members = travelService.getMembers(member, travelId);
        return ApiResponse.onSuccess(
                members.stream().map( m -> new TravelerResponseDTO(m)).toList()
        );
    }

    @Operation(summary = "여행 로그의 초대 코드 조회")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/{travelId}/invitation_code")
    public ApiResponse<InvitationCodeResponseDTO> getInvitationCode(
            @AuthorizedMember Member member, @PathVariable("travelId") Long travelId){
        String invitationCode = travelService.getInvitationCode(member, travelId);
        return ApiResponse.onSuccess(new InvitationCodeResponseDTO(travelId, invitationCode));
    }


    @Operation(summary = "여행 로그 합류")

    @PatchMapping("/members")
    public ApiResponse<JoinResponseDTO> join(
            @AuthorizedMember Member member,
            @RequestBody JoinRequestDTO request
    ){
        String invitationCode = request.getInvitationCode();
        Traveler traveler = travelService.join(member, invitationCode);
        return ApiResponse.onSuccess(new JoinResponseDTO(traveler));
    }

    @PatchMapping("{travelId}/members/{memberId}")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 로그 Id", required = true, schema = @Schema(type = "Long")),
            @Parameter( name = "memberId" , description = "여행 로그에서 탈퇴할 회원 Id", required = true, schema = @Schema(type = "Long"))
    })
    public ApiResponse<LeaveResponseDTO> leave(
            @AuthorizedMember Member member,
            @PathVariable Long travelId,
            @PathVariable("memberId") Long memberId
    ){
        travelService.leave(member, travelId, memberId);
        return ApiResponse.onSuccess(new LeaveResponseDTO(travelId, memberId));
    }
}
