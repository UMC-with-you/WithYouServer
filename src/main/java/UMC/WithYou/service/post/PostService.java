package UMC.WithYou.service.post;

import UMC.WithYou.common.apiPayload.exception.GeneralException;
import UMC.WithYou.domain.Post.Post;
import UMC.WithYou.domain.Travel;
import UMC.WithYou.domain.member.Email;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.repository.TravelRepository;
import UMC.WithYou.repository.member.MemberRepository;
import UMC.WithYou.repository.post.PostRepository;
import ch.qos.logback.core.spi.ErrorCodes;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final MemberRepository memberRepository;
    private final TravelRepository travelRepository;
    public PostService(PostRepository postRepository, MemberRepository memberRepository, TravelRepository travelRepository){
        this.postRepository = postRepository;
        this.memberRepository = memberRepository;
        this.travelRepository = travelRepository;
    }

    public Long createPost(String token, Long travelId, String text, List<String> urls){

        Member publisher = memberRepository.findByEmail(new Email(token)).orElseThrow();
        Travel travel = travelRepository.findById(travelId).orElseThrow();


        Post post = Post.createPost(publisher, travel, text, urls);
        postRepository.save(post);
        return post.getId();
    }

}
