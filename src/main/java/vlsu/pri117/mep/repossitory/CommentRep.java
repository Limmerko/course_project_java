package vlsu.pri117.mep.repossitory;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Comment;

public interface CommentRep extends CrudRepository<Comment, Long> {
}
