package com.hearkensummertask.hearkensummertask.bean;

import javax.persistence.*;

@Entity
@Table(name = "PublicFiles")
public class PublicFiles {
    @Id
            @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer fileid;
    String filename;
    String filepath;
    String createtime;
    String updatetime;
    String owner;
    String lastupdateuserid;

    public PublicFiles( String filename, String filepath, String createtime, String updatetime, String owner, String lastupdateuserid) {

        this.filename = filename;
        this.filepath = filepath;
        this.createtime = createtime;
        this.updatetime = updatetime;
        this.owner = owner;
        this.lastupdateuserid = lastupdateuserid;
    }

    @Override
    public String toString() {
        return "PublicFiles{" +
                "fileid=" + fileid +
                ", filename='" + filename + '\'' +
                ", filepath='" + filepath + '\'' +
                ", createtime='" + createtime + '\'' +
                ", updatetime='" + updatetime + '\'' +
                ", owner='" + owner + '\'' +
                ", lastupdateuserid='" + lastupdateuserid + '\'' +
                '}';
    }

    public PublicFiles() {
    }

    public Integer getFileid() {
        return fileid;
    }

    public void setFileid(Integer fileid) {
        this.fileid = fileid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFilepath() {
        return filepath;
    }

    public void setFilepath(String filepath) {
        this.filepath = filepath;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(String updatetime) {
        this.updatetime = updatetime;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getLastupdateuserid() {
        return lastupdateuserid;
    }

    public void setLastupdateuserid(String lastupdateuserid) {
        this.lastupdateuserid = lastupdateuserid;
    }
}
