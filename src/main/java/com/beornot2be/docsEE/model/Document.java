package com.beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Optional;
import java.util.Set;


@Entity
@Table(name = "Document")
public class Document implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "document_id")
    private int document_id;


    public void setDocument_id(int id) {
        this.document_id = id;
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

    @JoinColumn(name = "author_id", nullable = false, unique=false)
    private int author_id;

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
        return author_id;
    }

    @Column(name = "description", nullable = false)
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }



    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_at",  columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP", updatable = false, insertable = false)
    private Date created_at;


    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getCreated_at() {
        return created_at;
    }


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edited_date", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP", insertable = false)
    private Date edited_date;

    public void setEdited_date(Date edited_date) {
        this.edited_date = edited_date;
    }

    public Date getEdited_date() {
        return edited_date;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "document")
    private Set<DocumentFile> files;

    public Optional getFiles() {
        return Optional.ofNullable(files);
    }

    public void setFiles( Set<DocumentFile> files) {
        this.files = files;
    }
}
