package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.service.ProblemService;

import java.io.File;
import java.io.IOException;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final Cloudinary cloudinary;

    public ProblemController(Cloudinary cloudinary, ProblemService problemService) {
        this.cloudinary = cloudinary;
        this.problemService = problemService;
    }

    @PostMapping("/problems")
    //@RequestParam("file") MultipartFile file
    public void createProblem(@RequestBody Problem problem){
        // тута сервис

//        modelMap.addAttribute("file", file);
//        try {
//            cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
//        } catch (IOException e){
//            System.out.println(e.getMessage());
//        }
        this.getProblem(problemService.save(problem).getId());
    }

    @GetMapping("/problems/{id}")
    public String getProblem(@PathVariable Long id){
        problemService.findOne(id);
        return "problems/problemInfo";
    }

    @GetMapping("/problems")
    public String getProblems(ModelMap modelMap){
        modelMap.addAttribute("problems", problemService.findAll());
        return "problems/problems";
    }
}
