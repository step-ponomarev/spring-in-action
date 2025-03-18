package tacos.actuator;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

import tacos.service.TacoService;

@Component
public class TacoCountInfoContributor implements InfoContributor {
    @Autowired
    private TacoService tacoService;

    @Override
    public void contribute(Info.Builder builder) {
        final long ingredientCount = tacoService.getIngredients().stream().count();
        builder.withDetail("ingredient-stats", Map.of("ingredient-count", ingredientCount));
    }
}
