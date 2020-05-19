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
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final PhotoService photoService;

    public NewsController(NewsService newsService, PhotoService photoService) {
        this.newsService = newsService;
        this.photoService = photoService;
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
        news.getPhotos().remove(0);
        modelMap.addAttribute("news", news);
        return "news/getNews";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        newsService.delete(id);
        return "страница";
    }
}
