package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final PhotoService photoService;
    private final AsyncService asyncService;



    public ProblemController(ProblemService problemService, PhotoService photoService, AsyncService asyncService) {
        this.problemService = problemService;
        this.photoService = photoService;
        this.asyncService = asyncService;
    }



    @PostMapping("/problems/new")
    public RedirectView createProblem(@ModelAttribute("problem") Problem problem){
        List<byte[]> filesToUpload = asyncService.convertFilesToBytes(problem.getFiles());
        asyncService.saveProblemAsync(problem, filesToUpload);
        return new RedirectView( "/problems");
    }

    @GetMapping("/problems/new")
    public ModelAndView createProblem(ModelMap modelMap){
        modelMap.addAttribute("categories", CategoriesProblem.values());
        return new ModelAndView("problems/createProblem", "problem", new Problem());
    }

    @GetMapping("/problems/{id}")
    public String getProblem(@PathVariable Long id, ModelMap modelMap){
        Problem problem = problemService.findOne(id);
        if (problem.getPhotos().size() > 0) {
            problem.getPhotos().remove(0);
        }
        modelMap.addAttribute("problem", problem);
        modelMap.addAttribute("newComment", new Comment());
        return "problems/getProblem";
    }

    @GetMapping("/problems")
    public String getProblems(@RequestParam(value = "category",defaultValue = "", required = false) String category, ModelMap modelMap){
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
        return "problems/problems";
    }

    @GetMapping("/problems/edit/{id}")
    public String getProblemForUpdate(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("problem", problemService.findOne(id));
        modelMap.addAttribute("categories", CategoriesProblem.values());
        modelMap.addAttribute("statuses", StatusProblem.values());
        return "problems/updateProblem";
    }


    @PostMapping("/problems/edit/{id}")
    public RedirectView updateProblem(@ModelAttribute("problem") Problem problemNew, ModelMap modelMap){
        Problem problemOld = problemService.findOne(problemNew.getId());
        problemOld.setAddress(problemNew.getAddress());
        problemOld.setStatus(problemNew.getStatus());
        problemOld.setCategory(problemNew.getCategory());
        problemOld.setDescription(problemNew.getDescription());
        problemService.save(problemOld);

        return new RedirectView( "/admin/problems");
    }


}
