package com.hearkensummertask.hearkensummertask.bean;

public class ChildrenJson {
    Integer id;
    String text;
    String iconCls;
    String cuser;
    String ctime;
    String utime;
    String luser;
    String handle;

    public ChildrenJson() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls;
    }

    public String getCuser() {
        return cuser;
    }

    public void setCuser(String cuser) {
        this.cuser = cuser;
    }

    public String getCtime() {
        return ctime;
    }

    public void setCtime(String ctime) {
        this.ctime = ctime;
    }

    public String getUtime() {
        return utime;
    }

    public void setUtime(String utime) {
        this.utime = utime;
    }

    public String getLuser() {
        return luser;
    }

    public void setLuser(String luser) {
        this.luser = luser;
    }

    public String getHandle() {
        return handle;
    }

    public void setHandle(String handle) {
        this.handle = handle;
    }

    public ChildrenJson(Integer id, String text, String iconCls, String cuser, String ctime, String utime, String luser, String handle) {
        this.id = id;
        this.text = text;
        this.iconCls = iconCls;
        this.cuser = cuser;
        this.ctime = ctime;
        this.utime = utime;
        this.luser = luser;
        this.handle = handle;
    }
}
