package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.impl.NewsServiceImpl;


@Controller
public class RouteController {

    private final NewsService newsService;
    private final ProblemService problemService;

    public RouteController(NewsService newsService, NewsServiceImpl newsServiceimpl, ProblemService problemService) {
        this.newsService = newsService;
        this.problemService = problemService;
    }

    @GetMapping("/")
    public String index(ModelMap modelMap) {
        modelMap.addAttribute("categories",
                CategoriesProblem.values());
        modelMap.addAttribute("problems", problemService.findAll());
        return "main";
    }

    @GetMapping("/boot")
    public String boot() {
        // тута сервис
        return "boot";
    }

    // перенесен в NewsController
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

    /*@GetMapping("/problems")
    public String problems() {
        // тута сервис
        return "problems";
    }*/

    // перенесен в ProblemsController
    @GetMapping("/report-problem")
    public String reportProblem() {
        // тута сервис
        return "reportProblem";
    }

}
