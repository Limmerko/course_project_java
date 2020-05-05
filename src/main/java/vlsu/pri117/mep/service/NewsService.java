package vlsu.pri117.mep.service;

import vlsu.pri117.mep.model.News;

import java.util.List;

public interface NewsService {

    News save(News news);

    List<News> findAll();

    News findOne(Long id);

    void delete(Long id);
}
