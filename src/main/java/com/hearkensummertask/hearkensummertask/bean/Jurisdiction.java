package com.hearkensummertask.hearkensummertask.bean;

import javax.persistence.*;

@Entity
@Table(name = "Jurisdiction")
public class Jurisdiction {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer jid;  //定一个主键 并没有什么用
    String userid;   //用户id
    Integer fileid;   //文件id
    Integer jurisdiction;   //用户权限

    public Jurisdiction(Integer fileid, Integer jurisdiction) {

        this.fileid = fileid;
        this.jurisdiction = jurisdiction;
    }

    @Override
    public String toString() {
        return "Jurisdiction{" +
                "jid=" + jid +
                ", userid='" + userid + '\'' +
                ", fileid=" + fileid +
                ", jurisdiction=" + jurisdiction +
                '}';
    }

    public Integer getJid() {
        return jid;
    }

    public void setJid(Integer jid) {
        this.jid = jid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public Integer getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(Integer jurisdiction) {
        this.jurisdiction = jurisdiction;
    }

    public Jurisdiction() {
    }
}
