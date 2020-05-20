package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Comment, Long> {
}
