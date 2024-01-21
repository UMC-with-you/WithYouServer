package UMC.WithYou.dto;

import UMC.WithYou.domain.rewind.Mood;
import lombok.Getter;

import java.util.List;

public class RewindRequest {

    @Getter
    public static class CreateRewindDto {
        Integer day;
        Long mvpCandidateId;
        Mood mood;
        List<CreateRewindQnaDto> questions;
        String comment;
    }

    @Getter
    public static class CreateRewindQnaDto {
        Long questionId;
        String answer;
    }
}
