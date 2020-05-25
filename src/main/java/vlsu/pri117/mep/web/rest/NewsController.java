package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final Cloudinary cloudinary;

    private final PhotoService photoService;

    private final UserRepository userRepository;


    public NewsController(NewsService newsService, Cloudinary cloudinary, PhotoService photoService, UserRepository userRepository, CommentService commentService, UserRepository userRepository1) {
        this.newsService = newsService;
        this.cloudinary = cloudinary;
        this.photoService = photoService;
        this.userRepository = userRepository1;
    }

    @GetMapping("/news")
    public String getAllNews(ModelMap modelMap) {
        modelMap.addAttribute("news", newsService.findAll());
        return "news/news";
    }

    @GetMapping("/news/new")
    public ModelAndView getCreateNews(){
        return new ModelAndView("news/createNews", "news", new News());
    }

    @PostMapping("/news/new")
    public RedirectView createNews(@ModelAttribute("news") News news){
        news = newsService.save(news);
        photoService.addPhotosToNews(news);
        return new RedirectView("/news/"+news.getId());
    }

    @PutMapping("/news")
    public String updateNews(@RequestBody News news){
        newsService.save(news);
        return "страница";
    }

    @GetMapping("/news/{id}")
    public String getNews(@PathVariable Long id, ModelMap modelMap) {
        News news = newsService.findOne(id);
        if (news.getPhotos().size() > 0) {
            news.getPhotos().remove(0);
        }
        modelMap.addAttribute("news", news);
        modelMap.addAttribute("newComment", new Comment());
        return "news/getNews";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.delete(id);
        return "страница";
    }
}
