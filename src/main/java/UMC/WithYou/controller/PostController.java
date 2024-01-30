package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.service.post.PostService;
import UMC.WithYou.dto.post.PostRequest.*;
import UMC.WithYou.dto.post.PostResponse.*;
import org.springframework.http.HttpHeaders;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {
    private final PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }

    @Operation(summary = "포스트 작성")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "travelId" , description = "여행 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PostMapping("api/v1/travels/{travelId}/posts")
    public ApiResponse<PublishResponse> publishPost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("travelId") Long travelId,
            @RequestBody @Valid PublishRequestDTO request
        ){
        String text = request.getText();
        List<String> urls = request.getUrls();
        Long postId = postService.createPost(token, travelId, text, urls);
        return ApiResponse.onSuccess(new PublishResponse(postId));
    }


    @Operation(summary = "여행 로그에 해당하는 모든 포스트 조회")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("api/v1/travels/{travelId}/posts")
    public ApiResponse<List<ThumbnailResponseDTO>> getPostThumbnails(@PathVariable("travelId") Long travelId){
        List<Post> posts = postService.getPosts(travelId);

        return ApiResponse.onSuccess(posts.stream()
                .map(p -> new ThumbnailResponseDTO(p))
                .toList()
        );
    }

    @Operation(summary = "게시글 단건 조회")
    @Parameters({
            @Parameter( name = "travelId" , description = "여행 Id", required = true, schema = @Schema(type = "Long")),
            @Parameter( name = "postId" , description = "게시글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @GetMapping("api/v1/posts/{postId}")
    public ApiResponse<PostResponseDTO> getPost(
            @PathVariable("postId") Long postId
    ){

        Post post = postService.getPost(postId);
        return ApiResponse.onSuccess(new PostResponseDTO(post));
    }


    @Operation(summary = "게시글 삭제")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "postId" , description = "게시글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @DeleteMapping("api/v1/posts/{postId}")
    public ApiResponse<DeletionResponseDTO> deletePost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("postId") Long postId
    ){
        postService.deletePost(token, postId);

        return ApiResponse.onSuccess(new DeletionResponseDTO(postId));
    }


    @Operation(summary = "게시글 수정")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "postId" , description = "게시글 Id", required = true, schema = @Schema(type = "Long"))
    })

    @PatchMapping("api/v1/posts/{postId}")
    public ApiResponse<EditResponseDTO> editPost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("postId") Long postId,
            @RequestBody EditRequestDTO request
    ){
        String text = request.getText();
        Map<Long, Integer> newPositions = request.getNewPositions();
        Post post = postService.editPost(token, postId, text, newPositions);
        return ApiResponse.onSuccess(new EditResponseDTO(post.getId()));
    }



    @Operation(summary = "게시글 스크랩")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String")),
            @Parameter( name = "postId" , description = "게시글 Id", required = true, schema = @Schema(type = "Long"))
    })
    @PostMapping("api/v1/posts/{postId}")
    public ApiResponse<ScrapeResponseDTO> scrapPost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @PathVariable("postId") Long postId
    ){
        Long scrapedPostId = postService.scrapePost(token, postId);
        return ApiResponse.onSuccess(new ScrapeResponseDTO(scrapedPostId));
    }


    @Operation(summary = "회원이 스크랩한 모든 게시글 조회")
    @Parameters({
            @Parameter(name = "Authorization", description = "JWT token", required = true, schema = @Schema(type = "String"))
    })
    @GetMapping("api/v1/posts")
    public ApiResponse<List<ThumbnailResponseDTO>> getScrapedPosts(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token
    ){
        List<Post> posts = postService.getPosts(token);

        return ApiResponse.onSuccess(
                posts.stream()
                .map(p -> new ThumbnailResponseDTO(p))
                .toList()
        );
    }


}
