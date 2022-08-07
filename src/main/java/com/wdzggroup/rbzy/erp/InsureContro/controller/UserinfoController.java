package com.wdzggroup.rbzy.erp.InsureContro.controller;


import com.wdzggroup.rbzy.erp.InsureContro.common.Canst;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResponseUtils;
import com.wdzggroup.rbzy.erp.InsureContro.common.ResultVO;
import com.wdzggroup.rbzy.erp.InsureContro.service.IUserinfoService;
import io.swagger.annotations.Api;
import org.apache.ibatis.annotations.Param;
import org.apache.tomcat.util.bcel.Const;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author rbzy
 * @since 2020-12-29
 */
@Api(tags = "登陆")
@RestController
@RequestMapping("/InsureContro/userinfo")
public class UserinfoController {
    @Autowired
    IUserinfoService iUserinfoService;
    @GetMapping
    public ResultVO<String> login(@RequestParam(name="userName" ,required = true) String userName, @RequestParam(name="Password" ,required = true) String Password){
        String login = iUserinfoService.Login(userName, Password);
        return ResponseUtils.responseResult(login, Canst.RespMsg.SUCCESS);
    }
}
