package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.converter.NoticeConverter;
import UMC.WithYou.domain.notice.Notice;
import UMC.WithYou.dto.NoticeCheckResponseDTO;
import UMC.WithYou.dto.NoticeRequestDTO;
import UMC.WithYou.dto.NoticeResponseDTO;
import UMC.WithYou.service.notice.NoticeCommandService;
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

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/notice")
public class NoticeController {

    private final NoticeCommandService noticeCommandService;

    @Operation(summary="notice 생성 API")
    @PostMapping
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "MEMBER4003", description = "해당 member가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "LOG4013", description = "해당 로그가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<NoticeResponseDTO.JoinResultDto> create(@RequestBody @Valid NoticeRequestDTO.JoinDto request){
        Notice notice= noticeCommandService.createNotice(request);
        return ApiResponse.onSuccess(NoticeConverter.toJoinResultDTO(notice));
    }


    @Operation(summary="notice 삭제 API")
    @DeleteMapping("/{noticeId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE4023", description = "해당 notice가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "noticeId", description = "notice 의 아이디, path variable 입니다!"),
    })
    public ApiResponse<NoticeResponseDTO.JoinResultDto> delete(@PathVariable Long noticeId){
        Notice notice=noticeCommandService.delete(noticeId);
        return ApiResponse.onSuccess(NoticeConverter.toJoinResultDTO(notice));
    }

    @Operation(summary="notice 단건 조회 API")
    @GetMapping("/{noticeId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE4023", description = "해당 notice가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "noticeId", description = "notice 의 아이디, path variable 입니다!"),
    })
    public ApiResponse<NoticeResponseDTO.ResultDto> getNotice(@PathVariable Long noticeId){
        Notice notice=noticeCommandService.getNotice(noticeId);
        return ApiResponse.onSuccess(NoticeConverter.toResultDTO(notice));
    }

    @Operation(summary="travelLog에 따른 notice 모두 조회 API")
    @GetMapping("/logs/{noticeId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TRAVEL4003", description = "해당 travel log가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "logId", description = "travel log 의 아이디, path variable 입니다!"),
    })
    public ApiResponse<List<NoticeCheckResponseDTO.ShortResponseDto>> getTravelNotice(@PathVariable Long travelId){
        List<NoticeCheckResponseDTO.ShortResponseDto> notices=noticeCommandService.getTravelNotice(travelId);
        return ApiResponse.onSuccess(notices);
    }

    @Operation(summary="날짜에 따른 notice 모두 조회 API")
    @GetMapping("/date/{noticeId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "TRAVEL4003", description = "해당 travel log가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "logId", description = "travel log 의 아이디, path variable 입니다!"),
            @Parameter(name = "checkDate", description = "현재 날짜, path variable 입니다!"),
    })
    public ApiResponse<List<NoticeCheckResponseDTO.ShortResponseDto>> getDateNotice
            (@PathVariable Long travelId, @PathVariable LocalDateTime checkDate){
        List<NoticeCheckResponseDTO.ShortResponseDto> notices=noticeCommandService.getDateNotice(travelId,checkDate);
        return ApiResponse.onSuccess(notices);
    }

    @Operation(summary="notice 수정 API")
    @PatchMapping
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE4023", description = "해당 notice가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    public ApiResponse<NoticeResponseDTO.JoinResultDto> fix(@RequestBody @Valid NoticeRequestDTO.FixDto request){
        Notice notice=noticeCommandService.fix(request);
        return ApiResponse.onSuccess(NoticeConverter.toJoinResultDTO(notice));
    }
}