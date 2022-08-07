package com.wdzggroup.rbzy.erp.InsureContro;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@MapperScan("com.wdzggroup.rbzy.erp.InsureContro.mapper")
@ComponentScan(basePackages = {"com.wdzggroup.rbzy.erp.InsureContro.bean"})
@SpringBootApplication
@EnableSwagger2
@EnableScheduling
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
