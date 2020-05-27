package vlsu.pri117.mep.web.rest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.service.ProblemService;
import vlsu.pri117.mep.service.UserService;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private final ProblemService problemService;
    private final UserService userService;

    public RestController(ProblemService problemService, UserService userService) {
        this.problemService = problemService;
        this.userService = userService;
    }

    @PostMapping("/problems/upvote")
    public Long addCountOfVotes(@RequestParam(value = "authorLogin", required = true) String login,
                                @RequestParam(value = "idProblem", required = true) Long idProblem){
        Problem problem = problemService.findOne(idProblem);
        User user = userService.findByLogin(login);
        if (!user.getVotedProblems().contains(problem)){
            problem.incrementCountOfVotes();
            user.getVotedProblems().add(problem);
            userService.save(user);
            problemService.save(problem);
            return problem.getCountOfVotes();
        }
        return -1L;
    }
}

