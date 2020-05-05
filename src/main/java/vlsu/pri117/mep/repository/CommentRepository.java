package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Comment;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
