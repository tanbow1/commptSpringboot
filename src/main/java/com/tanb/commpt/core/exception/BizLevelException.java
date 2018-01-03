package com.tanb.commpt.core.exception;

/**
 * 业务异常:业务处理异常
 *
 * @author Tanbo
 */
public class BizLevelException extends BaseException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    private String code;

    public BizLevelException() {
        super();
        // TODO Auto-generated constructor stub
    }

    public BizLevelException(String message) {
        super(message);
        // TODO Auto-generated constructor stub
    }

    public BizLevelException(Throwable cause) {
        super(cause);
        // TODO Auto-generated constructor stub
    }

    public BizLevelException(String message, Throwable cause) {
        super(message, cause);
        // TODO Auto-generated constructor stub
    }

    public BizLevelException(String code, String msg) {
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
