package UMC.WithYou.dto;

import UMC.WithYou.common.validation.annotation.ExistQuestionId;
import UMC.WithYou.domain.rewind.Mood;
import UMC.WithYou.domain.rewind.RewindQna;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

public class RewindResponse {

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CreateRewindResultDto {
        Long rewindId;
        LocalDateTime createdAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveRewindResultDto {
        @Schema(description = "REWIND ID", example = "100")
        Long rewindId;
        @Schema(description = "여행 날짜", example = "1")
        Integer day;
        @Schema(description = "작성자 ID", example = "135")
        Long writerId;
        @Schema(description = "MVP 투표 대상 ID", example = "235")
        Long mvpCandidateId;
        @Schema(description = "오늘의 기분", example = "GOOD")
        Mood mood;
        @Schema(description = "오늘의 한마디", example = "다음에는 더 재밌게 놀자!")
        String comment;
        @Schema(description = "질의응답 목록")
        List<RetrieveRewindQnaDto> rewindQnaList;
        LocalDateTime createdAt;
        LocalDateTime updatedAt;
    }

    @Builder
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RetrieveRewindQnaDto {
        @ExistQuestionId
        @Schema(description = "질문 ID", example = "456")
        Long questionId;
        @Schema(description = "질문 내용", example = "오늘의 기분은 어땠어?")
        String content;
        @Schema(description = "질문 답변", example = "기분 좋았어요.")
        String answer;
    }


}
