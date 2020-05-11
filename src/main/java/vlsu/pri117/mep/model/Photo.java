package vlsu.pri117.mep.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "news_id")
    private News news;

    @ManyToOne
    @JoinColumn(name = "problem_id")
    private Problem problem;

/*    @ManyToOne
    @JoinColumn(name = "request_id")
    private Request request;*/

    private String url;

    public Photo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public News getNews() {
        return news;
    }

    public void setNews(News news) {
        this.news = news;
    }

    public Problem getProblem() {
        return problem;
    }

    public void setProblem(Problem problem) {
        this.problem = problem;
    }

/*    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }*/

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(id, photo.id) &&
                Objects.equals(news, photo.news) &&
                Objects.equals(problem, photo.problem) &&
                Objects.equals(url, photo.url);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, news, problem, url);
    }

    @Override
    public String toString() {
        return "Photo{" +
                "id=" + id +
                ", news=" + news +
                ", problem=" + problem +
                ", url='" + url + '\'' +
                '}';
    }
}
