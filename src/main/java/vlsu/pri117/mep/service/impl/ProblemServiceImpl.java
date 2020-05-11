package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.repository.ProblemRepository;
import vlsu.pri117.mep.service.ProblemService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    public ProblemServiceImpl( ProblemRepository problemRepository1) {
        this.problemRepository = problemRepository1;
    }

    @Override
    public Problem save(Problem problem) {
        problem.setCreationDate(LocalDateTime.now());
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
}
