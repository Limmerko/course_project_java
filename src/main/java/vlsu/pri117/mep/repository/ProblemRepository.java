package vlsu.pri117.mep.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;

import java.util.List;

public interface ProblemRepository extends CrudRepository<Problem, Long> {
    //@Query("SELECT pr FROM #{#entityName} pr WHERE pr.category = category AND pr.status != status ")
    List<Problem> findByCategoryAndStatusNotAndStatusNot(CategoriesProblem category, StatusProblem status, StatusProblem status1);
    List<Problem> findByStatusNot(StatusProblem status);

    List<Problem> findByStatus(StatusProblem status);

    List<Problem> findProblemsByStatusOrStatus(StatusProblem status, StatusProblem status1);

    List<Problem> findProblemsByAuthor(User user);
}
