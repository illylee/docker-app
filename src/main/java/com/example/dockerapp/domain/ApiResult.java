package com.example.dockerapp.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.commons.lang3.StringUtils;

import javax.validation.constraints.NotNull;
import java.util.Collection;

/**
 * API 결과 클래스
 */
@Setter
@Getter
@ToString
@JsonInclude(value = JsonInclude.Include.NON_NULL)

public class ApiResult<T> {

    private Code code = Code.SERVER_ERROR;
    private CharSequence message;

    @JsonIgnore
    private CharSequence debugMessage;

    private T data;

    private long timestamp = 0;

    private ApiResult() {}

    /**
     * {@link #data} 객체 사이즈를 리턴한다.
     * @return 0 if null, {@link Collection} 을 구현한 객체일 경우에는 그 사이즈를 그렇지 않은 경우에는 1
     */
    @JsonIgnore
    @SuppressWarnings("rawtypes")
    public int getDataLength() {
        if (data == null) {
            return 0;
        }

        return (data instanceof Collection) ? ((Collection) data).size() : 1;
    }

    @JsonIgnore
    public boolean isSucceeded() {
        return code == Code.SUCCESS;
    }

    @JsonIgnore
    public boolean isMessageExist() {
    	return StringUtils.isNotBlank(message);
    }

    /**
     * @param code 결과 코드
     * @return 결과 객체
     */
    public ApiResult<T> code(@NotNull Code code) {
        setCode(code);

        return this;
    }

    /**
     * @param data 결과 데이터
     * @return 결과 객체
     */
    public ApiResult<T> data(T data) {
        setData(data);

        return this;
    }

    /**
     * @param message 결과 메시지
     * @return 결과 객체
     */
    public ApiResult<T> message(CharSequence message) {
        setMessage(message);

        return this;
    }

    /**
     * @param message 결과 메시지
     * @param debugMessage 결과 디버그 메시지
     * @return 결과 객체
     */
    public ApiResult<T> message(CharSequence message, CharSequence debugMessage) {
        setMessage(message);
        setDebugMessage(debugMessage);

        return this;
    }

    /**
     * 성공 객체를 생성한다.
     *
     * @param data 결과 데이터
     * @return 결과 객체
     */
    public static <T> ApiResult<T> ok(T data) {
        return with(data).code(Code.SUCCESS);
    }

    /**
     * 성공 객체를 생성한다.
     *
     * @param data    결과 데이터
     * @param message 성공 메시지
     * @return 결과 객체
     */
    public static <T> ApiResult<T> ok(T data, CharSequence message) {
        return with(data).code(Code.SUCCESS).message(message);

    }

    /**
     * 실패 객체를 생성한다.
     *
     * @param code 실패 코드
     * @param message 메시지
     * @return 결과 객체
     */
    public static ApiResult<?> error(Code code, CharSequence message) {
    	return with(null).code(code).message(message);
    }

    /**
     * 객체를 생성한다.
     *
     * @param data 결과 데이터
     * @return 결과 객체
     */
    public static <T> ApiResult<T> with(T data) {
        ApiResult<T> response = new ApiResult<>();
        response.data = data;
        response.timestamp = System.currentTimeMillis();

        return response;
    }

    public enum Code {
        /** 성공 */
        SUCCESS(0),
        /** 서버오류 */
        SERVER_ERROR(999);

        private int value;

        Code(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @JsonCreator
        public static Code forJsonValue(int value) {
            for (Code code : values()) {
                if (code.getValue() == value) {
                    return code;
                }
            }

            return SERVER_ERROR;
        }

        @JsonValue
        public int toJsonValue() {
            return value;
        }
    }
}
