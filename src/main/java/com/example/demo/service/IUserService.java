package com.example.demo.service;

import com.example.demo.data.AuthUser;

import java.util.List;

public interface IUserService {

    //登録ユーザ、パスワード
    public String registerUser(String userName,String userPass);
    //削除ユーザ
    public boolean removeUser(String userName);

    /**
     * ユーザ名とパスワードをデータベースに照合する
     *
     * @param userName ユーザー名
     * @param userPass パスワード
     * @return 照合成功であれば<code>true</code>, 照合失敗は<code>false</code>
     */
    public boolean existsUser(String userName, String userPass);

    /**
     * ユーザ名とパスワードの一覧を、AuthUser型のリストで検索する
     *
     * @return AuthUser型のListインスタンス
     */
    public List<AuthUser> findAuthUsers();

}
