package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlsu.pri117.mep.model.User;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByLogin(String login);
}
