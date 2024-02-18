package UMC.WithYou.dto.post;

import java.util.List;
import java.util.Map;
import lombok.Getter;

public class PostRequest {

    @Getter
    public static class PublishRequestDTO {
        private String text;
    }

    @Getter
    public static class EditRequestDTO {
        private String text;
        private Map<Long, Integer> newPositions;
    }

}
