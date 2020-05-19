package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
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

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
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
    public RedirectView createProblem(@ModelAttribute("problem") Problem problem){
        problem = problemService.save(problem);
        photoService.addPhotosToProblem(problem);
        return new RedirectView( "/problems/" + problem.getId());
    }

/*    @PostMapping("/problems/new")
    public RedirectView createProblem(@ModelAttribute("problem") Problem problem) throws IOException {
        problem = problemService.save(problem);
        List<Photo> photos = new ArrayList<>();
        int i = 0;
        for( MultipartFile file : problem.getFiles()){
            InputStream in = new ByteArrayInputStream(file.getBytes());
            BufferedImage image = ImageIO.read(in);
            image = image.getSubimage( 0, 0, 200, 300);
            String dir = "D:/University/JavaProjects/course_project_java/src/main/webapp/photos/problems/" + problem.getId();
            File dirFile = new File(dir);
            dirFile.mkdirs();
            File outfile = new File(dir + "/" +i + ".png");
            try {
                ImageIO.write(image, "png", outfile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Photo photo = new Photo();
            photo.setUrl(outfile.getPath());
            photo.setProblem(problem);
            photos.add(photoService.save(photo));
            i++;
        }
        problemService.save(problem);
        return new RedirectView( "/problems/" +problemService.save(problem).getId());
    }*/


    @GetMapping("/problems/new")
    public ModelAndView createProblem(ModelMap modelMap){
        modelMap.addAttribute("categories",
                CategoriesProblem.values());
        return new ModelAndView("problems/createProblem", "problem", new Problem());
    }

    @GetMapping("/problems/{id}")
    public String getProblem(@PathVariable Long id, ModelMap modelMap){
        Problem problem = problemService.findOne(id);
        problem.getPhotos().remove(0);
        modelMap.addAttribute("problem", problem);
        return "problems/getProblem";
    }

    @GetMapping("/problems")
    public String getProblems(ModelMap modelMap){
        modelMap.addAttribute("problems", problemService.findAll());
        return "problems/problems";
    }

    @GetMapping("/problems/edit/{id}")
    public String getProblemForUpdate(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("problem", problemService.findOne(id));
        return "problems/updateProblem";
    }

    @PostMapping("/problems/edit/{id}")
    public String getProblemForUpdate(@ModelAttribute("problem") Problem problem){
        problemService.save(problem);
        return "problems/updateProblem";
    }
}
