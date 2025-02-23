package tacos.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.DefaultLoginPageConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.
                authorizeHttpRequests(i ->
                        i.requestMatchers("/orders/**", "/design").hasAnyRole("USER")
                        .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(c -> {
                    c.loginPage("/login")
                            .defaultSuccessUrl("/design");
                })
//                .oauth2Login(c -> {})
//                .oauth2Client( c -> {})
                .build();
    }
}
