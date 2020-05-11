package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.service.NewsService;

@Controller
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping("/news")
    public String getAllNews() {
        // тута сервис
        newsService.findAll();
        return "news/news";
    }

    @PostMapping("/news/new")
    public void createNews(@RequestBody News news, ModelMap modelMap){
        // тута сервис
        this.getNews(newsService.save(news).getId(), modelMap);
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
