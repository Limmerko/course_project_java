package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.impl.NewsServiceImpl;

import java.util.*;


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
        List<Problem> problems;
        String str;
        modelMap.addAttribute("categories", categoriesProblems);
        if (!category.isEmpty()){
            CategoriesProblem cat = CategoriesProblem.valueOf(category);
            str = "Отображаются проблемы в категории: " + cat.getDescription();
            modelMap.addAttribute("str", str);
            problems = problemService.findByCategoryAndStatusNotAndStatusNot(cat, StatusProblem.UNDER_CONSIDERATION, StatusProblem.REJECTED);

        }
        else
            problems = problemService.findProblemsByStatusOrStatus(StatusProblem.NOT_RESOLVED, StatusProblem.RESOLVED);
        var problemsToHot = problemService.findByStatus(StatusProblem.NOT_RESOLVED);
        Collections.sort(problemsToHot, Collections.reverseOrder());
        if (problemsToHot.size() != 0){
            Problem mostVotedProblem =  problemsToHot.get(0);
            modelMap.addAttribute("mostVotedProblem", mostVotedProblem);
        }

        Collections.sort(problemsToHot, (problem, t1) -> {
            var size1 = problem.getComments().size();
            var size2 = t1.getComments().size();
            return Integer.compare(size1,size2);
        });

        if (problemsToHot.size() != 0){
            Problem mostCommentProblem = problemsToHot.get(problemsToHot.size() - 1);
            modelMap.addAttribute("mostCommentProblem", mostCommentProblem);
        }

        modelMap.addAttribute("problems", problems);

        return "main";
    }

}
