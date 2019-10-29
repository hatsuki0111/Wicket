package com.example.demo.data;

import java.io.Serializable;

public class Chat implements Serializable {

    private String userName; //Chatテーブルのuser_name
    private String msgBody;  //Chatテーブルのmsg_body

    public Chat(){
        userName = "";
        msgBody = "";
    }

    public String getUserName(){
        return userName;
    }

    public String getMsgBody(){
        return msgBody;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public void setMsgBody(String msgBody){
        this.msgBody = msgBody;
    }
}
