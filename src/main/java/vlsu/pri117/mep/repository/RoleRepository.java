package vlsu.pri117.mep.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import vlsu.pri117.mep.model.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
}
