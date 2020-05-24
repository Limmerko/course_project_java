package vlsu.pri117.mep.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import vlsu.pri117.mep.model.User;

import java.util.List;

public interface UserService extends UserDetailsService {
    User save(User user);

    List<User> findAll();

    User findOne(Long id);

    void delete(Long id);
}
