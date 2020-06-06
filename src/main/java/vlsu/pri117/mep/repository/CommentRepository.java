package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlsu.pri117.mep.model.Comment;

@Repository
public interface CommentRepository extends CrudRepository<Comment, Long> {
}
