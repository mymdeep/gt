package com.example.demo.dao;

import com.example.demo.bean.OperatingAccount;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface OperatingAccountDao {
    // 获取所有账号信息，按ID从小到大排序
    @Select("select * from oat order by substr(userID,3)+0 asc")
    List<OperatingAccount> getAllAccounts();

    // 通过用户名获取账号信息
    @Select("select * from oat where username=#{username}")
    List<OperatingAccount> getOperatingAccountByUsername(OperatingAccount oa);

    //通过用户编号获取账号信息
    @Select("select * from oat where userid=#{userid}")
    List<OperatingAccount> getOperatingAccountByUserid(OperatingAccount oa);

    // 增加账号
    @Insert(
        "insert into oat values(#{username},#{userid},#{password},#{sex},${astatus})")
    Integer addOperationalAccount(OperatingAccount oa);

    // 删除账号
    @Delete("delete from oat where userid=#{userid}")
    Integer deleteOperationalAccountByUserid(OperatingAccount oa);

    // 启用/禁用账号
    @Update("update oat set aStatus=#{astatus} where userid=#{userid}")
    Integer updateOperationalAccountStatusByUserid(OperatingAccount oa);

    // 修改账号信息
    @Update("update oat set sex=#{sex} where username=#{username}")
    Integer updateOperationalAccountByUsername(OperatingAccount oa);

    // 获取最大用户编号
    @Select("select MAX(userid) as userid from oat")
    List<OperatingAccount> getMaxuserID();

    // 重置密码
    @Update("update oat set PASSWORD='123456' where userid=#{userid}")
    Integer resetOperationalAccountPasswordByUserid(OperatingAccount oa);

    // 多条件查询，见OperatingAccountDao.xml
    List<OperatingAccount> getOperationalAccountByMultipleConditions(OperatingAccount oa);
}
