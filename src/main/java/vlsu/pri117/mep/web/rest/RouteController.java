package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.impl.NewsServiceImpl;


@Controller
public class RouteController {

    private final NewsService newsService;

    public RouteController(NewsService newsService, NewsServiceImpl newsServiceimpl) {
        this.newsService = newsService;
    }

    @GetMapping("/")
    public String index() {
        // тута сервис
        return "main";
    }

    // перенес
    /*@GetMapping("/news")
    public String news() {
        // тута сервис
        return "news";
    }*/

    @GetMapping("/problem-info")
    public String problemInfo() {
        // тута сервис
        return "problemInfo";
    }

    @GetMapping("/problems")
    public String problems() {
        // тута сервис
        return "problems";
    }

    @GetMapping("/report-problem")
    public String reportProblem() {
        // тута сервис
        return "reportProblem";
    }

}
