package UMC.WithYou.service.post;

import UMC.WithYou.common.apiPayload.code.status.ErrorStatus;
import UMC.WithYou.common.apiPayload.exception.handler.CommonErrorHandler;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.Post.ScrapedPost;
import UMC.WithYou.domain.dummy.DummyTravel;
import UMC.WithYou.domain.member.Email;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.repository.dummy.DummyTravelRepository;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.repository.post.PostRepository;
import UMC.WithYou.repository.post.ScrapedPostRepository;
import UMC.WithYou.service.member.MemberService;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@Transactional
@RequiredArgsConstructor
public class PostService {
    private final PostRepository postRepository;
    private final MemberService memberService;
    private final DummyTravelRepository travelRepository;
    private final ScrapedPostRepository scrapedPostRepository;


    public Long createPost(String token, Long travelId, String text, List<String> urls){

//        Member publisher = memberRepository.findByEmail(new Email(token)).orElseThrow(
//                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
//        );
        Member publisher = memberService.findByMemberIdToken(token);
        DummyTravel travel = travelRepository.findById(travelId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND)
        );
        if (urls.isEmpty() || urls.size() > 11){
            throw new CommonErrorHandler(ErrorStatus.INVALID_MEDIA_COUNT);
        }

        Post post = new Post(publisher, travel, text, urls);
        postRepository.save(post);
        return post.getId();
    }

    public List<Post> getPosts(Long travelId){
        travelRepository.findById(travelId).orElseThrow(
            ()->new CommonErrorHandler(ErrorStatus.TRAVEL_LOG_NOT_FOUND)
        );

        return postRepository.findByTravelId(travelId);
    }

    public Post getPost(Long postId){
        return this.findPostById(postId);
    }

    public void deletePost(String token, Long postId){
//        Member member = memberRepository.findByEmail(new Email(token)).orElseThrow();
        Member member = memberService.findByMemberIdToken(token);
        Post post = this.findPostById(postId);


        this.validatePostOwnership(member, post);

        postRepository.delete(post);
    }




    public Post editPost(String token, Long postId, String text, Map<Long, Integer> newPositions){
        Post post = this.findPostById(postId);
//        Member member = memberRepository.findByEmail(new Email(token)).orElseThrow(
//                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
//        );
        Member member = memberService.findByMemberIdToken(token);
        this.validatePostOwnership(member, post);

        post.edit(text, newPositions);
        return post;
    }

    public List<Post> getPosts(String token) {
        /*Member member = memberRepository.findByEmail(new Email(token)).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );*/
        Member member = memberService.findByMemberIdToken(token);
        List<ScrapedPost> scrapedPosts = member.getScrapedPosts();
        List<Post> posts = new ArrayList<>();
        for (ScrapedPost scrapedPost: scrapedPosts){
            posts.add(scrapedPost.getPost());
        }
        return posts;
    }

    public Long scrapePost(String token, Long postId) {
        Post post = this.findPostById(postId);
//        Member member = memberRepository.findByEmail(new Email(token)).orElseThrow(
//                ()->new CommonErrorHandler(ErrorStatus.MEMBER_NOT_FOUND)
//        );
        Member member = memberService.findByMemberIdToken(token);
        ScrapedPost scrapedPost = new ScrapedPost(post, member);
        scrapedPostRepository.save(scrapedPost);
        return scrapedPost.getPost().getId();
    }








    public Post findPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(
                ()->new CommonErrorHandler(ErrorStatus.POST_NOT_FOUND)
        );
    }

    private void validatePostOwnership(Member member, Post post) {
        if (!member.isSameId(post.getMember().getId())){
            throw new CommonErrorHandler(ErrorStatus.UNAUTHORIZED_ACCESS_TO_POST);
        }
    }
}
