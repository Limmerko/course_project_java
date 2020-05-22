package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.impl.NewsServiceImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


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

        List<CategoriesProblem> categoriesProblems = new ArrayList<CategoriesProblem>
                (Arrays.asList(CategoriesProblem.values()));
        modelMap.addAttribute("categories", categoriesProblems);
        modelMap.addAttribute("problems", problemService.findAll());
        return "main";
    }


}
