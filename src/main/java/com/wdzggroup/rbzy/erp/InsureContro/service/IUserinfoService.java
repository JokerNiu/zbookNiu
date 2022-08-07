package com.wdzggroup.rbzy.erp.InsureContro.service;

import com.wdzggroup.rbzy.erp.InsureContro.bean.Userinfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-29
 */
public interface IUserinfoService extends IService<Userinfo> {
    String  Login(String userName,String Password);

}
