package vlsu.pri117.mep.web.rest;

import com.cloudinary.Cloudinary;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.util.Collections;
import java.util.List;

@Controller
public class NewsController {

    private final NewsService newsService;

    private final Cloudinary cloudinary;

    private final PhotoService photoService;

    private final UserRepository userRepository;

    private final AsyncService asyncService;
    private final CommentService commentService;


    public NewsController(NewsService newsService, Cloudinary cloudinary, PhotoService photoService, UserRepository userRepository, CommentService commentService, UserRepository userRepository1, AsyncService asyncService, CommentService commentService1) {
        this.newsService = newsService;
        this.cloudinary = cloudinary;
        this.photoService = photoService;
        this.userRepository = userRepository1;
        this.asyncService = asyncService;
        this.commentService = commentService1;
    }

    @GetMapping("/news")
    public String getAllNews(ModelMap modelMap) {
        var news = newsService.findAll();
        Collections.sort(news, Collections.reverseOrder());
        modelMap.addAttribute("news", news);
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

    @PostMapping("/news/edit/photo/delete")
    public String deletePhotoInNews(@RequestParam(required = true, defaultValue = "") Long photoId,
                                       @RequestParam(required = true, defaultValue = "") Long newsId) {
        photoService.delete(photoId);
        String redirect = "/news/edit/" + newsId;
        return "redirect:" + redirect;
    }

    @PostMapping("/news/edit/photo/main")
    public String setMainPhotoInNews(@RequestParam(required = true, defaultValue = "") Long photoId,
                                        @RequestParam(required = true, defaultValue = "") Long newsId) {
        News news = newsService.findOne(newsId);
        news.setMainPhoto(photoService.findOne(photoId).getUrl());
        newsService.save(news);
        String redirect = "/news/edit/" + newsId;
        return "redirect:" + redirect;
    }

    @GetMapping("/news/deleteComment/{newsId}")
    public String getDeleteComment(@PathVariable Long newsId,
                                   @RequestParam(required = true) Long commentId){
        commentService.delete(commentId);
        String redirect = "/news/" + newsId;
        return "redirect:" + redirect;
    }
}
