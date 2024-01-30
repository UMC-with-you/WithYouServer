package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.Post.Reply;
import UMC.WithYou.dto.post.ReplyRequest.*;
import UMC.WithYou.dto.post.ReplyResponse.*;
import UMC.WithYou.service.post.ReplyService;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyController {
    private final ReplyService replyService;

    public ReplyController(ReplyService replyService) {
        this.replyService = replyService;
    }


    @Operation(summary = "대댓글 추가")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "commentId" , description = "댓글 Id", required = true, schema = @Schema(type = "Long"))
    })

    @PostMapping("api/v1/comments/{commentId}/replies")
    public ApiResponse<WriteResponseDTO> writeReply(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable @Valid Long commentId,
            @RequestBody WriteRequestDTO request
    ){
        String content = request.getContent();
        Reply reply = replyService.writeReply(token, commentId, content);

        return ApiResponse.onSuccess(new WriteResponseDTO(reply));
    }



    @Operation(summary = "대댓글 삭제")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "replyId" , description = "대댓글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("api/v1/replies/{replyId}")
    public ApiResponse<DeletionResponseDTO> deleteReply(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable @Valid Long replyId
    ){
        replyService.deleteReply(token, replyId);

        return ApiResponse.onSuccess(new DeletionResponseDTO(replyId));
    }


    @Operation(summary = "대댓글 수정")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "replyId" , description = "대댓글 Id", required = true, schema = @Schema(type = "Long"))
    })

    @PatchMapping("api/v1/replies/{replyId}")
    public ApiResponse<EditResponseDTO> editReply(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable @Valid Long replyId,
            @RequestBody EditRequestDTO request ){

        String content = request.getContent();
        replyService.editReply(token, replyId, content);
        return ApiResponse.onSuccess(new EditResponseDTO(replyId));
    }

}
