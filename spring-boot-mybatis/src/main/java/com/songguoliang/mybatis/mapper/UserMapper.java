package com.songguoliang.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.songguoliang.mybatis.entity.User;

/**
 * @Description
 * @Author sgl
 * @Date 2018-05-02 15:02
 */
public interface UserMapper {

	List<User> getUsers();
	 
	@Insert( "<script>"+ "insert into tbl_user(user_name, user_age, sex,address,phone,hobby,flag) values "+
			   "<foreach collection='users' item='user' index='index' separator=','>"+
			      "(#{user.userName}, #{user.userAge}, #{user.sex}, #{user.address},#{user.phone},#{user.hobby},#{user.flag})"+ 
			   "</foreach>" +
			  "</script>" 
			)
	int addUsers(@Param(value = "users") List<User> users);
	
	@Delete("delete from tbl_user where flag =#{flag} ")
	int deleteUsers(@Param(value = "flag") int flag);
	
	
	@Select("select count(1) from tbl_user ")
	int selectCount();
	
	@Select("select * from tbl_user  limit #{startRow},#{limit}")
	List<User> selectUserByPage(@Param(value = "startRow") int startRow,@Param(value = "limit") int limit);
	
	
	
	
}
