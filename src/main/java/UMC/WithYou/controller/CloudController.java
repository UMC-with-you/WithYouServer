package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.converter.CloudConverter;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import UMC.WithYou.service.cloud.CloudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/cloud")
public class CloudController {

    private final CloudService cloudService;

    @Operation(summary="cloud 생성 API")
    @PostMapping
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4003", description = "해당 member가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<CloudResponseDTO.ResultDto> create(@RequestBody @Valid CloudRequestDTO.JoinDto request){
        Cloud cloud= cloudService.createCloud(request);
        return ApiResponse.onSuccess(CloudConverter.toResultDTO(cloud));
    }
}
