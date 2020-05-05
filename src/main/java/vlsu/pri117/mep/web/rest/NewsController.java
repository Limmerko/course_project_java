package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class NewsController {

    @PostMapping("/news")
    public String createNews(){
        // тута сервис
        return "страница";
    }

    @PutMapping("/news")
    public String updateNews(){
        // тута сервис
        return "страница";
    }

    @GetMapping("/news/{id}")
    public String getNews(@PathVariable Long id) {
        // тута сервис
        return "страница";
    }

    @DeleteMapping("news/{id}")
    public String deleteNews(@PathVariable Long id) {
        // тута сервис
        return "страница";
    }
}
