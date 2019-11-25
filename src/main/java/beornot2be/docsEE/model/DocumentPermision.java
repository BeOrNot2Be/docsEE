package beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "Document_Permision")
public class DocumentPermision implements Serializable {
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



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "document_id", nullable = false, unique=false, referencedColumnName = "document_id")
    private Document document;

    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }

    @Column(name = "author_id", nullable = false, unique=false)
    private int author_id;

    public void setAuthor_id(int author_id) {
        this.author_id = author_id;
    }

    public int getAuthor_id() {
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "permission_type_id", nullable = false, unique=false, referencedColumnName = "permission_type_id")
    private PermissionType type;

    public void setType(PermissionType type) {
        this.type = type;
    }

    public PermissionType getType() {
        return type;
    }

    @ManyToOne
    @JoinColumn(name="author_id", nullable=false, insertable = false, updatable = false, referencedColumnName = "user_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="dependant_user_id", nullable=false, insertable = false, updatable = false, referencedColumnName = "user_id")
    private User dependant;

}
