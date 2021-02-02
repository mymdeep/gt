package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.bean.OperatingAccount;
import com.example.demo.services.OperatingAccountServices;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
public class OperatingAccountController {
    @Resource
    private OperatingAccountServices operatingAccountservices;

    // 01_获取所有账号信息
    @RequestMapping("/getAllAccounts")
    public Map<String, Object> getAllAccounts() {
        return this.operatingAccountservices.getAllAccounts();
    }

    // 02_按照用户编号获取账号信息
    @RequestMapping("/getOperatingAccountByUserid")
    public Map<String, Object> getOperatingAccountByid(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserid(userid);
        return this.operatingAccountservices.getOperatingAccountByUserid(oa);
    }

    // 03_新增账号
    @RequestMapping("/addOperationalAccount")
    public Map<String, Object> addOperationalAccount(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String str_maxid = operatingAccountservices.getMaxUserid();
        int int_maxid = Integer.parseInt(str_maxid.substring(2));
        String userid = "OA" + (int_maxid + 1);
        String username = hashMap.get("username").toString();
        String password = "123456";
        String sex = hashMap.get("sex").toString();
        Integer astatus = 1;
        OperatingAccount oa = new OperatingAccount();
        oa.setUsername(username);
        oa.setUserid(userid);
        oa.setPassword(password);
        oa.setSex(sex);
        oa.setAstatus(astatus);
        Integer lines = operatingAccountservices.addOperationalAccount(oa);
        if (lines > 0) {
            List<OperatingAccount> list = Arrays.asList(oa);
            Map<String, Object> map = new HashMap<>();
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            System.out.println(map);
            return map;
        } else {
            return null;
        }
    }

    // 04_删除账号
    @RequestMapping("/deleteOperationalAccountByUserid")
    public Integer deleteOperationalAccountByUserid(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserid(userid);
        return this.operatingAccountservices.deleteOperationalAccountByUserid(oa);
    }

    // 05_启用/禁用账号
    @RequestMapping("/updateOperationalAccountStatusByUserid")
    public Integer updateOperationalAccountStatusByruserID(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        Integer astatus = Integer.parseInt(hashMap.get("astatus").toString());
        OperatingAccount oa = new OperatingAccount();
        oa.setUserid(userid);
        oa.setAstatus(astatus);
        return this.operatingAccountservices.updateOperationalAccountStatusByUserid(oa);
    }

    // 06_按照用户编号重置账号密码
    @RequestMapping("/resetOperationalAccountPasswordByUserid")
    public Integer resetOperationalAccountPasswordByUserid(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String userid = hashMap.get("userid").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUserid(userid);
        return this.operatingAccountservices.resetOperationalAccountPasswordByUserid(oa);
    }

    // 07_按照用户名更新账号信息
    @RequestMapping("/updateOperationalAccountByUsername")
    public Integer updateOperationalAccountByusername(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String sex = hashMap.get("sex").toString();
        OperatingAccount oa = new OperatingAccount();
        oa.setUsername(username);
        oa.setSex(sex);
        return this.operatingAccountservices.updateOperationalAccountByUsername(oa);
    }

    // 08_多条件查询
    @RequestMapping("/getOperationalAccountByMultipleConditions")
    public Map<String, Object> getOperationalAccountByMultipleConditions(@RequestBody String data) {
        HashMap hashMap = JSON.parseObject(data, HashMap.class);
        System.out.println(hashMap);
        String username = hashMap.get("username").toString();
        String userid = hashMap.get("userid").toString();
        String sex = hashMap.get("sex").toString();
        switch (sex) {
            case "2":
                sex = "('M', 'F')";
                break;
            case "1":
                sex = "('M')";
                break;
            case "0":
                sex = "('F')";
                break;
        }
        Integer astatus = Integer.parseInt(hashMap.get("astatus").toString());
        if (astatus == 2) {
            astatus = null;
        }
        OperatingAccount oa = new OperatingAccount();
        oa.setUsername(username);
        oa.setUserid(userid);
        oa.setSex(sex);
        oa.setAstatus(astatus);
        return this.operatingAccountservices.getOperationalAccountByMultipleConditions(oa);
    }
}
