package vlsu.pri117.mep.service;

import vlsu.pri117.mep.model.Comment;

import java.util.List;

public interface CommentService {

    Comment save(Comment comment);

    List<Comment> findAll();

    Comment findOne(Long id);

    void delete(Long id);
}
