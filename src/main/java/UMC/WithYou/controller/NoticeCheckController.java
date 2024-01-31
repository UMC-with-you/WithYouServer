package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.converter.NoticeCheckConverter;
import UMC.WithYou.domain.notice.NoticeCheck;
import UMC.WithYou.dto.notice.NoticeCheckResponseDTO;
import UMC.WithYou.service.notice.NoticeCheckCommandService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Validated
@RequestMapping("/api/v1/noticeCheck")
public class NoticeCheckController {

    private final NoticeCheckCommandService noticeCheckCommandService;

    @Operation(summary="notice 체크 API")
    @PatchMapping("/{noticeId}")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE2000",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "NOTICE4023", description = "해당 notice가 없습니다",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "noticeId", description = "notice 의 아이디, path variable 입니다!"),
    })
    public ApiResponse<NoticeCheckResponseDTO.ResultDto> checkBox(@PathVariable Long noticeId){
        NoticeCheck noticeCheck=noticeCheckCommandService.checkBox(noticeId);
        return ApiResponse.onSuccess(NoticeCheckConverter.toResultDTO(noticeCheck));
    }
}
