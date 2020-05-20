package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Comment;
import vlsu.pri117.mep.repository.CommentRepository;
import vlsu.pri117.mep.service.CommentService;
import vlsu.pri117.mep.service.UserService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {

    private final CommentRepository commentRepository;

    private final UserService userService;

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment save(Comment comment) {
        comment.setCreationDate(LocalDateTime.now());
        comment.setAuthor(userService.findOne(1L));
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        return null;
    }

    @Override
    public Comment findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {
    }
}
