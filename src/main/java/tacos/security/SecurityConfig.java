package tacos.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@Configuration
@EnableMethodSecurity
public class SecurityConfig {
    @Autowired
    private SecurityProps props;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(i ->
                        i.requestMatchers("/orders/**", "/design").hasAnyRole("USER")
                                .requestMatchers(HttpMethod.POST, "/api/ingredient")
                                .hasAnyAuthority("SCOPE_writeIngredients")
                                .requestMatchers(HttpMethod.DELETE, "/api/ingredient")
                                .hasAnyAuthority("SCOPE_deleteIngredients")
                                .requestMatchers(HttpMethod.GET, "/api/orders/**")
                                .hasAnyAuthority("SCOPE_readOrder")
                                .requestMatchers(HttpMethod.POST, "/api/orders/**")
                                .hasAnyAuthority("SCOPE_writeOrder")
                                .requestMatchers("/", "/**").permitAll()
                )
                .formLogin(c -> {
                    c.loginPage("/login")
                            .defaultSuccessUrl("/design");
                }).logout(c -> {
                    c.logoutSuccessUrl("/");
                })
                .csrf(c -> {
                    c.ignoringRequestMatchers(props.getIgnoreCsrfUrlPatterns());
                })
                .headers(headers -> headers
                        // Отключаем защиту от кликов через фреймы, чтобы H2-консоль могла отображаться
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                ).oauth2ResourceServer(c -> c.jwt(Customizer.withDefaults()))
//                .oauth2Login(c -> {})
//                .oauth2Client( c -> {})
                .build();
    }
}
