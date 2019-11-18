package beornot2be.docsEE.model;

import java.io.Serializable;
import java.util.Date;
import java.sql.Timestamp;
import javax.persistence.*;

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



    @Column(name = "description", nullable = false)
    private String description;

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
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


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "edited_date", insertable = false)
    private Date edited_date;

    public void setEdited_date(Date edited_date) {
        this.edited_date = edited_date;
    }

    public Date getEdited_date() {
        return edited_date;
    }

}
