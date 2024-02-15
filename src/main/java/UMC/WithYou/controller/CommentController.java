package UMC.WithYou.controller;


import UMC.WithYou.common.annotation.AuthorizedMember;
import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.Post.Comment;
import UMC.WithYou.dto.post.CommentRequest.*;
import UMC.WithYou.dto.post.CommentResponse.*;
import UMC.WithYou.service.post.CommentService;
import UMC.WithYou.domain.member.Member;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CommentController {
    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @Operation(summary = "게시글 댓글 작성")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String"), in = ParameterIn.HEADER),
            @Parameter(name = "member", hidden = true),
            @Parameter( name = "postId" , description = "게시글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PostMapping("api/v1/posts/{postId}/comments")
    public ApiResponse<WriteResponseDTO> writeComment(
            @AuthorizedMember Member member,
            @PathVariable @Valid Long postId,
            @RequestBody @Valid WriteRequestDTO request){

        String content = request.getContent();
        Comment comment = commentService.writeComment(member, postId, content);
        return ApiResponse.onSuccess(new WriteResponseDTO(comment));
    }


    @Operation(summary = "게시글 댓글 삭제")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String"), in = ParameterIn.HEADER),
            @Parameter(name = "member", hidden = true),
            @Parameter( name = "commentId" , description = "댓글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("api/v1/comments/{commentId}")
    public ApiResponse<DeletionResponseDTO> deleteComment(
            @AuthorizedMember Member member,
            @PathVariable @Valid Long commentId){

        commentService.deleteComment(member, commentId);
        return ApiResponse.onSuccess(new DeletionResponseDTO(commentId));
    }




    @Operation(summary = "게시글 댓글 수정")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String"), in = ParameterIn.HEADER),
            @Parameter(name = "member", hidden = true),
            @Parameter( name = "commentId" , description = "댓글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PatchMapping("api/v1/comments/{commentId}")
    public ApiResponse<EditResponseDTO> editComment(
            @AuthorizedMember Member member,
            @PathVariable @Valid Long commentId,
            @RequestBody @Valid EditRequestDTO request){

        String content = request.getContent();
        Comment comment = commentService.editComment(member, commentId, content);

        return ApiResponse.onSuccess(new EditResponseDTO(comment));
    }



}
