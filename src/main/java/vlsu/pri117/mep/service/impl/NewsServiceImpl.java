package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.repository.NewsRepository;
import vlsu.pri117.mep.service.NewsService;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class NewsServiceImpl implements NewsService {

    //в этих методах реализуется бизнес логика и вызывается NewsRepository.

    private final NewsRepository newsRepository;

    public NewsServiceImpl(NewsRepository newsRepository) {
        this.newsRepository = newsRepository;
    }

    @Override
    public News save(News news) {
        if (news.getId() == null)
            news.setCreationDate(LocalDateTime.now());
        if (news.getPhotos() != null) {
            news.setMainPhoto(news.getPhotos().get(0).getUrl());
        }
        return newsRepository.save(news);
    }

    @Override
    public List<News> findAll() {
        return (List<News>)newsRepository.findAll();
    }

    @Override
    public News findOne(Long id) {
        return newsRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {
        newsRepository.deleteById(id);
    }
}
