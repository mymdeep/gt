package com.example.demo.services.impl;

import com.example.demo.bean.OperatingAccount;
import com.example.demo.dao.OperatingAccountDao;
import com.example.demo.services.OperatingAccountServices;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class OperatingAccountServicesImpl implements OperatingAccountServices {
    @Resource
    private OperatingAccountDao operatingAccountDao;

    @Override
    public Map<String, Object> getAllAccounts() {
        Map<String, Object> map = new HashMap<>();
        List<OperatingAccount> list = this.operatingAccountDao.getAllAccounts();
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            // 返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Map<String, Object> getOperatingAccountByUsername(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        // 调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getOperatingAccountByUsername(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            // 返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }


    @Override
    public Map<String, Object> getOperatingAccountByUserid(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        // 调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getOperatingAccountByUserid(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            // 返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }

    @Override
    public Integer addOperationalAccount(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.addOperationalAccount(oa);
        System.out.println("受影响的行：" + lines);
        return lines;
    }

    @Override
    public Integer deleteOperationalAccountByUserid(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.deleteOperationalAccountByUserid(oa);
        System.out.println("受影响的行：" + lines);
        return lines;
    }

    @Override
    public Integer updateOperationalAccountStatusByUserid(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.updateOperationalAccountStatusByUserid(oa);
        System.out.println("受影响的行：" + lines);
        return lines;
    }

    @Override
    public String getMaxUserid() {
        List<OperatingAccount> list = this.operatingAccountDao.getMaxuserID();
        return list.get(0).getUserid();
    }

    @Override
    public Integer resetOperationalAccountPasswordByUserid(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.resetOperationalAccountPasswordByUserid(oa);
        System.out.println("受影响的行：" + lines);
        return lines;
    }

    @Override
    public Integer updateOperationalAccountByUsername(OperatingAccount oa) {
        Integer lines = this.operatingAccountDao.updateOperationalAccountByUsername(oa);
        System.out.println("受影响的行：" + lines);
        return lines;
    }

    @Override
    public Map<String, Object> getOperationalAccountByMultipleConditions(OperatingAccount oa) {
        Map<String, Object> map = new HashMap<>();
        // 调用数据访问层的模块
        List<OperatingAccount> list = this.operatingAccountDao.getOperationalAccountByMultipleConditions(oa);
        if (list.size() > 0) {
            map.put("code", "0");
            map.put("list", list);
            map.put("count", list.size());
            map.put("message", "查询信息成功！");
            // 返回其他业务数据
        } else {
            map.put("code", "1");
            map.put("flag", false);
            map.put("count", list.size());
            map.put("message", "抱歉！没有您查询的信息！");
        }
        return map;
    }
}
