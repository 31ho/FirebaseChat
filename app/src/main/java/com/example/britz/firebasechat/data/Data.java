package com.example.britz.firebasechat.data;

public class Data {

    String user_name;
    String message;
    String time;
    String google_ad_id;

    public Data(){}

    public Data(String google_ad_id, String name, String message, String time){
        this.google_ad_id = google_ad_id;
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

    public String getGoogle_ad_id() {
        return google_ad_id;
    }
}
