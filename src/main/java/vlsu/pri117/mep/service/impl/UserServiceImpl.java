package vlsu.pri117.mep.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import vlsu.pri117.mep.model.Role;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.RoleRepository;
import vlsu.pri117.mep.repository.UserRepository;
import vlsu.pri117.mep.service.UserService;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private static final Logger log = LogManager.getLogger();

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public User save(User user) {
        log.info("Request to save user with id = " + user.getId());
        if (user.getRoles() == null) {
            user.setRoles(Collections.singleton(new Role(2L, Roles.ROLE_USER.name())));
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User changeRole(Long userId, Roles role) {
        log.info("Request to change role user with id = " + userId + " and role = " + role);
        User user = userRepository.findById(userId).get();
        user.getRoles().add(new Role((long)role.getId(), role.name()));
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        log.info("Request to find all users");
        return (List<User>)userRepository.findAll();
    }

    @Override
    public User findOne(Long id) {
        log.info("Request to find one user with id = " + id);
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElse(new User());
    }

    @Override
    public void delete(Long id) {
        log.info("Request to delete user with id = " + id);
        if (userRepository.findById(id).isPresent()) {
            userRepository.deleteById(id);
        }
    }

    @Override
    public User findByLogin(String login) {
        log.info("Request to find one user with login = " + login);
        return userRepository.findByLogin(login);
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return user;
    }
}
