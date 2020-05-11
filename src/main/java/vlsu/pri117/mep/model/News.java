package vlsu.pri117.mep.model;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "news")
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "news_id")
    private Long id;

    private String title;

    private String mainPhoto;

    private String description;

    private Long countOfVotes;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @Column(name = "creation_date")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH.mm.ss")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "news", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public News() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getCountOfVotes() {
        return countOfVotes;
    }

    public void setCountOfVotes(Long countOfVotes) {
        this.countOfVotes = countOfVotes;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public List<Photo> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photo> photos) {
        this.photos = photos;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        News news = (News) o;
        return Objects.equals(id, news.id) &&
                Objects.equals(title, news.title) &&
                Objects.equals(mainPhoto, news.mainPhoto) &&
                Objects.equals(description, news.description) &&
                Objects.equals(countOfVotes, news.countOfVotes) &&
                Objects.equals(comments, news.comments) &&
                Objects.equals(creationDate, news.creationDate) &&
                Objects.equals(photos, news.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, mainPhoto, description, countOfVotes, comments, creationDate, photos);
    }

    @Override
    public String toString() {
        return "News{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", mainPhoto='" + mainPhoto + '\'' +
                ", description='" + description + '\'' +
                ", countOfVotes=" + countOfVotes +
                ", comments=" + comments +
                ", creationDate=" + creationDate +
                ", photos=" + photos +
                '}';
    }
}
