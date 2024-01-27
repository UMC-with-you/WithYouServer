package UMC.WithYou.controller;

import UMC.WithYou.common.apiPayload.ApiResponse;
import UMC.WithYou.service.post.PostService;
import UMC.WithYou.dto.PostRequest.*;
import UMC.WithYou.dto.PostResponse.*;
import com.google.common.net.HttpHeaders;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }


    @PostMapping("api/v1/posts")
    public ApiResponse<PublishResponse> publishPost(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String token,
            @RequestBody @Valid PublishRequest request
        ){
        Long travelId = request.getTravelId();
        String text = request.getText();
        List<String> urls = request.getUrls();
        Long postId = postService.createPost(token, travelId, text, urls);
        return ApiResponse.onSuccess(new PublishResponse(postId));
    }

//    @GetMapping("api/v1/posts/{travelId}")
//    public ApiResponse<PublishResponse> getPosts(@PathVariable("travelId") Long travelId){
//
//    }

}
