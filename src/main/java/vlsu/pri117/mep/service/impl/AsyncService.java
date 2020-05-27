package vlsu.pri117.mep.service.impl;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.File;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AsyncService {

    private final ProblemService problemService;
    private final PhotoService photoService;

    public AsyncService(ProblemService problemService, PhotoService photoService) {
        this.problemService = problemService;
        this.photoService = photoService;
    }

    @Async
    public void saveAsync(Problem problem, List<byte[]> filesToUpload) {
        if (problem.getId() == null){
            problem.setCreationDate(LocalDateTime.now());
            problem.setStatus(StatusProblem.UNDER_CONSIDERATION);
        }
        if (problem.getPhotos() != null) {
            problem.setMainPhoto(problem.getPhotos().get(0).getUrl());
        }
        problem = problemService.save(problem);
        photoService.addPhotosToProblem(problem, filesToUpload);
    }
}
