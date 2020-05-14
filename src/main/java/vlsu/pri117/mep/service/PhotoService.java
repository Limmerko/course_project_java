package vlsu.pri117.mep.service;

import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.model.Problem;

import java.util.List;

public interface PhotoService {
    Photo save(Photo photo);

    List<Photo> findAll();

    Photo findOne(Long id);

    void delete(Long id);

    void addPhotosToProblem(Problem problem);
}
