package tacos.actuator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import io.micrometer.core.instrument.MeterRegistry;
import jakarta.annotation.PostConstruct;
import tacos.data.Ingredient;
import tacos.service.TacoService;

@Component
@DependsOn("dataLoader")
public class TacoCloudMetrics {
    private final TacoService tacoService;
    private final MeterRegistry meterRegistry;

    @Autowired
    public TacoCloudMetrics(TacoService tacoService, MeterRegistry meterRegistry) {
        this.tacoService = tacoService;
        this.meterRegistry = meterRegistry;
    }

    @PostConstruct
    public void init() {
        for (Ingredient ingredient : tacoService.getIngredients()) {
            meterRegistry.counter("tacocloud", "ingredient", ingredient.getId()).increment();
        }
    }
}
