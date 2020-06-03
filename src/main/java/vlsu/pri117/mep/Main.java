package vlsu.pri117.mep;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.scheduling.annotation.EnableAsync;
import org.telegram.telegrambots.ApiContextInitializer;
import vlsu.pri117.mep.model.Role;
import vlsu.pri117.mep.model.enums.Roles;
import vlsu.pri117.mep.repository.RoleRepository;

import java.util.List;

@EnableAsync
@SpringBootApplication
public class Main {

    private final RoleRepository roleRepository;

    public Main(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public static void main(String[] args) {
        ApiContextInitializer.init();
        SpringApplication.run(Main.class, args);
    }

    public void onStart(ApplicationReadyEvent applicationReadyEvent) {
        Roles[] roles = Roles.values();
        List<Role> rolesBD = (List<Role>) roleRepository.findAll();
        if (roles.length != rolesBD.size()) {
            for (int i = 0; i < roles.length; i++) {
                roleRepository.save(new Role((long) i, roles[i].name()));
            }
        }
    }
}
