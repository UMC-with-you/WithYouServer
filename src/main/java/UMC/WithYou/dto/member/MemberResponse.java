package UMC.WithYou.dto.member;


import lombok.Builder;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MemberResponse {
    private String imageUrl;
    private String name;

    @Builder
    public MemberResponse(String imageUrl, String name) {
        this.imageUrl = imageUrl;
        this.name = name;
    }
}
