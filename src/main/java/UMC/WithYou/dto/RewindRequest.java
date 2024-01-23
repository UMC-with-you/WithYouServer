package UMC.WithYou.dto;

import UMC.WithYou.common.validation.annotation.ExistQuestionId;
import UMC.WithYou.domain.rewind.Mood;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import lombok.Getter;

import java.util.List;

public class RewindRequest {

    @Getter
    public static class CreateRewindDto {
        @Min(1)
        @Schema(description = "여행 일자", example = "1")
        Integer day;
        @Schema(description = "MVP 투표 대상 멤버 ID", example = "10")
        Long mvpCandidateId;
        @Schema(description = "오늘의 기분")
        Mood mood;
        @Schema(description = "Q&A 목록")
        List<CreateRewindQnaDto> qnaList;
        @Schema(description = "오늘의 한마디", example = "좋은 날이었습니다.")
        String comment;
    }

    @Getter
    public static class CreateRewindQnaDto {
        @ExistQuestionId
        @Schema(description = "질문 ID", example = "456")
        Long questionId;
        @Schema(description = "질문 답변", example = "기분 좋았어요.")
        String answer;
    }
}
