package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.service.UserService;

@Controller
public class AdminController {

    private final UserService userService;

    public AdminController(UserService userService) {
        this.userService = userService;
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
}
