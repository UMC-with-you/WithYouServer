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

    // Q&A Error Response
    QNA_NOT_FOUND(HttpStatus.NOT_FOUND, "QNA404", "존재하지 않는 QNA입니다."),

    // Rewind Error Response
    REWIND_NOT_FOUND(HttpStatus.NOT_FOUND, "REWIND404", "존재하지 않는 회고입니다."),
    NOT_VALID_WRITER(HttpStatus.FORBIDDEN, "REWIND403", "해당 회고의 작성자가 아닙니다.");


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