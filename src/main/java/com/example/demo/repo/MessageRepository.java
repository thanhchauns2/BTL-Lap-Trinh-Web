package com.example.demo.repo;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.demo.entities.*;

@Repository
public class MessageRepository {

    private final JdbcTemplate jdbcTemplate;

    public MessageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Message> findByReceiveId(String email) {
        String sql = "SELECT * FROM message WHERE receive_email = ?";
        List<Message> messages = jdbcTemplate.query(sql, new Object[]{email}, (rs, rowNum) -> new Message(
        		rs.getLong("message_id"),
        		rs.getString("send_email"),
        		rs.getString("receive_email"),
                rs.getString("content")
        ));
        return messages;
    }
    
    public List<Message> findBySendId(String email) {
        String sql = "SELECT * FROM message WHERE send_email = ?";
        List<Message> messages = jdbcTemplate.query(sql, new Object[] {email}, (rs, rowNum) -> new Message(
        		rs.getLong("message_id"),
        		rs.getString("send_email"),
        		rs.getString("receive_email"),
                rs.getString("content")
        ));
        return messages;
    }
    
    public Message save(Message message) {
        String sql = "INSERT INTO message (message_id, send_email, receive_email, content) VALUES (?, ?, ?, ?)";
        jdbcTemplate.update(sql, message.getMessageId(), message.getSendEmail(), message.getReceiveEmail(), message.getContent());
        return message;
    }
}
