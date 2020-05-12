package com.leyou;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author huangjiabao
 * @data 2020/5/7/0007
 * @time 18:21:17
 */
@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.leyou.item.mapper") // mapper接口的包扫描
@ComponentScan("com.leyou.item.*")
public class LyItemService {
    public static void main(String[] args) {
        SpringApplication.run(LyItemService.class,args);
    }
}
