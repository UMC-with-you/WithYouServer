package UMC.WithYou.controller;

import UMC.WithYou.service.post.PostService;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
    private PostService postService;

    public PostController(PostService postService){
        this.postService = postService;
    }


}
