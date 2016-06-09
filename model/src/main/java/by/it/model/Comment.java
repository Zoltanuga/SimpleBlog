package by.it.model;


import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "COMMENTS")
public class Comment implements Serializable {
    private static final long serialVersionUID = 3L;

    private int commentId;
    private String content;
    private Date date;
    private User user;
    private News news;

    public Comment() {
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "C_ID", nullable = false)
    @Type(type = "java.lang.Integer")
    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    @Column(name = "C_CONTENT", nullable = false)
    @Type(type = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "C_DATE", columnDefinition = "DATETIME", nullable = false)
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "USERS_U_EMAIL")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "NEWS_N_ID")

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        return !(date != null ? !date.equals(comment.date) : comment.date != null);

    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
