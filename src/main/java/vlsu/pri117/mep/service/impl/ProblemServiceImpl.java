package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.repository.ProblemRepository;
import vlsu.pri117.mep.service.ProblemService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl( ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public Problem save(Problem problem) {
        if (problem.getId() == null){
            problem.setCreationDate(LocalDateTime.now());
            problem.setStatus(StatusProblem.UNDER_CONSIDERATION);
        }
        if (problem.getPhotos() != null) {
            problem.setMainPhoto(problem.getPhotos().get(0).getUrl());
        }
        return problemRepository.save(problem);
    }

    @Override
    public List<Problem> findAll() {
        return (List<Problem>)problemRepository.findAll();
    }

    @Override
    public Problem findOne(Long id) {
        return problemRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        problemRepository.deleteById(id);
    }

    @Override
    public List<Problem> findByCategory(CategoriesProblem category) {
        return problemRepository.findByCategory(category);
    }
}
