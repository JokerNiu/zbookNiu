package com.wdzggroup.rbzy.erp.InsureContro.Interceptor;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.wdzggroup.rbzy.erp.InsureContro.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
@Component
public class MyInterceptor implements HandlerInterceptor {
    @Autowired
    RedisService redisService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
     response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Origin", request.getHeader("Origin"));
        response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept");
        response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, DELETE, TRACE, OPTIONS, PATCH");
        //从request对象中获取到Auth信息
        String sessionId = request.getHeader("token");
        if(StringUtils.isBlank(sessionId)){
            return false;
        } else if(redisService.hasKey("token")){
            String token = redisService.get("token");
            if(sessionId.equals(token)){
                return true;
            }else{
                return false;
            }
        }else {
            response.sendRedirect("/InsureContro/userinfo");
            return false;
        }


        //开始进行业务判断操作
       // System.out.println("拦截器处理中...");
        //未登陆，则返回一个未登陆的Result
      /*  if (sessionId != null){
           // Result result = Result.fail(ErrorEnum.NOT_AUTH);
            PrintWriter writer = response.getWriter();
         //   writer.print(JSON.toJSONString(result));
            writer.flush();
            writer.close();
            return false;
        }*/
       // return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("拦截器处理后...");
    }

}
