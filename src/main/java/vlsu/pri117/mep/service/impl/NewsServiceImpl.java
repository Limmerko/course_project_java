package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.News;
import vlsu.pri117.mep.repository.NewsRepository;
import vlsu.pri117.mep.service.NewsService;

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
        return newsRepository.save(news);
    }

    @Override
    public List<News> findAll() {
        return null;
    }

    @Override
    public News findOne(Long id) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
