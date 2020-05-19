package vlsu.pri117.mep.service.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.Transformation;
import com.cloudinary.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.Problem;
import vlsu.pri117.mep.repository.PhotoRepository;
import vlsu.pri117.mep.service.PhotoService;
import vlsu.pri117.mep.service.ProblemService;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class PhotoServiceImpl implements PhotoService {

    private final PhotoRepository photoRepository;
    private final ProblemService problemService;
    private final Cloudinary cloudinary;

    public PhotoServiceImpl(PhotoRepository photoRepository, ProblemService problemService, Cloudinary cloudinary) {
        this.photoRepository = photoRepository;
        this.problemService = problemService;
        this.cloudinary = cloudinary;
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
    }

    @Override
    public void addPhotosToProblem(Problem problem){
        List<Photo> photos = new ArrayList<Photo>();
        try {
            for (MultipartFile file : problem.getFiles()) {
                Map uploadResult = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap(
                        "eager", new Transformation().width(200).height(300).crop("fill")
                ));
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
