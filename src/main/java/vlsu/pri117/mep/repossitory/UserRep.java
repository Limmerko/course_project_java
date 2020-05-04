package vlsu.pri117.mep.repossitory;

import org.springframework.data.repository.CrudRepository;
import vlsu.pri117.mep.model.User;

public interface UserRep extends CrudRepository<User, Long> {
}
