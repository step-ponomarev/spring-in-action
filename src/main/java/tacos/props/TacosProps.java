package tacos.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "taco.props")
@Getter
@Setter
@Validated
public class TacosProps {
    @Min(1)
    @Max(20)
    int orderPageSize = 20;

    @Min(1)
    @Max(20)
    int tacoPageSize = 20;
}
