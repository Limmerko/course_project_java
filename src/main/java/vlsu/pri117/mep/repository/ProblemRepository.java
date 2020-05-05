package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Problem;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
}
