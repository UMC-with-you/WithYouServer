package UMC.WithYou.dto;

import java.util.List;
import lombok.Getter;

public class PostRequest {

    @Getter
    public static class PublishRequest{
        private Long travelId;
        private String text;
        private List<String> urls;
    }
}
