package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.UserService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Controller
public class AdminController {

    private final UserService userService;
    private final ProblemService problemService;

    public AdminController(UserService userService, ProblemService problemService) {
        this.userService = userService;
        this.problemService = problemService;
    }

    @GetMapping("/admin")
    public String userList(ModelMap modelMap) {
        modelMap.addAttribute("allUsers", userService.findAll());
        modelMap.addAttribute("roles", Roles.values());
        return "users/admin";
    }

    @PostMapping("/admin/delete")
    public String deleteUser(@RequestParam(required = true, defaultValue = "") Long userId,
                             @RequestParam(required = true, defaultValue = "") String action,
                             ModelMap modelMap) {
        if (action.equals("delete")) {
            userService.delete(userId);
        }
        return "redirect:/admin";
    }

    /*@GetMapping("admin/gt/{id}")
    public String gtUser(@PathVariable("id") Long id, ModelMap modelMap) {
        // я не знаю зачем этот метод. В сервис я его тоже не добавлял
    }*/

    @GetMapping("/admin/problems")
    public String getProblems(@RequestParam(value = "status",defaultValue = "", required = false) String status, ModelMap modelMap){
        List<StatusProblem> statusesProblem = new ArrayList<StatusProblem>
                (Arrays.asList(StatusProblem.values()));

        modelMap.addAttribute("statuses", statusesProblem);
        if (!status.isEmpty()){
            StatusProblem statusProblem = StatusProblem.valueOf(status);
            String str = "Отображаются проблемы со статусом: " + statusProblem.getDescription();
            modelMap.addAttribute("str", str);
            modelMap.addAttribute("problems", problemService.findByStatus(statusProblem));
        }
        else
            modelMap.addAttribute("problems", problemService.findAll());
        return "admin/problems";
    }
}
