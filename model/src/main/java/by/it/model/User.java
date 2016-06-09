package by.it.model;


import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "USERS")

@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)

public class User implements Serializable{
    private static final long serialVersionUID = 1L;
    private String email;
    private String password;
    private String name;
    private String surname;
    private UserRole role;
    private Set<Comment> comments = new HashSet<Comment>();
    private Set<News> newsSet = new HashSet<News>();

    public User() {
    }
    @Id
    @Column(name = "U_EMAIL", nullable = false)
    public String getEmail() {
        return email;
    }

    @Column(name = "U_ROLE", nullable = false)
    @Enumerated(EnumType.STRING)
    public UserRole getRole() {
        return role;
    }

    @Column(name = "U_NAME", nullable = false)
    public String getName() {
        return name;
    }

    @Column(name = "U_SURNAME", nullable = false)
    public String getSurname() {
        return surname;
    }

    @Column(name = "U_PASSWORD", nullable = false)
    public String getPassword() {
        return password;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }


    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    public Set<News> getNewsSet() {
        return newsSet;
    }



    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

  public void setNewsSet(Set<News> newsSet) {
        this.newsSet = newsSet;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (email != null ? !email.equals(user.email) : user.email != null) return false;
        if (password != null ? !password.equals(user.password) : user.password != null) return false;
        if (name != null ? !name.equals(user.name) : user.name != null) return false;
        return !(surname != null ? !surname.equals(user.surname) : user.surname != null);

    }

    @Override
    public int hashCode() {
        int result = email != null ? email.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (surname != null ? surname.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
