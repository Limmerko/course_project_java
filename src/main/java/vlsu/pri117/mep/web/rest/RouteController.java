package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.service.NewsService;


@Controller
public class RouteController {

    private final NewsService newsService;

    public RouteController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/index")
    public String index() {
        News news = new News();
        newsService.save(news);
        return "/index";
    }

    @GetMapping("/news")
    public String news() {
        return "/news";
    }

    @GetMapping("/problem-info")
    public String problemInfo() {
        return "problemInfo";
    }

    @GetMapping("/problems")
    public String problems() {
        return "problems";
    }

    @GetMapping("/report-problem")
    public String reportProblem() {
        return "reportProblem";
    }

}
