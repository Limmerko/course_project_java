package vlsu.pri117.mep.startup;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import vlsu.pri117.mep.model.Role;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.RoleRepository;

import java.util.List;

@Component
public class KonohaApp {

    private final RoleRepository roleRepository;

    public KonohaApp(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @EventListener
    public void onApplicationEvent(ApplicationReadyEvent applicationReadyEvent) {
        Roles[] roles = Roles.values();
        List<Role> rolesBD = (List<Role>) roleRepository.findAll();
        if (roles.length != rolesBD.size()) {
            for (int i = 0; i < roles.length; i++) {
                roleRepository.save(new Role((long) i+1, roles[i].name()));
            }
        }
    }
}
