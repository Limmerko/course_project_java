package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.NewsRepository;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final Cloudinary cloudinary;

    private final PhotoService photoService;

    private final UserRepository userRepository;

    public NewsController(NewsService newsService, Cloudinary cloudinary, PhotoService photoService, UserRepository userRepository) {
        this.newsService = newsService;
        this.cloudinary = cloudinary;
        this.photoService = photoService;
        this.userRepository = userRepository;
    }

    @GetMapping("/news")
    public String getAllNews(ModelMap modelMap) {
        // тута сервис
        modelMap.addAttribute("news", newsService.findAll());
        return "news/news";
    }

    @GetMapping("/news/new")
    public ModelAndView getCreateNews(){
        // тута сервис
        return new ModelAndView("news/createNews", "news", new News());
    }

    @PostMapping("/news/new")
    public RedirectView createNews(@ModelAttribute("news") News news){
        List<Photo> photos = new ArrayList<>();
        news = newsService.save(news);
        try {
            for (MultipartFile file : news.getFiles() ) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.emptyMap());
                Photo photo = new Photo();
                cloudinary.url().transformation(new Transformation().width(200).height(300).crop("scale")).imageTag((String) uploadResult.get("imageTag"));
                photo.setUrl((String)uploadResult.get("url"));
                photo.setNews(news);
                photos.add(photoService.save(photo));
            }
            news.setPhotos(photos);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return new RedirectView("/news/"+newsService.save(news).getId());
    }

    @PutMapping("/news")
    public String updateNews(@RequestBody News news){
        // тута сервис
        newsService.save(news);
        return "страница";
    }

    @GetMapping("/news/{id}")
    public String getNews(@PathVariable Long id, ModelMap modelMap) {
        // тута сервис
        News news = newsService.findOne(id);
        news.getPhotos().remove(0);
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("comment", new Comment());
        return "news/getNews";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        // тута сервис
        newsService.delete(id);
        return "страница";
    }

    @GetMapping("addUser")
    public void addUser() {
        User user = new User();
        user.setLogin("Vlad");
        user.setPassword("122232");
        user.setRole(Roles.ADMIN);
        userRepository.save(user);
    }
}
