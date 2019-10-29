package com.example.demo.service;

import com.example.demo.data.Chat;

import java.util.List;

public interface IChatService {

    public void registerChat(String userName, String msgBody);

    /**
     * ユーザ名とチャット内容の一覧を、Chat型のリストで検索
     * @return Chat型のListインスタンス
     *
     */
    public List<Chat> findChat();
}
