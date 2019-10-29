package com.example.demo.repository;

import com.example.demo.data.Chat;

import java.util.List;

public interface IChatRepository {

    /**
     * @param userName ユーザ名
     * @param msgBody chatメッセージ
     * @return データベースの更新行数
     */
    public int insert(String userName,String msgBody);

    /**
     *  Chatテーブルのすべての情報を検索する
     *
     * @return  レコードの内容を{@link Chat]の{@link List}で返す
     */
    public List<Chat> find();
}
