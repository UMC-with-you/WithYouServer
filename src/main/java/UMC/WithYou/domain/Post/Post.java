package UMC.WithYou.domain.Post;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.Travel;
import UMC.WithYou.domain.member.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Entity
@Table(name = "post")
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor

public class Post extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "log_id")
    private Travel travel;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<ScrapedPost> scrapedPosts = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<PostMedia> postMedia = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();


    private String text;


    static public Post createPost(Member publisher, Travel travel, String text, List<String> urls ){
        List<PostMedia> postMedia = new ArrayList<>();
        for (String url: urls){
            postMedia.add(new PostMedia(url));
        }

        return Post.builder()
                .member(publisher)
                .travel(travel)
                .text(text)
                .postMedia(postMedia)
                .build();
    }
}


