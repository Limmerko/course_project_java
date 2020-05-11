package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.service.NewsService;

import javax.validation.Valid;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
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
    public RedirectView createNews(@ModelAttribute("news") News news,  BindingResult result, ModelMap modelMap){
        // тута сервис
        news = newsService.save(news);
        return new RedirectView("/news/"+news.getId());
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
        modelMap.addAttribute("news", news);
        return "news/getNews";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        // тута сервис
        newsService.delete(id);
        return "страница";
    }
}
