package com.wdzggroup.rbzy.erp.InsureContro.exception.handle;

import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.exception.BaseException;
import com.wdzggroup.rbzy.erp.InsureContro.exception.Err;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedList;
import java.util.List;

import static com.wdzggroup.rbzy.erp.InsureContro.common.Canst.DataCommon.EXCEPTION_CHAIN_SEPARATOR;

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler
{
    @ExceptionHandler(Exception.class)
    public HttpEntity<ResultVO<String>> handleUndefinedException(Exception e) {
        log.error(e.getMessage(),e);
        ResponseEntity<ResultVO<String>> body = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ResponseUtils.responseResult(Err.Common.BASE));
        return body;
    }

    @ExceptionHandler(BaseException.class)
    public HttpEntity<ResultVO<String>> handleBaseException(BaseException e) {
        log.error(e.getMessage());
        log.debug(e.getMessage(), e);
        Integer code = e.getCode();
        String message = getErrMsgFromExceptionChain(e, false);
        ResponseEntity<ResultVO<String>> body = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseUtils.responseResult(code, message));
        return body;
    }
    private static String getErrMsgFromExceptionChain(Exception e, boolean needSeparator) {
        List<String> errMsgList = new LinkedList<>();
        for(Throwable cause = e; cause != null; cause = cause.getCause()) {
            errMsgList.add(cause.getMessage());
        }
        return String.join(needSeparator ? EXCEPTION_CHAIN_SEPARATOR: "", errMsgList);
    }
}
