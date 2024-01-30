package UMC.WithYou.common.apiPayload.code.status;

import UMC.WithYou.common.apiPayload.code.BaseErrorCode;
import UMC.WithYou.common.apiPayload.code.ErrorReasonDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorStatus implements BaseErrorCode {

    // General Error Response
    _INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "COMMON500", "서버 에러, 관리자에게 문의 바랍니다."),
    _BAD_REQUEST(HttpStatus.BAD_REQUEST,"COMMON400","잘못된 요청입니다."),
    _UNAUTHORIZED(HttpStatus.UNAUTHORIZED,"COMMON401","인증이 필요합니다."),
    _FORBIDDEN(HttpStatus.FORBIDDEN, "COMMON403", "권한이 없습니다."),


    _NOTICE_NOT_FOUND(HttpStatus.BAD_REQUEST,"NOTICE4003","공지가 없습니다."),

    // Question Error Response
    QUESTION_NOT_FOUND(HttpStatus.NOT_FOUND, "QUESTION404", "존재하지 않는 질문입니다."),



    // Member Error Response
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER4001", "존재하지 않는 회원입니다."),
    // Travel Error Response
    TRAVEL_LOG_NOT_FOUND(HttpStatus.NOT_FOUND, "TRAVEL4001", "존재하지 않는 여행 로그입니다."),

    // PackingItem Error Response
    INVALID_ITEM_NAME_LENGTH(HttpStatus.BAD_REQUEST, "PACKING_ITEM4001", "유효하지 않은 짐 이름의 길이입니다."),
    PACKING_ITEM_NOT_FOUND(HttpStatus.NOT_FOUND, "PACKING_ITEM4002", "존재하지 않는 짐입니다."),


    // Post Error Response
    INVALID_MEDIA_COUNT(HttpStatus.BAD_REQUEST, "POST4001", "유효하지 않은 게시글 메디어 개수입니다."),
    POST_NOT_FOUND(HttpStatus.NOT_FOUND, "POST4002", "존재하지 않는 게시글입니다."),
    UNAUTHORIZED_ACCESS_TO_POST(HttpStatus.BAD_REQUEST, "POST4003", "게시글 수정, 삭제 권한이 없는 회원입니다."),


    // Comment Error Response
    COMMENT_NOT_FOUND(HttpStatus.NOT_FOUND, "COMMENT4001", "존재하지 않는 댓글입니다."),
    UNAUTHORIZED_ACCESS_TO_COMMENT(HttpStatus.BAD_REQUEST, "COMMENT4002", "댓글 수정, 삭제 권한이 없는 회원입니다."),

    // Reply Error Response
    REPLY_NOT_FOUND(HttpStatus.NOT_FOUND, "REPLY4001", "존재하지 않는 대댓글입니다."),
    UNAUTHORIZED_ACCESS_TO_REPLY(HttpStatus.BAD_REQUEST, "COMMENT4002", "대댓글 수정, 삭제 권한이 없는 회원입니다."),

    ;
    private final HttpStatus httpStatus;
    private final String code;
    private final String message;

    @Override
    public ErrorReasonDTO getReason() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .build();
    }

    @Override
    public ErrorReasonDTO getReasonHttpStatus() {
        return ErrorReasonDTO.builder()
                .message(message)
                .code(code)
                .isSuccess(false)
                .httpStatus(httpStatus)
                .build();
    }
}