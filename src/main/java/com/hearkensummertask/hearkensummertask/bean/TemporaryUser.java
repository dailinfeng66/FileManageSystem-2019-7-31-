package com.hearkensummertask.hearkensummertask.bean;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TemporaryUser")
public class TemporaryUser {    //注册之后  激活账号之前的存在地
    @Id
    String userid;   //电话号
    String password;
    String uuid;
    String email;


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

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public TemporaryUser() {
    }

    public TemporaryUser(String userid, String password, String uuid, String email) {
        this.userid = userid;
        this.password = password;
        this.uuid = uuid;
        this.email = email;
    }

    @Override
    public String toString() {
        return "TemporaryUser{" +
                "userid='" + userid + '\'' +
                ", password='" + password + '\'' +
                ", uuid='" + uuid + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public TemporaryUser(String userid, String password, String email) {
        this.userid = userid;
        this.password = password;
        this.email = email;
    }
}
