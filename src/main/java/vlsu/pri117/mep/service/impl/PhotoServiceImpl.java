package vlsu.pri117.mep.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.repository.PhotoRepository;
import vlsu.pri117.mep.service.NewsService;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ProblemService problemService;
    private final NewsService newsService;
    private final Cloudinary cloudinary;

    public PhotoServiceImpl(PhotoRepository photoRepository, ProblemService problemService, NewsService newsService, Cloudinary cloudinary) {
        this.photoRepository = photoRepository;
        this.problemService = problemService;
        this.newsService = newsService;
        this.cloudinary = cloudinary;
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void addPhotosToProblem(Problem problem, List<byte[]> files){
        if (files.size() == 0)
            return;
        List<Photo> photos = new ArrayList<Photo>();
        try {
            for (byte[] file : files) {
                Map uploadResult = cloudinary.uploader().upload(file, ObjectUtils.emptyMap());
                Photo photo = new Photo();
                photo.setUrl((String)uploadResult.get("url"));
                photo.setProblem(problem);
                photos.add(save(photo));
            }
            problem.setPhotos(photos);
        } catch (IOException e){
            //Logger
            System.out.println(e.getMessage());
        }
        problemService.save(problem);
    }

    @Override
    public void addPhotosToNews(News news, List<byte[]> files){
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
            //Logger
            System.out.println(e.getMessage());
        }
        newsService.save(news);
    }
    @Override
    public List<Photo> findAll() {
        return null;
    }

    @Override
    public Photo findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
