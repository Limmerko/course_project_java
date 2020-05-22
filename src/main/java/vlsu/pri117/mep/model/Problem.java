package vlsu.pri117.mep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.web.multipart.MultipartFile;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.Roles;
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

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy")
    private LocalDateTime resolveDate;

    private String description;

    private String mainPhoto;

    @Transient
    private MultipartFile[] files;

    private Long countOfVotes;

    @Enumerated(EnumType.STRING)
    private CategoriesProblem category;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

    @Enumerated(EnumType.STRING)
    private StatusProblem status;

    @OneToMany(mappedBy = "problem", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH.mm.ss")
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

    public LocalDateTime getResolveDate() {
        return resolveDate;
    }

    public void setResolveDate(LocalDateTime resolveDate) {
        this.resolveDate = resolveDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
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

    public CategoriesProblem getCategory() {
        return category;
    }

    public void setCategory(CategoriesProblem category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Problem problem = (Problem) o;
        return Objects.equals(id, problem.id) &&
                Objects.equals(address, problem.address) &&
                Objects.equals(resolveDate, problem.resolveDate) &&
                Objects.equals(description, problem.description) &&
                Objects.equals(mainPhoto, problem.mainPhoto) &&
                Objects.equals(countOfVotes, problem.countOfVotes) &&
                category == problem.category &&
                Objects.equals(author, problem.author) &&
                status == problem.status &&
                Objects.equals(comments, problem.comments) &&
                Objects.equals(creationDate, problem.creationDate) &&
                Objects.equals(photos, problem.photos);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, address, resolveDate, description, mainPhoto, countOfVotes, category, author, status, comments, creationDate, photos);
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", resolveDate=" + resolveDate +
                ", description='" + description + '\'' +
                ", mainPhoto='" + mainPhoto + '\'' +
                ", countOfVotes=" + countOfVotes +
                ", category=" + category +
                ", author=" + author +
                ", status=" + status +
                ", comments=" + comments +
                ", creationDate=" + creationDate +
                ", photos=" + photos +
                '}';
    }
}
