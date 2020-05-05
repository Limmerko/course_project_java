package vlsu.pri117.mep.repossitory;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Problem;

public interface ProblemRep extends CrudRepository<Problem, Long> {
}
