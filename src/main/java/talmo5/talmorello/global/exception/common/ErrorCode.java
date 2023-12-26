package talmo5.talmorello.global.exception.common;

import lombok.Getter;

@Getter
public enum ErrorCode {

    // JWT
    INVALID_JWT_SIGNATURE_EXCEPTION(401, "잘못된 JWT 서명입니다."),
    EXPIRED_JWT_TOKEN_EXCEPTION(401, "만료된 JWT 토큰입니다."),
    UNSUPPORTED_JWT_TOKEN_EXCEPTION(401, "지원되지 않는 JWT 토큰입니다."),
    INVALID_JWT_EXCEPTION(401, "JWT 토큰이 잘못되었습니다"),
    NOT_REFRESH_TOKEN_EXCEPTION(401, "Refresh Token이 아닙니다."),
    NOT_MISMATCHED_REFRESH_TOKEN_EXCEPTION(401, "DB의 리프레쉬 토큰 값과 다릅니다."),
    NO_JWT_EXCEPTION(401, "이 요청은 JWT가 필요합니다."),
    NOT_SUPPORTED_GRANT_TYPE_EXCEPTION(401, "지원하지 않는 권한 부여 유형입니다."),

    // 회원
    NOT_FOUND_USER_EXCEPTION(401, "회원 정보를 찾을 수 없습니다."),
    FAILED_AUTHENTICATION_EXCEPTION(401, "인증에 실패하였습니다."),
    ALREADY_EXIST_USER_NAME_EXCEPTION(409, "이미 존재하는 이름입니다."),
    ALREADY_EXIST_EMAIL_EXCEPTION(409, "이미 존재하는 이메일입니다."),
    UNAUTHORIZED_MODIFY_EXCEPTION(401, "수정할 권한이 없습니다."),
    NO_AUTHORIZATION_EXCEPTION(400, "접근 권한이 없습니다"),
    MISMATCHED_PASSWORD_EXCEPTION(401, "비밀번호가 일치하지 않습니다."),
    FAILED_LOGIN_EXCEPTION(401, "닉네임 또는 패스워드를 확인해주세요."),

    // 이메일 인증
    MISMATCHED_AUTH_CODE_EXCEPTION(401, "인증번호가 일치하지 않습니다."),
    NOT_FOUND_AUTH_CODE_EXCEPTION(401, "없는 인증 번호입니다."),

    // Board
    NOT_FOUND_BOARD_EXCEPTION(401, "해당 보드를 찾을 수 없습니다."),

    // Column
    NOT_FOUND_COLUMN_EXCEPTION(401, "해당 컬럼을 찾을 수 없습니다."),
    INVALID_ORDER_EXCEPTION(401, "변경할 수 없는 순서입니다."),

    // Card
    NOT_FOUND_CARD_EXCEPTION(401, "해당 카드를 찾을 수 없습니다."),
    NOT_MATCHING_DATE_FORM_EXCEPTION(400, "올바른 형식의 날짜 형식이 아닙니다."),
    DATE_IS_AHEAD_OF_CURRENT_TIME_EXCEPTION(400, "현재 시점 이전의 날짜가 입력되었습니다. 유효한 날짜를 입력해주세요"),
    EXPIRATION_DATE_IS_AHEAD_OF_START_DATE_EXCEPTION(400, "마감일이 시작일보다 앞섭니다. 유효한 날짜를 입력해주세요."),

    // Comment
    NOT_FOUND_COMMENT_EXCEPTION(401, "해당 댓글을 찾을 수 없습니다."),

    // TODO
    NOT_FOUND_TODO_EXCEPTION(401, "해당 할일을 찾을 수 없습니다.");

    private final int status;

    private final String message;

    ErrorCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
