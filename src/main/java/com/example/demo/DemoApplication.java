package com.example.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.ConfigurableApplicationContext;

@MapperScan(basePackages = {"com.example.demo.dao"})
@SpringBootApplication
@ServletComponentScan
public class DemoApplication {

    public static void main(String[] args) {
        System.out.println("开始启动...");
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        System.out.println("启动成功！");
        /*获取项目本地地址 begin*/
        String port = (String) context.getBean("port");
        String path = (String) context.getBean("path");
        String page = "/login.html";
        String url = "http://localhost:" + port + path + page;
        System.out.println(url);
        String osName = System.getProperty("os.name");
        if (osName.startsWith("Windows")) {
            /*自动打开浏览器 begin*/
            System.out.println("应用已经准备就绪 ... 正在打开浏览器并跳转至指定的页面 ... ");
            try {
                Runtime.getRuntime().exec("cmd /c start " + url);
            } catch (Exception e) {
                e.printStackTrace();
            }
            /*自动打开浏览器 end*/
        }
        /*获取项目本地地址 end*/
    }
}
