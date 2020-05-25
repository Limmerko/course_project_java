package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    List<Problem> findByCategory(CategoriesProblem category);
}
