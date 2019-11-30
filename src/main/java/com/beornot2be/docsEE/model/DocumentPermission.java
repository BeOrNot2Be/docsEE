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


    @Column(name = "author_id", nullable = false, unique=false)
    private int author_id;

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    @Column(name = "document_id", nullable = false, unique=false)
    private int document_id;

    public void setDocument_id(int author_id) {
        this.author_id = author_id;
    }

    public int getDocument_id() {
        return author_id;
    }


    @Column(name = "dependant_user_id", nullable = false, unique=false)
    private int dependant_user_id;

    public void setDependant_user_id(int dependant_user_id) {
        this.dependant_user_id = dependant_user_id;
    }

    public int getDependant_user_id() {
        return dependant_user_id;
    }

    @Column(name = "permission_type_id", nullable = false, unique=false)
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

}
