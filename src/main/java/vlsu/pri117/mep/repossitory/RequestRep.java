package vlsu.pri117.mep.repossitory;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.Request;

public interface RequestRep extends CrudRepository<Request, Long> {
}
