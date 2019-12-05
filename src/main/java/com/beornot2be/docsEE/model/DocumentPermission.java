package com.beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Document_Permission")
public class DocumentPermission implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_permission_id")
    private int document_permission_id;

    public void setDocument_permission_id(int document_permission_id) {
        this.document_permission_id = document_permission_id;
    }

    public int getDocument_permission_id() {
        return document_permission_id;
    }


    @JoinColumn(name = "author_id", nullable = false, unique=false)
    private int author_id;

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    @JoinColumn(name = "document_id", nullable = false, unique=false)
    private int document_id;

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getDocument_id() {
        return author_id;
    }


    @JoinColumn(name = "dependant_user_id", nullable = false, unique=false)
    private int dependant_user_id;

    public void setDependant_user_id(int dependant_user_id) {
        this.dependant_user_id = dependant_user_id;
    }

    public int getDependant_user_id() {
        return dependant_user_id;
    }

    @JoinColumn(name = "permission_type_id", nullable = false, unique=false)
    private int permission_type_id;

    public void setType(int permission_type_id) {
        this.permission_type_id = permission_type_id;
    }

    public int getType() {
        return permission_type_id;
    }

}
