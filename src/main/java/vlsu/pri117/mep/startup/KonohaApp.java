package vlsu.pri117.mep.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import vlsu.pri117.mep.model.Role;
import vlsu.pri117.mep.model.User;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.RoleRepository;
import vlsu.pri117.mep.repository.UserRepository;

import java.util.Collections;
import java.util.List;

@Component
public class KonohaApp {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public KonohaApp(RoleRepository roleRepository, UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @EventListener
    public void addRoles(ApplicationReadyEvent applicationReadyEvent) {
        Roles[] roles = Roles.values();
        List<Role> rolesBD = (List<Role>) roleRepository.findAll();
        if (roles.length != rolesBD.size()) {
            for (int i = 0; i < roles.length; i++) {
                roleRepository.save(new Role((long) i+1, roles[i].name()));
            }
        }
    }

    @EventListener
    public void addAdmin(ApplicationReadyEvent applicationReadyEvent) {
        User user = new User();
        user.setLogin("admin");
        user.setPassword(bCryptPasswordEncoder.encode("admin"));
        User userFomDB = userRepository.findByLogin(user.getLogin());
        if (userFomDB != null) {
            return;
        }
        user.setRoles(Collections.singleton(new Role(1L, Roles.ROLE_ADMIN.name())));
        userRepository.save(user);
    }
}
