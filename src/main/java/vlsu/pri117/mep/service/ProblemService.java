package vlsu.pri117.mep.service;

import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;

import java.util.List;

public interface ProblemService {
    Problem save(Problem problem);

    List<Problem> findAll();

    Problem findOne(Long id);

    void delete(Long id);

    List<Problem> findByCategoryAndStatusNotAndStatusNot(CategoriesProblem category, StatusProblem status, StatusProblem status1);

    List<Problem> findByStatusNot(StatusProblem status);

    List<Problem> findByStatus(StatusProblem status);

    List<Problem> findProblemsByStatusOrStatus(StatusProblem status, StatusProblem status1);

    List<Problem> findProblemsByAuthor(User user);
}
