package vlsu.pri117.mep.service;

import vlsu.pri117.mep.model.Problem;

import java.util.List;

public interface ProblemService {
    Problem save(Problem problem);

    List<Problem> findAll();

    Problem findOne(Long id);

    void delete(Long id);
}
