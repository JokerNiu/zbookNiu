package com.wdzggroup.rbzy.erp.InsureContro.common;

public class ResponseUtils {
    public  static <T> ResultVO<T> responseResult(T data,String msg,Integer code){
        ResultVO<T> tResultVO = new ResultVO<>();
        tResultVO.setData(data);
        tResultVO.setCode(code);
        tResultVO.setMsg(msg);
        return tResultVO;
    }
    public  static ResultVO<String> responseResult(Integer code,String msg){

        return  responseResult("",msg,code);
    }
    public static <T> ResultVO<T> responseResult(T data, IRespStatus status) {
        ResultVO<T> tResultVO = new ResultVO<>();
        tResultVO.setData(data);
        tResultVO.setCode(status.getCode());
        tResultVO.setMsg(status.getMsg());
        return tResultVO;
    }
    public static ResultVO<String> responseResult(IRespStatus status) {
        return responseResult(status.getCode(), status.getMsg());
    }

}
