package vlsu.pri117.mep.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.repository.NewsRepository;
import vlsu.pri117.mep.service.NewsService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    private final NewsRepository newsRepository;

    private static final Logger log = LogManager.getLogger();

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News save(News news) {
        log.info("Request to save news with id = " + news.getId());
        if (news.getId() == null)
            news.setCreationDate(LocalDateTime.now());
        if (news.getMainPhoto() == null &&
            news.getPhotos() != null &&
            news.getPhotos().size() != 0) {
            news.setMainPhoto(news.getPhotos().get(0).getUrl());
        }
        return newsRepository.save(news);
    }

    @Override
    public List<News> findAll() {
        log.info("Request to find all news");
        return (List<News>)newsRepository.findAll();
    }

    @Override
    public News findOne(Long id) {
        log.info("Request to find one news with id = " + id);
        return newsRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete news with id = " + id);
        newsRepository.deleteById(id);
    }
}
