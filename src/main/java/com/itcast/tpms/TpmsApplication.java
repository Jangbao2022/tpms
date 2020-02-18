package com.itcast.tpms;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.itcast.tpms.mapper")
public class TpmsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpmsApplication.class, args);
    }

}
