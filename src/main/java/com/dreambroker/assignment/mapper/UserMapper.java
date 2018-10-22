package com.dreambroker.assignment.mapper;

import com.dreambroker.assignment.model.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {

	@Select("SELECT * FROM user WHERE name = #{name}")
	User findUserByName(String name);

	@Insert("INSERT INTO user(name, password) VALUES (#{username}, #{password})")
	void createUser(String username, String password);
}