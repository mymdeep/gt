package com.example.demo.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
@ConfigurationProperties(prefix = "oa")
public class OperatingAccount {
    private String username; // 01_用户名_主键
    private String userid; // 02_用户编号_唯一，格式OAxxx，OA代表运营账号，xxx为具体编号
    private String password; // 03_密码
    private String sex; // 04_性别，M为男性，F为女性
    private Integer astatus = 1; // 05_账号状态，0为禁用，1为启用，默认启用
}
