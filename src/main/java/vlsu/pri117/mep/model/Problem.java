package vlsu.pri117.mep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import vlsu.pri117.mep.model.enums.StatusProblem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "problem_id")
    private Long id;

    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH.mm.ss")
    private LocalDateTime date;

    private String description;

    private String mainPhoto;

    private Long countOfVotes;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.STRING)
    private StatusProblem status;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Request> requests;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public Problem() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public StatusProblem getStatus() {
        return status;
    }

    public void setStatus(StatusProblem status) {
        this.status = status;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
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
        Problem problem = (Problem) o;
        return Objects.equals(id, problem.id) &&
                Objects.equals(address, problem.address) &&
                Objects.equals(date, problem.date) &&
                Objects.equals(description, problem.description) &&
                Objects.equals(mainPhoto, problem.mainPhoto) &&
                Objects.equals(countOfVotes, problem.countOfVotes) &&
                Objects.equals(author, problem.author) &&
                status == problem.status &&
                Objects.equals(comments, problem.comments) &&
                Objects.equals(requests, problem.requests) &&
                Objects.equals(creationDate, problem.creationDate) &&
                Objects.equals(photos, problem.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, date, description, mainPhoto, countOfVotes, author, status, comments, requests, creationDate, photos);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", mainPhoto='" + mainPhoto + '\'' +
                ", countOfVotes=" + countOfVotes +
                ", author=" + author +
                ", status=" + status +
                ", comments=" + comments +
                ", requests=" + requests +
                ", creationDate=" + creationDate +
                ", photos=" + photos +
                '}';
    }
}
