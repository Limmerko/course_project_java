package vlsu.pri117.mep.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import vlsu.pri117.mep.model.enums.StatusProblem;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "problems")
public class Problem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id")
    private Long id;
    private String address;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd.MM.yyyy HH.mm.ss")
    private LocalDateTime date;
    private String description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Problem problem = (Problem) o;

        if (!id.equals(problem.id)) return false;
        if (!author.equals(problem.author)) return false;
        return creationDate.equals(problem.creationDate);

    }

    @Override
    public int hashCode() {
        int result = id.hashCode();
        result = 31 * result + author.hashCode();
        result = 31 * result + creationDate.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Problem{" +
                "id=" + id +
                ", address='" + address + '\'' +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", countOfVotes=" + countOfVotes +
                ", author=" + author +
                ", status=" + status +
                ", comments=" + comments +
                ", requests=" + requests +
                ", creationDate=" + creationDate +
                '}';
    }
}
