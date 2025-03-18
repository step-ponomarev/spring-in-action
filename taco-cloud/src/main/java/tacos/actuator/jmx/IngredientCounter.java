package tacos.actuator.jmx;

import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.jmx.export.annotation.ManagedAttribute;
import org.springframework.jmx.export.annotation.ManagedOperation;
import org.springframework.jmx.export.annotation.ManagedResource;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;
import tacos.service.TacoService;

@Service
@ManagedResource
@DependsOn("dataLoader")
public class IngredientCounter {
    private final TacoService tacoService;
    private final AtomicLong counter = new AtomicLong();

    @PostConstruct
    public void init() {
        counter.set(tacoService.getIngredients().stream().count());
    }

    @Autowired
    public IngredientCounter(TacoService tacoService) {
        this.tacoService = tacoService;
    }

    @ManagedAttribute
    public long getIngredientsCount() {
        return counter.get();
    }

    @ManagedOperation
    public long incrementCounter(long amount) {
        return counter.addAndGet(amount);
    }
}
