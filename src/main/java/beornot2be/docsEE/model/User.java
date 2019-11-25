package beornot2be.docsEE.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Optional;
import java.util.Set;

@Entity
@Table(name = "User")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private int user_id;

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getUser_id() {
        return user_id;
    }

    @Column(name = "name", nullable = false, unique=false)
    private String name;


    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Column(name = "username", nullable = false, unique=true)
    private String username;

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    @Column(name = "email", nullable = false, unique=false)
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "hash", nullable = false, unique=false)
    private String hash;

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    @Column(name = "verified", nullable = false, unique=false)
    private Boolean verified;

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "author")
    private Set<DocumentPermision> permissionsAuthor;

    public Optional getPermissionsAuthor() {
        return Optional.ofNullable(permissionsAuthor);
    }

    public void setPermissionsAuthor( Set<DocumentPermision> permissions) {
        this.permissionsAuthor = permissions;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.ALL, mappedBy = "dependant")
    private Set<DocumentPermision> permissionsDependant;

    public Optional getPermissionDependant() {
        return Optional.ofNullable(permissionsDependant);
    }

    public void setPermissionDependant( Set<DocumentPermision> permissions) {
        this.permissionsDependant = permissions;
    }

}

