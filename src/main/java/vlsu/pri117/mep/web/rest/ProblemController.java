package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.ProblemService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final Cloudinary cloudinary;

    public ProblemController(Cloudinary cloudinary, ProblemService problemService) {
        this.cloudinary = cloudinary;
        this.problemService = problemService;
    }

    @PostMapping("/problems/new")
    //@RequestParam("file") MultipartFile file
    public RedirectView createProblem(@ModelAttribute("problem") Problem problem){
        //modelMap.addAttribute("file", file);
/*        try {
            cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        this.getProblem(problemService.save(problem).getId());*/
        return new RedirectView( "/problems/" + problemService.save(problem).getId());
    }

    @GetMapping("/problems/new")
    public ModelAndView createProblem(ModelMap modelMap){
        modelMap.addAttribute("categories",
                CategoriesProblem.values());
        return new ModelAndView("problems/createProblem", "problem", new Problem());
    }

    @GetMapping("/problems/{id}")
    public String getProblem(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("problem", problemService.findOne(id));
        return "problems/getProblem";
    }

    @GetMapping("/problems")
    public String getProblems(ModelMap modelMap){
        modelMap.addAttribute("problems", problemService.findAll());
        return "problems/problems";
    }
}
