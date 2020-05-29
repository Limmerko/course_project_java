package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
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
    public String indexFilter(@RequestParam(value = "category",defaultValue = "", required = false) String category,
                              ModelMap modelMap) {
        List<CategoriesProblem> categoriesProblems = new ArrayList<CategoriesProblem>
                (Arrays.asList(CategoriesProblem.values()));

        modelMap.addAttribute("categories", categoriesProblems);
        if (!category.isEmpty()){
            CategoriesProblem cat = CategoriesProblem.valueOf(category);
            String str = "Отображаются проблемы в категории: " + cat.getDescription();
            modelMap.addAttribute("str", str);
            modelMap.addAttribute("problems", problemService.findByCategoryAndStatusNotAndStatusNot(cat, StatusProblem.UNDER_CONSIDERATION, StatusProblem.REJECTED));
        }
        else
            modelMap.addAttribute("problems", problemService.findProblemsByStatusOrStatus(StatusProblem.NOT_RESOLVED, StatusProblem.RESOLVED));
        return "main";
    }

}
