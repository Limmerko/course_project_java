package vlsu.pri117.mep.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    private static final Logger log = LogManager.getLogger();

    public CommentServiceImpl(CommentRepository commentRepository, UserService userService) {
        this.commentRepository = commentRepository;
        this.userService = userService;
    }

    @Override
    public Comment save(Comment comment) {
        log.info("Request to save comment with id = " + comment.getId());
        comment.setCreationDate(LocalDateTime.now());
        return commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAll() {
        log.info("Request to find all comments");
        return (List<Comment>)commentRepository.findAll();
    }

    @Override
    public Comment findOne(Long id) {
        log.info("Request to find one comment with id = " + id);
        return commentRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete comment with id = " + id);
        commentRepository.delete(commentRepository.findById(id).get());
    }
}
