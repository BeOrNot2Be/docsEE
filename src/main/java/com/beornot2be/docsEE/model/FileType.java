package com.beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "File_Type")
public class FileType implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "file_type_id")
    private int file_type_id;

    public void setFile_type_id(int id) {
        this.file_type_id = id;
    }

    public int getFile_type_id() {
        return file_type_id;
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
