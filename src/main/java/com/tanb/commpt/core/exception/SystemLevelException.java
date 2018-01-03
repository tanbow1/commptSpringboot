package com.tanb.commpt.core.exception;

/**
 * 系统异常:程序出错时统一抛出该异常
 *
 * @author Tanbo
 */
public class SystemLevelException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code;

    public SystemLevelException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public SystemLevelException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public SystemLevelException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public SystemLevelException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public SystemLevelException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
