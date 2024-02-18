package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.common.validation.annotation.ExistClouds;
import UMC.WithYou.converter.CloudConverter;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.cloud.Cloud;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.cloud.CloudRequestDTO;
import UMC.WithYou.dto.cloud.CloudResponseDTO;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;
import UMC.WithYou.service.cloud.CloudService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/cloud")
public class CloudController {

    private final CloudService cloudService;

    @Operation(summary="cloud 생성 API")
    @PostMapping
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TRAVEL003", description = "해당 travel가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<CloudResponseDTO.ResultDto> create(@RequestPart @Valid CloudRequestDTO.JoinDto request,
                                                          @RequestPart(value = "image", required = false) List<MultipartFile> files){
        Cloud cloud= cloudService.createCloud(request, files);
        return ApiResponse.onSuccess(CloudConverter.toResultDTO(cloud));
    }

    @Operation(summary="cloud 조회 API")
    @GetMapping("/{travelId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TRAVEL4003", description = "해당 travel log가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "logId", description = "travel log 의 아이디, path variable 입니다!"),
    })
    public ApiResponse<List<CloudResponseDTO.PictureDto>> getDateNotice(@PathVariable Long travelId){
        List<CloudResponseDTO.PictureDto> pictures=cloudService.getPictures(travelId);
        return ApiResponse.onSuccess(pictures);
    }

    @Operation(summary = "cloud 삭제 API")
    @DeleteMapping
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "CLOUD4003", description = "해당 cloud가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<CloudResponseDTO.ResultDto> deletePictures(@RequestBody @Valid CloudRequestDTO.DeleteDto request){
        Cloud cloud=cloudService.deletePictures(request.getCloudId(),request.getFiles());
        return ApiResponse.onSuccess(CloudConverter.toResultDTO(cloud));
    }

}
