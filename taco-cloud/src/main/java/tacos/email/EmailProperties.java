package tacos.email;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "tacocloud.email")
@Component
public class EmailProperties {
    private String username;
    private String password;
    private String host;
    private String mailbox;
    private long poolRate = 3000;

    public String getImageUrl() {
        return String.format("imaps://%s:%s@%s/%s", username, password, host, mailbox);
    }
}
