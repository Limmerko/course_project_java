package vlsu.pri117.mep.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.repository.PhotoRepository;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;

    private final ProblemService problemService;

    private final NewsService newsService;

    private final Cloudinary cloudinary;

    private static final Logger log = LogManager.getLogger();

    public PhotoServiceImpl(PhotoRepository photoRepository,
                            ProblemService problemService,
                            NewsService newsService,
                            Cloudinary cloudinary) {
        this.photoRepository = photoRepository;
        this.problemService = problemService;
        this.newsService = newsService;
        this.cloudinary = cloudinary;
    }

    @Override
    public Photo save(Photo photo) {
        log.debug("Request to save photo with id = " + photo.getId());
        return photoRepository.save(photo);
    }

    @Override
    public void addPhotosToProblem(Problem problem, List<byte[]> files) {
        log.info("Request to add photo to problem with id = " + problem.getId());
        if (files == null) {
            List<Photo> defaultPhoto = new ArrayList<>();
            Photo photo = new Photo();
            photo.setUrl("http://res.cloudinary.com/konoha/image/upload/v1590765019/jsizyqj0yzbetg1dzyp2.png");
            photo.setProblem(problem);
            defaultPhoto.add(save(photo));
            problem.setPhotos(defaultPhoto);
        } else {
            List<Photo> photos = new ArrayList<Photo>();
            try {
                for (byte[] file : files) {
                    Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                    Photo photo = new Photo();
                    photo.setUrl((String) uploadResult.get("url"));
                    photo.setProblem(problem);
                    photos.add(save(photo));
                }
                problem.setPhotos(photos);
            } catch (IOException e) {
                log.error("Error to add photo to problem with id = " + problem.getId() +
                        ". Message: " + e.getMessage());
                System.out.println(e.getMessage());
            }
        }
        problemService.save(problem);
    }

    @Override
    public void addPhotosToNews(News news, List<byte[]> files) {
        log.info("Request to add news to problem with id = " + news.getId());
        if (files.size() == 0)
            return;
        List<Photo> photos = new ArrayList<Photo>();
        try {
            for (var file : files) {
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                Photo photo = new Photo();
                photo.setUrl((String)uploadResult.get("url"));
                photo.setNews(news);
                photos.add(save(photo));
            }
            news.setPhotos(photos);
        } catch (IOException e){
            log.error("Error to add news to problem with id = " + news.getId() +
                    ". Message: " +e.getMessage());
            System.out.println(e.getMessage());
        }
        newsService.save(news);
    }
    @Override
    public List<Photo> findAll() {
        log.info("Request to find all photos");
        return (List<Photo>)photoRepository.findAll();
    }

    @Override
    public Photo findOne(Long id) {
        log.info("Request to find one photo with id = " + id);
        return photoRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete photo with id = " + id);
        photoRepository.delete(this.findOne(id));
    }
}
