package com.lab.lotto.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lab.lotto.entity.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 插入用户
     * @param user 用户信息
     * @return 影响行数
     */
    @Insert("INSERT INTO user (uname, stuid, upassword, urole, status, draw_count, draw_limit, ucreate_time, uupdate_time) " +
            "VALUES (#{uname}, #{stuid}, #{upassword}, #{urole}, #{status}, #{drawCount}, #{drawLimit}, #{ucreateTime}, #{uupdateTime})")
    @Options(useGeneratedKeys = true, keyProperty = "uid")
    int insert(User user);

    /**
     * 更新用户
     * @param user 用户信息
     * @return 影响行数
     */
    @Update("UPDATE user SET uname = #{uname}, stuid = #{stuid}, upassword = #{upassword}, " +
            "urole = #{urole}, status = #{status}, draw_count = #{drawCount}, " +
            "draw_limit = #{drawLimit}, uupdate_time = #{uupdateTime} WHERE uid = #{uid}")
    int update(User user);

    /**
     * 根据ID删除用户
     * @param uid 用户ID
     * @return 影响行数
     */
    @Delete("DELETE FROM user WHERE uid = #{uid}")
    int deleteById(Integer uid);

    /**
     * 根据ID查询用户
     * @param uid 用户ID
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE uid = #{uid}")
    User findById(Integer uid);

    /**
     * 查询所有用户
     * @return 用户列表
     */
    @Select("SELECT * FROM user")
    List<User> findAll();

    /**
     * 根据状态查询用户
     * @param status 状态
     * @return 用户列表
     */
    @Select("SELECT * FROM user WHERE status = #{status}")
    List<User> findByStatus(Integer status);

    /**
     * 根据学号查询用户
     * @param stuid 学号
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE stuid = #{stuid}")
    User findByStuId(Integer stuid);

    /**
     * 根据用户名查询用户
     * @param uname 用户名
     * @return 用户信息
     */
    @Select("SELECT * FROM user WHERE uname = #{uname}")
    User findByUname(String uname);

    @Select("SELECT * FROM user WHERE id = #{id}")
    User selectById(@Param("id") Long id);

    @Select("SELECT * FROM user")
    List<User> selectAll();

    @Select("SELECT * FROM user WHERE username = #{username}")
    User selectByUsername(@Param("username") String username);

    @Update("UPDATE user SET urole = #{role}, updateTime = NOW() WHERE uid = #{id}")
    int updateRole(@Param("id") Integer id, @Param("role") String role);
    
    @Update("UPDATE user SET upassword = #{password}, updateTime = NOW() WHERE uid = #{id}")
    int updatePassword(@Param("id") Integer id, @Param("password") String password);
    
    @Update("UPDATE user SET uname = #{name}, updateTime = NOW() WHERE uid = #{id}")
    int updateName(@Param("id") Integer id, @Param("name") String name);
    
    @Update("UPDATE user SET ustatus = #{status}, updateTime = NOW() WHERE uid = #{id}")
    int updateStatus(@Param("id") Integer id, @Param("status") String status);
    
    @Update("UPDATE user SET ustatus = 'active', updateTime = NOW() WHERE ustatus = 'inactive'")
    int updateStatistics();
}