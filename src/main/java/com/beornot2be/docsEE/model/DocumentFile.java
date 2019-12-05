package com.beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Document_File")
public class DocumentFile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_file_id")
    private int document_file_id;

    public void setDocument_file_id(int document_file_id) {
        this.document_file_id = document_file_id;
    }

    public int getDocument_file_id() {
        return document_file_id;
    }

    @Column(name = "document_id", nullable = false, unique=false)
    private int document_id;

    public void setDocument_id(int document_id) {
        this.document_id = document_id;
    }

    public int getDocument_id() {
        return document_id;
    }

    @Column(name = "title", nullable = false)
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at", updatable = false, insertable = false)
    private Date created_at;

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }


    @Column(name = "link", nullable = false)
    private String link;

    public void setLink(String link) {
        this.link = link;
    }

    public String getLink() {
        return link;
    }

    @Column(name = "file_type_id", nullable = false, unique=false)
    private int type;

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    @ManyToOne
    @JoinColumn(name="document_id", nullable=false, insertable = false, updatable = false)
    private Document document;

}
