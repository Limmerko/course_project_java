package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class RouteController {

    @GetMapping("/index")
    public String index() {
        return "/index";
    }

    @GetMapping("/news")
    public String news() {
        return "/news";
    }

    @GetMapping("/problem-info")
    public String problemInfo() {
        return "problemInfo";
    }

    @GetMapping("/problems")
    public String problems() {
        return "problems";
    }

    @GetMapping("/report-problem")
    public String reportProblem() {
        return "reportProblem";
    }

}
