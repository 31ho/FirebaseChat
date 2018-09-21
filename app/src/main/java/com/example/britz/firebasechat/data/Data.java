package com.example.britz.firebasechat.data;

public class Data {

    String user_name;
    String message;
    String time;

    public Data(){}

    public Data(String name, String message, String time){
        user_name = name;
        this.message = message;
        this.time = time;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getMessage() {
        return message;
    }

    public String getTime() {
        return time;
    }
}
