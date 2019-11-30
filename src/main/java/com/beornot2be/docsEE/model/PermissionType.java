package com.beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Permission_Type")
public class PermissionType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "permission_type_id")
    private int permission_type_id;

    public void setPermission_type_id(int permission_type_id) {
        this.permission_type_id = permission_type_id;
    }

    public int getPermission_type_id() {
        return permission_type_id;
    }

    @Column(name = "title", nullable = false)
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

}

