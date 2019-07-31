package com.hearkensummertask.hearkensummertask.bean;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "User")
public class User {
    @Id
    String userid;
    String password;
    String username;
    String yzm;
    String email;

    @Override
    public String toString() {
        return "User{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", username='" + username + '\'' +
                ", yzm='" + yzm + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public User() {
    }

    public User(String userid, String password, String email) {   //这个是注册要用到的
        this.userid = userid;
        this.password = password;
        this.email = email;
    }

    public User(String userid, String password, String username, String yzm, String email) {
        this.userid = userid;
        this.password = password;
        this.username = username;
        this.yzm = yzm;
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getYzm() {
        return yzm;
    }

    public void setYzm(String yzm) {
        this.yzm = yzm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
