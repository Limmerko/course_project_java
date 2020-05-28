package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final Cloudinary cloudinary;

    private final PhotoService photoService;

    private final UserRepository userRepository;

    private final AsyncService asyncService;


    public NewsController(NewsService newsService, Cloudinary cloudinary, PhotoService photoService, UserRepository userRepository, CommentService commentService, UserRepository userRepository1, AsyncService asyncService) {
        this.newsService = newsService;
        this.cloudinary = cloudinary;
        this.photoService = photoService;
        this.userRepository = userRepository1;
        this.asyncService = asyncService;
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
        List<byte[]> filesToUpload = asyncService.convertFilesToBytes(news.getFiles());
        asyncService.saveNewsAsync(news, filesToUpload);
        return new RedirectView("/news");
    }

    @GetMapping("/news/edit/{id}")
    public String getNewsForUpdate(@PathVariable Long id, ModelMap modelMap){
        News news = newsService.findOne(id);
        modelMap.addAttribute("news", news);
        return "/news/updateNews";
    }

    @PostMapping("/news/edit/{id}")
    public RedirectView updateNews(@ModelAttribute("news") News newsNew){
        News newsOld = newsService.findOne(newsNew.getId());
        newsOld.setDescription(newsNew.getDescription());
        newsOld.setTitle(newsNew.getTitle());
        newsService.save(newsOld);
        return new RedirectView("/news/" + newsOld.getId());
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
