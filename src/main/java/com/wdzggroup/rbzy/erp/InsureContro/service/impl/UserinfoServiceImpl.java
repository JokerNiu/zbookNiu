package com.wdzggroup.rbzy.erp.InsureContro.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.wdzggroup.rbzy.erp.InsureContro.bean.Userinfo;
import com.wdzggroup.rbzy.erp.InsureContro.mapper.UserinfoMapper;
import com.wdzggroup.rbzy.erp.InsureContro.service.IUserinfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdzggroup.rbzy.erp.InsureContro.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Wrapper;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author rbzy
 * @since 2020-12-29
 */
@Service
public class UserinfoServiceImpl extends ServiceImpl<UserinfoMapper, Userinfo> implements IUserinfoService {
    @Autowired
    private RedisService redisService;
    @Override
    public String  Login(String userName, String Password) {
        LambdaQueryWrapper<Userinfo> loginQuery = Wrappers.lambdaQuery();
        LambdaQueryWrapper<Userinfo> eq = loginQuery.eq(userName != null, Userinfo::getUiName, userName).eq(Password != null, Userinfo::getUiPassword, Password);
        Integer integer = this.baseMapper.selectCount(eq);
        if(integer<0){
            return "用户不存在";
        }
        String token= UUID.randomUUID().toString();

        redisService.set("token",token);
        redisService.expire("token",30);
        return "用户"+userName+"登录成功"+token;

    }
}
