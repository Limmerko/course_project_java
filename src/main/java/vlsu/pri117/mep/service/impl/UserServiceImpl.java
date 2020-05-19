package vlsu.pri117.mep.service.impl;

import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void delete(Long id) {

    }
}
