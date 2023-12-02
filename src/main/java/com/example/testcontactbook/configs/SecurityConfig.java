package com.example.testcontactbook.configs;

import com.example.testcontactbook.models.entities.User;
import com.example.testcontactbook.models.enums.AthorityEnum;
import com.example.testcontactbook.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final UserRepository userRepository;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((a) -> {
                            a.requestMatchers("/auth/contacts/get-all").hasAuthority(AthorityEnum.ADMIN.toString());
                            a.requestMatchers("/auth/**").hasAnyAuthority(AthorityEnum.ADMIN.toString(), AthorityEnum.USER.toString());
                            a.anyRequest().permitAll();
                        }
                )
                .httpBasic()
                .and()
                .logout(
                        (logout) -> {
                            logout.deleteCookies("JSESSIONID");
                            logout.deleteCookies();
                            logout.logoutUrl("/logout");
                            logout.invalidateHttpSession(true);
                            logout.clearAuthentication(true);
                            logout.logoutSuccessUrl("http://localhost:8080/api/swagger-ui/index.html");
                        }
                )


                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            User user = userRepository.findByUsername(username).orElseThrow(
                    () -> new UsernameNotFoundException("security: user with username %s not found".formatted(username))
            );
            return org.springframework.security.core.userdetails.User.builder()
                    .username(username)
                    .password(user.getPassword())
                    .authorities(user.getAuthorities())
                    .build();
        };
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}
