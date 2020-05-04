package vlsu.pri117.mep.web.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repossitory.UserRep;


@Controller
public class RouteController {

    @Autowired
    private UserRep _userRep;

    @GetMapping("/index")
    public String index() {
        User user = new User();
        user.setLogin("login");
        user.setPassword("password");
        user.setRole(Roles.USER);
        _userRep.save(user);
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
