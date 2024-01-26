package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.common.validation.annotation.ExistRewindId;
import UMC.WithYou.converter.RewindConverter;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.RewindRequest;
import UMC.WithYou.dto.RewindResponse;
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
    public ApiResponse<RewindResponse.CreateRewindResultDto> createRewind(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                                          @PathVariable Long travelId,
                                                                          @RequestBody @Valid RewindRequest.CreateRewindDto requestDto) {
        Rewind rewind = rewindCommandService.createRewind(token, travelId, requestDto);
        return ApiResponse.onSuccess(RewindConverter.toCreateRewindResultDto(rewind));
    }

    @Operation(summary = "REWIND 목록 조회", description = "해당 여행 그룹의 멤버가 해당 여행의 모든 회고(REWIND) 목록, 또는 해당 여행의 특정 날짜의 모든 회고를 조회합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "day", description = "여행 일자", required = false, schema = @Schema(type = "Integer"), example = "1")
    })
    @GetMapping("/api/v1/travels/{travelId}/rewinds")
    public ApiResponse<List<RewindResponse.RetrieveRewindResultDto>> retrieveAllRewindsInTravel(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                                                                @PathVariable Long travelId,
                                                                                                @RequestParam(value = "day", required = false) @Min(1) Integer day) {
        List<Rewind> rewindList = rewindQueryService.retrieveRewindsInTravel(token, travelId, day);
        return ApiResponse.onSuccess(RewindConverter.toRetrieveRewindResultDtoList(rewindList));
    }

    @Operation(summary = "REWIND 단건 조회", description = "해당 여행 그룹의 멤버가 해당 여행의 특정 회고를 조회합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter(name = "travelId", description = "여행 ID", required = true, schema = @Schema(type = "Long")),
            @Parameter(name = "rewindId", description = "회고 ID", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("/api/v1/travels/{travelId}/rewinds/{rewindId}")
    public ApiResponse<RewindResponse.RetrieveRewindResultDto> retrieveRewindById(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                                                  @PathVariable Long travelId,
                                                                                  @PathVariable @ExistRewindId Long rewindId) {
        Rewind rewind = rewindQueryService.retrieveRewindById(token, travelId, rewindId);
        return ApiResponse.onSuccess(RewindConverter.toRetrieveRewindResultDto(rewind));
    }
}
