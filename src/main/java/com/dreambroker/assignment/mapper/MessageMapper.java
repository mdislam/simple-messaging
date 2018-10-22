package com.dreambroker.assignment.mapper;


import com.dreambroker.assignment.model.Message;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.Date;
import java.util.List;

@Mapper
public interface MessageMapper {

	@Insert("INSERT INTO message(userId, message, sent) VALUES (#{userId}, #{message}, #{date})")
	void createMessage(long userId, String message, Date date);

	@Select("SELECT name as sender, message, sent FROM message INNER JOIN user ON message.userId = user.id ORDER BY sent")
	List<Message> getMessages();
}
