package com.example.demo.services;

import com.example.demo.bean.OperatingAccount;

import java.util.Map;

public interface OperatingAccountServices {
    Map<String, Object> getAllAccounts(); // 01_获取所有账号信息

    Map<String, Object> getOperatingAccountByUsername(OperatingAccount oa); // 02_按照用户名获取账号信息

    Map<String, Object> getOperatingAccountByUserid(OperatingAccount oa); // 03_按照ID获取账号信息

    Integer addOperationalAccount(OperatingAccount oa); // 04_新增账号

    Integer deleteOperationalAccountByUserid(OperatingAccount oa);  // 05_按照ID删除账号

    Integer updateOperationalAccountByUsername(OperatingAccount oa);    //06_按照用户名更新账号信息

    Integer updateOperationalAccountStatusByUserid(OperatingAccount oa);    // 07_按照ID更新账号状态

    String getMaxUserid();  // 08_获取最大用户编号

    Integer resetOperationalAccountPasswordByUserid(OperatingAccount oa); // 09_按照ID重置密码

    Map<String, Object> getOperationalAccountByMultipleConditions(OperatingAccount oa); // 10_多条件查询
}
