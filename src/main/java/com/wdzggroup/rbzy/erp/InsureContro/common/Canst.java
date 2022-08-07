package com.wdzggroup.rbzy.erp.InsureContro.common;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class Canst {
    public static class DataCommon{
        public static final String EXCEPTION_CHAIN_SEPARATOR = " cause by: ";

    }

    @Getter
    public enum Common implements IRespStatus{
        LIST_IS_EMPTY(4000,"未查到数据"),
        ADD_ERROR(4001,"新增失败"),
        DELETE_ERROR(4002,"删除失败"),
        SUCCESSFUL(0000,"操作成功");

        private Integer code;
        private  String msg;
         Common(Integer code, String msg){
           this.code=code;
           this.msg=msg;
        }

    }
    @Getter
    public enum DataCanst{
        COMPANY_STATE(0,"未合作"),
        COMPANY_STATE1(1,"合作中"),
        COMPANY_STATE2(2,"曾合作"),
        SOFT_DELETE(0,"正常"),
        SOFT_UDELETE_(1,"已修改"),
        LOGIN_USER(001,"admin");
        int currentNum1=1;
        long currentNum=currentNum1;
        int page_size1=10;
        long page_size=page_size1;
        private  Integer code;
        private String msg;
        DataCanst(Integer code,String msg){
            this.code=code;
            this.msg=msg;
        }
    }
    @Getter
    public enum RespMsg implements IRespStatus {
        SUCCESS(HttpStatus.OK.value(), "操作成功！"),
        UPDATE_SUCCESS(HttpStatus.OK.value(), "修改成功！"),
        DELETE_SUCCESS(HttpStatus.OK.value(), "删除成功！"),
        SAVE_SUCCESS(HttpStatus.OK.value(), "保存成功！"),
        RELEASE_SUCCESS(HttpStatus.OK.value(),"发布成功"),
        REGISTER_SUCCESS(HttpStatus.OK.value(),"注册成功");

        private Integer code;
        private String msg;

        RespMsg(Integer code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
