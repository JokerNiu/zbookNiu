package com.wdzggroup.rbzy.erp.InsureContro.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;
import java.util.Date;

@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill ....");
     /*   this.setFieldValByName("creatTime", new Date().getTime(),metaObject);
        this.setFieldValByName("updateTime", new Date().getTime(),metaObject);
        this.setFieldValByName("opertor",Canst.DataCanst.LOGIN_USER.getMsg(),metaObject);
        this.setFieldValByName("softDelete", Canst.DataCanst.SOFT_DELETE.getCode(),metaObject);*/
        this.strictInsertFill(metaObject,"creatTime",Long.class,new Date().getTime());
        this.strictInsertFill(metaObject,"updateTime",Long.class,new Date().getTime());
        this.strictInsertFill(metaObject,"opertor",String.class,new Date().getTime());
        this.strictInsertFill(metaObject,"softDelete",Integer.class,new Date().getTime());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill ....");
 /*     this.strictUpdateFill(metaObject, "opertor", String.class, Canst.DataCanst.LOGIN_USER.getMsg()); // 起始版本 3.3.0(推荐)
        this.strictUpdateFill(metaObject, "updateTime", Long.class,  new Date().getTime()); // 起始版本 3.3.0(推荐)*/
        this.setFieldValByName("updateTime", new Date().getTime(),metaObject);
        // 或者
        //this.fillStrategy(metaObject, "updateTime", LocalDateTime.now()); // 也可以使用(3.3.0 该方法有bug)
    }
}