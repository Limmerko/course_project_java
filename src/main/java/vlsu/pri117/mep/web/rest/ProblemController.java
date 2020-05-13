package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final PhotoService photoService;
    private final Cloudinary cloudinary;


    public ProblemController(ProblemService problemService, PhotoService photoService, Cloudinary cloudinary) {
        this.problemService = problemService;
        this.photoService = photoService;
        this.cloudinary = cloudinary;
    }


    @PostMapping("/problems/new")
    public void createProblem(@ModelAttribute("problem") Problem problem){
        problem = problemService.save(problem);
        photoService.addPhotosToProblem(problem);
        //return new RedirectView( "/problems/" + problem.getId());
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
