package UMC.WithYou.dto.post;

import lombok.Getter;

public class ReplyRequest {

    @Getter
    public static class WriteRequestDTO{
        String content;
    }

    @Getter
    public static class EditRequestDTO{
        String content;
    }
}
