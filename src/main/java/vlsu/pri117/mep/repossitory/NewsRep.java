package vlsu.pri117.mep.repossitory;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.News;

public interface NewsRep extends CrudRepository<News, Long> {
}
