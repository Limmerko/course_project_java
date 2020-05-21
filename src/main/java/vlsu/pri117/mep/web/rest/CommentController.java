package vlsu.pri117.mep.web.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.UserService;

@Controller
public class CommentController {

    private final CommentService commentService;

    private final UserService userService;

    public CommentController(CommentService commentService, UserService userService) {
        this.commentService = commentService;
        this.userService = userService;
    }

    @PostMapping("/news/comments/new")
    public RedirectView createCommentNews(@ModelAttribute("comment") Comment comment) {
        commentService.save(comment);
        return new RedirectView( "/news/" + comment.getNews().getId());
    }
}
