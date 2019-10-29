package com.example.demo.repository;

import com.example.demo.data.Chat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ChatRepository implements IChatRepository {

    private final JdbcTemplate jdbc;

    @Autowired
    public ChatRepository(JdbcTemplate jdbc){
        this.jdbc = jdbc;
    }

    @Override
    public int insert(String userName, String msgBody){
        String sql = "insert into chat values (?, ?)";
        int n = jdbc.update(sql, userName, msgBody);
        return n;
    }

    @Override
    public List<Chat> find(){

        String sql = "select user_name, msg_body from chat";

        List<Chat> users = jdbc.query(sql,new BeanPropertyRowMapper<>(Chat.class), new Object[]{});

        return users;
    }
}
