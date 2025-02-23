package tacos.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "taco.order")
@Getter
@Setter
@Validated
public class OrderProps {
    @Min(1)
    @Max(20)
    int pageSize = 20;
}
