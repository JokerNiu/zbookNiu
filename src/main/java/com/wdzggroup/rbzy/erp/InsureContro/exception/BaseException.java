package com.wdzggroup.rbzy.erp.InsureContro.exception;

import com.wdzggroup.rbzy.erp.InsureContro.common.IRespStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseException extends RuntimeException {
    private  Integer code;
    private  String msg;
    public BaseException(Integer code,String msg){
        super(msg);
        this.code=code;
        this.msg=msg;
    }

    public BaseException(Integer code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
        this.msg = msg;
    }
    public BaseException(String message, Throwable cause) {
        this(Err.Common.BASE.getCode(), message, cause);
    }

    public BaseException(String message) {
        this(Err.Common.BASE.getCode(), message);
    }

    public BaseException(IRespStatus err) {
        this(err.getCode(), err.getMsg());
    }

    public BaseException(IRespStatus err, String msg) {
        this(err.getCode(), String.format(err.getMsg(), msg));
    }

    public BaseException(IRespStatus err, Throwable cause) {
        this(err.getCode(), err.getMsg(), cause);
    }

    public BaseException(IRespStatus err, String msg, Throwable cause) {
        this(err.getCode(), String.format(err.getMsg(), msg), cause);
    }

    public BaseException() {
        this(Err.Common.BASE.getCode(), Err.Common.BASE.getMsg());
    }
}
