package UMC.WithYou.controller;

import UMC.WithYou.common.annotation.AuthorizedMember;
import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.common.validation.annotation.ExistRewindId;
import UMC.WithYou.converter.RewindConverter;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.rewind.RewindRequest;
import UMC.WithYou.dto.rewind.RewindResponse;
import UMC.WithYou.service.rewind.RewindCommandService;
import UMC.WithYou.service.rewind.RewindQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
public class RewindController {

    private final RewindCommandService rewindCommandService;
    private final RewindQueryService rewindQueryService;

    @Operation(summary = "REWIND 작성", description = "해당 여행 그룹의 멤버가 특정 날짜의 여행 회고(REWIND)를 작성합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long"))
    })
    @PostMapping("/api/v1/travels/{travelId}/rewinds")
    public ApiResponse<RewindResponse.CreateRewindResultDto> createRewind(@AuthorizedMember Member member,
                                                                          @PathVariable Long travelId,
                                                                          @RequestBody @Valid RewindRequest.CreateRewindDto requestDto) {
        Rewind rewind = rewindCommandService.createRewind(member, travelId, requestDto);
        return ApiResponse.onSuccess(RewindConverter.toCreateRewindResultDto(rewind));
    }

    @Operation(summary = "REWIND 목록 조회", description = "해당 여행 그룹의 멤버가 해당 여행의 모든 회고(REWIND) 목록, 또는 해당 여행의 특정 날짜의 모든 회고를 조회합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "day", description = "여행 일자", required = false, schema = @Schema(type = "Integer"), example = "1")
    })
    @GetMapping("/api/v1/travels/{travelId}/rewinds")
    public ApiResponse<List<RewindResponse.RetrieveRewindResultDto>> retrieveAllRewindsInTravel(@AuthorizedMember Member member,
                                                                                                @PathVariable Long travelId,
                                                                                                @RequestParam(value = "day", required = false) @Min(1) Integer day) {
        List<Rewind> rewindList = rewindQueryService.retrieveRewindsInTravel(member, travelId, day);
        return ApiResponse.onSuccess(RewindConverter.toRetrieveRewindResultDtoList(rewindList));
    }

    @Operation(summary = "REWIND 단건 조회", description = "해당 여행 그룹의 멤버가 해당 여행의 특정 회고를 조회합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "rewindId", description = "회고 ID", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/api/v1/travels/{travelId}/rewinds/{rewindId}")
    public ApiResponse<RewindResponse.RetrieveRewindResultDto> retrieveRewindById(@AuthorizedMember Member member,
                                                                                  @PathVariable Long travelId,
                                                                                  @PathVariable @ExistRewindId Long rewindId) {
        Rewind rewind = rewindQueryService.retrieveRewindById(member, travelId, rewindId);
        return ApiResponse.onSuccess(RewindConverter.toRetrieveRewindResultDto(rewind));
    }

    @Operation(summary = "REWIND 수정", description = "해당 여행 그룹의 멤버가 해당 여행중 자신의 특정 회고를 수정합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "rewindId", description = "회고 ID", required = true, schema = @Schema(type = "Long"))
    })
    @PatchMapping("/api/v1/travels/{travelId}/rewinds/{rewindId}")
    public ApiResponse<RewindResponse.UpdateRewindResultDto> updateRewindById(@AuthorizedMember Member member,
                                                                              @PathVariable Long travelId,
                                                                              @PathVariable @ExistRewindId Long rewindId,
                                                                              @RequestBody @Valid RewindRequest.UpdateRewindDto requestDto) {
        Rewind rewind = rewindCommandService.updateRewindById(member, travelId, rewindId, requestDto);
        return ApiResponse.onSuccess(RewindConverter.toUpdateRewindResultDto(rewind));
    }

    @Operation(summary = "REWIND 삭제", description = "해당 여행 그룹의 멤버가 해당 여행중 자신의 특정 회고를 삭제합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "rewindId", description = "회고 ID", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("/api/v1/travels/{travelId}/rewinds/{rewindId}")
    public ApiResponse deleteRewindById(@AuthorizedMember Member member,
                                        @PathVariable Long travelId,
                                        @PathVariable @ExistRewindId Long rewindId) {
        rewindCommandService.deleteRewindById(member, travelId, rewindId);
        return ApiResponse.onSuccess_NoContent();
    }
}
