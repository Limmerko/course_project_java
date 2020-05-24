package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.service.UserService;

import javax.validation.Valid;

@Controller
public class RegistrationController {

    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/registration")
    public String registration(ModelMap modelMap) {
        modelMap.addAttribute("userForm", new User());
        return "users/registration";
    }

    @PostMapping("/registration")
    public String addUser(@ModelAttribute("userForm") @Valid User userForm,
                          BindingResult bindingResult, ModelMap modelMap) {
        if (bindingResult.hasFieldErrors()) {
            return "users/registration";
        }
        if (!userForm.getPassword().equals(userForm.getPasswordConfirm())) {
            modelMap.addAttribute("passwordError", "Пароли не совпадают");
            return "users/registration";
        }
        if (userService.save(userForm) == null) {
            modelMap.addAttribute("usernameError", "Пользователь с таким именем уже существует");
            return "users/registration";
        }
        return "redirect:/";
    }
}
