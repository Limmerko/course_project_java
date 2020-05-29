package vlsu.pri117.mep.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.model.enums.StatusProblem;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AsyncService {

    private final ProblemService problemService;

    private final PhotoService photoService;

    private final NewsService newsService;

    private static final Logger log = LogManager.getLogger();

    public AsyncService(ProblemService problemService, PhotoService photoService, NewsService newsService) {
        this.problemService = problemService;
        this.photoService = photoService;
        this.newsService = newsService;
    }

    @Async
    public void saveProblemAsync(Problem problem, List<byte[]> filesToUpload) {
        problem = problemService.save(problem);
        photoService.addPhotosToProblem(problem, filesToUpload);
    }

    @Async
    public void saveNewsAsync(News news, List<byte[]> filesToUpload) {
        news = newsService.save(news);
        photoService.addPhotosToNews(news, filesToUpload);
    }

    public List<byte[]> convertFilesToBytes(MultipartFile[] files){
        log.info("Convert files to bytes");
        List<byte[]> filesToUpload = new ArrayList<>();
        for (int i = 0; i < files.length; i++){
            try {
                filesToUpload.add(files[i].getBytes());
            } catch (IOException e) {
                System.out.println();
            }
        }
        return filesToUpload;
    }
}
