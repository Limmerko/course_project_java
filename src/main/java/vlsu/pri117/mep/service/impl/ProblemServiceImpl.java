package vlsu.pri117.mep.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.CategoriesProblem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.repository.ProblemRepository;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProblemServiceImpl implements ProblemService {

    private final ProblemRepository problemRepository;

    private static final Logger log = LogManager.getLogger();

    public ProblemServiceImpl(ProblemRepository problemRepository) {
        this.problemRepository = problemRepository;
    }

    @Override
    public Problem save(Problem problem) {
        log.info("Request to save problem with id = " + problem.getId());
        if (problem.getId() == null){
            problem.setCreationDate(LocalDateTime.now());
            problem.setStatus(StatusProblem.UNDER_CONSIDERATION);
        }
        if (problem.getMainPhoto() == null &&
            problem.getPhotos() != null &&
            problem.getPhotos().size() != 0) {
            problem.setMainPhoto(problem.getPhotos().get(0).getUrl());
        }
        return problemRepository.save(problem);
    }

    @Override
    public List<Problem> findAll() {
        log.info("Request to find all problems");
        return (List<Problem>)problemRepository.findAll();
    }

    @Override
    public Problem findOne(Long id) {
        log.info("Request to find one problem with id = " + id);
        return problemRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete problem with id = " + id);
        problemRepository.deleteById(id);
    }

    @Override
    public List<Problem> findByCategoryAndStatusNotAndStatusNot(CategoriesProblem category, StatusProblem status, StatusProblem status1) {
        log.info("Request to find one problem with category = " + category + ", status = " + status);
        return problemRepository.findByCategoryAndStatusNotAndStatusNot(category, status, status1);
    }

    @Override
    public List<Problem> findByStatusNot(StatusProblem status) {
        log.info("Request to find one problem with status = " + status);
        return problemRepository.findByStatusNot(status);
    }

    @Override
    public List<Problem> findByStatus(StatusProblem status) {
        log.info("Request to find one problem with status = " + status);
        return problemRepository.findByStatus(status);
    }

    @Override
    public List<Problem> findProblemsByStatusOrStatus(StatusProblem status, StatusProblem status1) {
        log.info("Request to find one problem with status = " + status + " or status = " + status1);
        return problemRepository.findProblemsByStatusOrStatus(status, status1);
    }
}
