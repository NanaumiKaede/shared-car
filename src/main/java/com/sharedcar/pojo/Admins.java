package com.sharedcar.pojo;

import java.io.Serializable;

public class Admins implements Serializable {
    private static final long serialVersionUID = 8194584077900062802L;
    private Integer id;
    private String adminName;
    private String password;

    public Admins() {
    }

    @Override
    public String toString() {
        return "Admins{" +
                "id=" + id +
                ", adminName='" + adminName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAdminName() {
        return adminName;
    }

    public void setAdminName(String adminName) {
        this.adminName = adminName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
