package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Request;

public interface RequestRepository extends CrudRepository<Request, Long> {
}
