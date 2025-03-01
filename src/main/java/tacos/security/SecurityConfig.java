package tacos.security;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.web.context.annotation.RequestScope;

import tacos.web.rest.service.IngredientService;
import tacos.web.rest.service.RestIngredientService;


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
                        i.requestMatchers("/orders/**", "/design").authenticated()
                                .requestMatchers(HttpMethod.POST, "/api/ingredient")
                                .hasAnyAuthority("SCOPE_writeIngredients")
                                .requestMatchers(HttpMethod.DELETE, "/api/ingredient")
                                .hasAnyAuthority("SCOPE_deleteIngredients")
                                .requestMatchers(HttpMethod.GET, "/api/orders/**")
                                .hasAnyAuthority("SCOPE_readOrder")
                                .requestMatchers(HttpMethod.POST, "/api/orders/**")
                                .hasAnyAuthority("SCOPE_writeOrder")
                                .requestMatchers("/").permitAll()
                                .anyRequest().authenticated()
                ).logout(c -> c.logoutSuccessUrl("/"))
                .csrf(c -> c.ignoringRequestMatchers(props.getIgnoreCsrfUrlPatterns()))
                .headers(headers -> headers.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .oauth2ResourceServer(c -> c.jwt(Customizer.withDefaults()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED))
                .oauth2Login(c -> c.loginPage("/oauth2/authorization/taco-admin-client"))
                .build();
    }

    @Bean
    @RequestScope
    public IngredientService ingredientService(OAuth2AuthorizedClientService clientService) {
        return new RestIngredientService(
                getAccessToken(clientService)
        );
    }

    private String getAccessToken(OAuth2AuthorizedClientService clientService) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        String accessToken = null;
        if (authentication.getClass().isAssignableFrom(OAuth2AuthenticationToken.class)) {
            final OAuth2AuthenticationToken token = (OAuth2AuthenticationToken) authentication;
            String clientRegistrationId = token.getAuthorizedClientRegistrationId();
            if (Objects.equals(clientRegistrationId, "taco-admin-client")) {
                final OAuth2AuthorizedClient client = clientService.loadAuthorizedClient(clientRegistrationId, token.getName());
                accessToken = client.getAccessToken().getTokenValue();
            }
        }

        return accessToken;
    }

    private HttpSecurity applyCommonConfig(HttpSecurity http) throws Exception {
        return http
                .oauth2Login(c ->
                        c.loginPage("/oauth2/authorization/taco-admin-client")
                                .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
                )
                .logout(logout -> logout.logoutSuccessUrl("/"));
    }
}
