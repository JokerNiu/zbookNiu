package com.wdzggroup.rbzy.erp.InsureContro.exception;

import com.wdzggroup.rbzy.erp.InsureContro.common.IRespStatus;
import lombok.Getter;

public class Err {

    @Getter
    public enum Common implements IRespStatus {
        BASE(40000, "未知错误！请联系系统管理员！"),
        UNAUTHORIZED(40001, "认证失败，没有访问权限！"),
        ACCOUNT_IS_NOT_EXITED(40002, "账号不存在！"),
        PASSWORD_IS_ERROR(40003, "密码错误！"),
        ACCOUNT_IS_LOCKED(40004, "账号已被停用！如需要请联系管理员"),
        VALIDATED_ERR(40005, "参数错误"),
        DATA_IS_NOT_EXIST(40006, "数据不存在"),
        JSON_PARSE_ERROR(40007, "json解析有误"),
        CLASS_CAST_ERROR(40008, "类型转换错误"),
        ACCOUNT_IS_NOT_EXITED_OR_PASS_ERR(40009, "账号不存在或密码错误"),
        ACCOUNT_AMOUNT_SIZE(40010, "输入金额数据太大"),
        PASSWORD_ERROR(40011, "密码长度必须最少6位包含大小写字母、数字、特殊字符"),
        EXPORT_ERROR(40011, "数据不存在，导出失败"),
        IMPORT_ERROR(40012, "文件中数据为空，导入失败");

        private Integer code;
        private String msg;

        Common(Integer code, String msg) {
            this.code=code;
            this.msg=msg;
        }
    }

    @Getter
    public enum RESTfulRequest implements IRespStatus {
        COMMON(40100, "RESTful 接口请求异常！"),
        DATA_PARSE_FAILED(40101, "RESTful 接口返回数据类型解析失败！");

        private Integer code;
        private String msg;

        RESTfulRequest(Integer code, String msg) {
            this.code=code;
            this.msg=msg;
        }
    }

    //InsureInfo
    @Getter
    public enum InsureInfo implements IRespStatus {
        NAME_HAS_EXIST(1, "保险公司已经存在"),
        ;
        private Integer code;
        private String msg;

        InsureInfo(Integer code, String msg) {
            this.code=code;
            this.msg=msg;
        }
    }

}
