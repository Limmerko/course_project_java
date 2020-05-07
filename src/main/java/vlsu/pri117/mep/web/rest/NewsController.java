package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
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
        return "страница";
    }

    @PostMapping("/news")
    public void createNews(@RequestBody News news){
        // тута сервис
        this.getNews(newsService.save(news).getId());
    }

    @PutMapping("/news")
    public String updateNews(@RequestBody News news){
        // тута сервис
        newsService.save(news);
        return "страница";
    }

    @GetMapping("/news/{id}")
    public String getNews(@PathVariable Long id) {
        // тута сервис
        newsService.findOne(id);
        return "страница";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        // тута сервис
        newsService.delete(id);
        return "страница";
    }
}
