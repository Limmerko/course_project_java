/*
package vlsu.pri117.mep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import vlsu.pri117.mep.model.enums.StatusRequest;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "requests")
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long id;

    private String address;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH.mm.ss")
    private LocalDateTime date;

    private String description;

    @Enumerated(EnumType.STRING)
    private StatusRequest status;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @OneToMany(mappedBy = "request", cascade = CascadeType.ALL)
    private List<Photo> photos;

    public Request() {
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

    public StatusRequest getStatus() {
        return status;
    }

    public void setStatus(StatusRequest status) {
        this.status = status;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Request request = (Request) o;
        return Objects.equals(id, request.id) &&
                Objects.equals(address, request.address) &&
                Objects.equals(date, request.date) &&
                Objects.equals(description, request.description) &&
                status == request.status &&
                Objects.equals(problem, request.problem) &&
                Objects.equals(author, request.author) &&
                Objects.equals(creationDate, request.creationDate) &&
                Objects.equals(photos, request.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, date, description, status, problem, author, creationDate, photos);
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", status=" + status +
                ", problem=" + problem +
                ", author=" + author +
                ", creationDate=" + creationDate +
                ", photos=" + photos +
                '}';
    }
}
*/
