package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.converter.RewindConverter;
import UMC.WithYou.domain.rewind.Rewind;
import UMC.WithYou.dto.RewindRequest;
import UMC.WithYou.dto.RewindResponse;
import UMC.WithYou.service.rewind.RewindCommandService;
import com.google.common.net.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class RewindController {

    private final RewindCommandService rewindCommandService;

    @Operation(summary = "REWIND 작성", description = "해당 여행 그룹의 멤버가 특정 날짜의 여행 회고(REWIND)를 작성합니다.")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 ID", required = true, schema = @Schema(type = "Long"))
    })
    @PostMapping("/api/v1/travels/{travelId}/rewinds")
    public ApiResponse<RewindResponse.CreateRewindResultDto> createRewind(@RequestHeader(HttpHeaders.AUTHORIZATION) String token,
                                                                          @PathVariable Long travelId,
                                                                          @RequestBody @Valid RewindRequest.CreateRewindDto requestDto) {
        Rewind rewind = rewindCommandService.createRewind(token, travelId, requestDto);
        return ApiResponse.onSuccess(RewindConverter.tocreateRewindResultDto(rewind));
    }
}
