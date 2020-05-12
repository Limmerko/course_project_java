package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Photo;
import vlsu.pri117.mep.repository.PhotoRepository;
import vlsu.pri117.mep.service.PhotoService;

import java.util.List;

@Service
public class PhotoServiceImpl implements PhotoService {
    private final PhotoRepository photoRepository;

    public PhotoServiceImpl(PhotoRepository photoRepository) {
        this.photoRepository = photoRepository;
    }

    @Override
    public Photo save(Photo photo) {
        return photoRepository.save(photo);
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
