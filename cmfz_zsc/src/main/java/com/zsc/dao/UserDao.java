package com.zsc.dao;

import com.zsc.dto.UserCount;
import com.zsc.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository
public interface UserDao extends Mapper<User> {

    @Select("select * from cmfz_user where YEARWEEK(DATE_FORMAT(create_date,'%Y-%m-%d')) = YEARWEEK(NOW())-#{a}")
    List<User> queryRegisterCount(@Param("a") int a);

    @Select("SELECT province as name,COUNT(id) as value FROM cmfz_user WHERE sex = #{sex} GROUP BY province")
    List<UserCount> queryUserProvinceCount(@Param("sex") String sex);


}
