package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class ProblemController {

    private final Cloudinary cloudinary;

    public ProblemController(Cloudinary cloudinary) {
        this.cloudinary = cloudinary;
    }

    @PostMapping("/problem")
    public String createProblem(@RequestParam("file") MultipartFile file,  ModelMap modelMap){
        // тута сервис
        modelMap.addAttribute("file", file);
        try {
            cloudinary.uploader().upload(new File(file.getOriginalFilename()), ObjectUtils.emptyMap());
        } catch (IOException e){
            System.out.println(e.getMessage());
        }
        return "reportProblem";
    }

    @GetMapping("/problem")
    public String getProblem(){
        // тута сервис
        return "reportProblem";
    }
}
