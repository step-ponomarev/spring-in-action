package tacos.security;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Component
@ConfigurationProperties(prefix = "security.props")
public class SecurityProps {
    private String[] ignoreCsrfUrlPatterns = {};
}
