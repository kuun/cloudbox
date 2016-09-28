package org.cloudbox.console.core.exception;

/**
 * Created by kevin on 9/29/16.
 */
public class AppException extends RuntimeException {
    private ErrorCode code;

    public AppException(ErrorCode code) {
        this.code = code;
    }

    public AppException(ErrorCode code, String message) {
        super(message);
        this.code = code;
    }

    public AppException(ErrorCode code, Throwable cause) {
        super(cause);
        this.code = code;
    }

    public ErrorCode getCode() {
        return code;
    }

    public void setCode(ErrorCode code) {
        this.code = code;
    }
}
