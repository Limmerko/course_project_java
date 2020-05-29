package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.UserService;
import vlsu.pri117.mep.service.impl.AsyncService;

import java.util.*;

@Controller
public class ProblemController {

    private final ProblemService problemService;
    private final PhotoService photoService;
    private final AsyncService asyncService;
    private final UserService userService;
    private final CommentService commentService;



    public ProblemController(ProblemService problemService, PhotoService photoService, AsyncService asyncService, UserService userService, CommentService commentService) {
        this.problemService = problemService;
        this.photoService = photoService;
        this.asyncService = asyncService;
        this.userService = userService;
        this.commentService = commentService;
    }



    @PostMapping("/problems/new")
    public String createProblem(@ModelAttribute("problem") Problem problem,
                                      @RequestParam(value = "authorLogin", defaultValue ="", required = false) String login){
        if(!login.isEmpty()){
            var user = userService.findByLogin(login);
            problem.setAuthor(user);
        }
        List<byte[]> filesToUpload = asyncService.convertFilesToBytes(problem.getFiles());
        asyncService.saveProblemAsync(problem, filesToUpload);
        return "/problems/thankyouForm";
    }

    @GetMapping("/problems/new")
    public ModelAndView createProblem(ModelMap modelMap){
        modelMap.addAttribute("categories", CategoriesProblem.values());
        return new ModelAndView("problems/createProblem", "problem", new Problem());
    }

    @GetMapping("/problems/{id}")
    public String getProblem(@PathVariable Long id, ModelMap modelMap){
        Problem problem = problemService.findOne(id);
        if (problem.getPhotos().size() > 0) {
            problem.getPhotos().remove(0);
        }
        modelMap.addAttribute("problem", problem);
        modelMap.addAttribute("newComment", new Comment());
        return "problems/getProblem";
    }

    @GetMapping("/problems")
    public String getProblems(@RequestParam(defaultValue = "", required = false) String category,
                              @RequestParam(defaultValue="false",required = false) Boolean votesFilter,
                              ModelMap modelMap){
        List<CategoriesProblem> categoriesProblems = new ArrayList<CategoriesProblem>
                (Arrays.asList(CategoriesProblem.values()));

        modelMap.addAttribute("categories", categoriesProblems);
        if (!category.isEmpty()){
            CategoriesProblem cat = CategoriesProblem.valueOf(category);
            String str = "Отображаются проблемы в категории: " + cat.getDescription();
            modelMap.addAttribute("str", str);
            var problems =problemService.findByCategoryAndStatusNotAndStatusNot(cat,
                    StatusProblem.UNDER_CONSIDERATION,
                    StatusProblem.REJECTED);
            if (votesFilter)
                Collections.sort(problems, Collections.reverseOrder());
            modelMap.addAttribute("problems", problems);
        }
        else{
            var problems = problemService.findProblemsByStatusOrStatus(StatusProblem.NOT_RESOLVED, StatusProblem.RESOLVED);
            if (votesFilter)
                Collections.sort(problems, Collections.reverseOrder());
            modelMap.addAttribute("problems", problems);
        }
        return "problems/problems";
    }

    @PostMapping("/problems/usersProblems")
    public String getUsersProblems(@RequestParam(value = "authorLogin", required = true) String login,
                                   ModelMap modelMap){
        var user = userService.findByLogin(login);
        modelMap.addAttribute("problems", problemService.findProblemsByAuthor(user));
        return "problems/problems";
    }

    @GetMapping("/problems/edit/{id}")
    public String getProblemForUpdate(@PathVariable Long id, ModelMap modelMap){
        modelMap.addAttribute("problem", problemService.findOne(id));
        modelMap.addAttribute("categories", CategoriesProblem.values());
        modelMap.addAttribute("statuses", StatusProblem.values());
        return "problems/updateProblem";
    }

    @GetMapping("/problems/deleteComment/{problemId}")
    public String getDeleteComment(@PathVariable Long problemId,
                                   @RequestParam(required = true) Long commentId){
        commentService.delete(commentId);
        String redirect = "/problems/" + problemId;
        return "redirect:" + redirect;
    }

    @PostMapping("/problems/edit/{id}")
    public RedirectView updateProblem(@ModelAttribute("problem") Problem problemNew, ModelMap modelMap){
        Problem problemOld = problemService.findOne(problemNew.getId());
        problemOld.setAddress(problemNew.getAddress());
        problemOld.setStatus(problemNew.getStatus());
        problemOld.setCategory(problemNew.getCategory());
        problemOld.setDescription(problemNew.getDescription());
        problemService.save(problemOld);

        return new RedirectView( "/admin/problems");
    }

    @PostMapping("/problems/edit/photo/delete")
    public String deletePhotoInProblem(@RequestParam(required = true, defaultValue = "") Long photoId,
                                       @RequestParam(required = true, defaultValue = "") Long problemId) {
        photoService.delete(photoId);
        String redirect = "/problems/edit/" + problemId;
        return "redirect:" + redirect;
    }

    @PostMapping("/problems/edit/photo/main")
    public String setMainPhotoInProblem(@RequestParam(required = true, defaultValue = "") Long photoId,
                                        @RequestParam(required = true, defaultValue = "") Long problemId) {
        Problem problem = problemService.findOne(problemId);
        problem.setMainPhoto(photoService.findOne(photoId).getUrl());
        problemService.save(problem);
        String redirect = "/problems/edit/" + problemId;
        return "redirect:" + redirect;
    }
}
