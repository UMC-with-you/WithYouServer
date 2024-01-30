package UMC.WithYou.domain.Post;

import UMC.WithYou.domain.BaseEntity;
import UMC.WithYou.domain.member.Member;
import UMC.WithYou.domain.travel.Travel;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

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

//    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL)
//    private List<ScrapedPost> scrapedPosts = new ArrayList<>();

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<PostMedia> postMediaList;

    @OneToMany(mappedBy = "post", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Comment> comments = new ArrayList<>();


    private String text;

    public Post(Member publisher, Travel travel, String text, List<String> urls) {
        this.member = publisher;
        this.travel = travel;
        this.text = text;
        this.postMediaList = new ArrayList<>();

        for (int position = 0; position < urls.size(); position++){
            postMediaList.add(new PostMedia(urls.get(position), this, position));
        }

    }

    public void edit(String text, Map<Long, Integer> newPositions) {

        System.out.println("입력");
        newPositions.forEach((key, value) -> {
            System.out.println("ID: " + key + ", Position: " + value);
        });

        System.out.println("기존");
        for (PostMedia postMedia: this.getPostMediaList()){
            System.out.println(postMedia.getId());
        }
        this.text = text;
        for (PostMedia postMedia: this.getPostMediaList()){
            Long mediaId = postMedia.getId();
            int newPosition = newPositions.get(mediaId);

            // 미디어 삭제 로직
//            if (newPosition == -1){
//                this.getPostMediaList().remove(postMedia);
//            }
//            else{
//                postMedia.setPosition(newPosition);
//            }

            postMedia.setPosition(newPosition);
        }


        Collections.sort(this.postMediaList);
    }
}


