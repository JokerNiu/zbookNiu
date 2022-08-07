package com.wdzggroup.rbzy.erp.InsureContro.common;

import lombok.Data;

@Data
public class ResultVO<T> {
    private Integer code;
    private  String msg;
    private T data;
}
