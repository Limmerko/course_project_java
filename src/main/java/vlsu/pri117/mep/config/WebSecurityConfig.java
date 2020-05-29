package vlsu.pri117.mep.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import vlsu.pri117.mep.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    UserService userService;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable().authorizeRequests()
                // доступ только для не зарегистрированных пользователей
                .antMatchers("/registration").not().fullyAuthenticated()
                // доступ только для Администраторов
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/problems/edit/**").hasAnyRole("ADMIN", "ROLE_MODERATOR")
                .antMatchers("/news/edit/**").hasAnyRole("ADMIN", "ROLE_NEWS_MODERATOR", "ROLE_MODERATOR")
                //.antMatchers("/news").hasRole("USER")
                // доступ разрешен всем пользователям
                .antMatchers("/","/resources/**", "/news/**", "/problems/**").permitAll()
                // все остальные страницы требуют аутентификации
                .anyRequest().authenticated()
                .and()
                    // настройка для входа в систему
                    .formLogin()
                    .loginPage("/login")
                    // перенаправление на главную страницу после успешного входа
                    .defaultSuccessUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
                    .logoutSuccessUrl("/");
    }

    @Autowired
    protected void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder());
    }
}
