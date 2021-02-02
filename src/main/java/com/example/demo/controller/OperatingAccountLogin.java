package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.OperatingAccount;
import com.example.demo.services.OperatingAccountServices;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OperatingAccountLogin {
    @Resource
    private OperatingAccountServices operatingAccountServices;

    // 判断是否存在该账号
    @RequestMapping("/hasaccount")
    @ResponseBody
    public boolean hasAccount(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        OperatingAccount operatingAccount = new OperatingAccount();
        String username = hashMap.get("username").toString();
        operatingAccount.setUsername(username);
        Map<String, Object> map =
            this.operatingAccountServices.getOperatingAccountByUsername(operatingAccount);
        if (map.get("code") == "0") {
            return true;
        }
        return false;
    }

    // 检查该账号账号密码是否正确
    @RequestMapping("/checkaccount")
    @ResponseBody
    public int checkAccount(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String password = hashMap.get("password").toString();
        OperatingAccount operatingAccount = new OperatingAccount();
        operatingAccount.setUsername(username);
        Map<String, Object> map =
            this.operatingAccountServices.getOperatingAccountByUsername(operatingAccount);
        int res = 2;
        List<OperatingAccount> list = (List<OperatingAccount>) map.get("list");
        OperatingAccount o = list.get(0);

        if (username.equals(o.getUsername())) {  // 账号正确
            if (password.equals(o.getPassword())) { // 密码正确
                if (o.getAstatus() == 1) { // 账号启用
                    res = 1;
                } else { // 账号禁用
                    res = 0;
                }
            }
        }
        return res;
    }

    // 登录
    @RequestMapping("/login")
    @ResponseBody
    public int login(
        @RequestBody String data, HttpServletRequest request, HttpServletResponse response) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String password = hashMap.get("password").toString();
        OperatingAccount operatingAccount = new OperatingAccount();
        operatingAccount.setUsername(username);
        Map<String, Object> map = this.operatingAccountServices.getOperatingAccountByUsername(operatingAccount);
        List<OperatingAccount> list = (List<OperatingAccount>) map.get("list");
        OperatingAccount o = list.get(0);
        int status = o.getAstatus();    //获取账号状态
        if (username.equals(o.getUsername())) { // 账号正确
            if (password.equals(o.getPassword())) { // 密码正确
                if (status == 1) {
                    // 创建cookie并将成功登录的用户保存在里面
                    Cookie cookie_username = new Cookie("username", username);
                    Cookie cookie_password = new Cookie("password", password);
                    cookie_username.setMaxAge(120); // 设置两分钟有效
                    cookie_password.setMaxAge(120);
                    response.addCookie(cookie_username); // 服务器返回给浏览器cookie以便下次判断
                    response.addCookie(cookie_password);
                }
            }
        }
        // 前端未处理返回值为2的情况
        return (status == 0 || status == 1) ? status : 2;
    }

    // 获取登录用户
    @RequestMapping("/getloginuser")
    @ResponseBody
    public Map<String, Object> getloginuser(
        HttpServletRequest request, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<>();
        String username = "";
        Cookie[] cookies = request.getCookies();    // 获取一个cookies数据，方便后续操作
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) { // 遍历cookies，寻找指定内容
                if (cookie.getName().equals("username")) {
                    username = cookie.getValue();
                }
            }
        }
        map.put("login_username", username);
        return map;
    }

    // 退出
    @RequestMapping("/logout")
    @ResponseBody
    public Integer logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie[] cookies = request.getCookies();    // 获取一个cookies数据，方便后续操作
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) { // 遍历cookies，寻找指定内容
                if (cookie.getName().equals("username") || cookie.getName().equals("password")) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                }
            }
        }
        return 0;
    }
}
