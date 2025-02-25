package tacos.web;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import tacos.order.data.Ingredient;
import tacos.order.data.Taco;
import tacos.order.repository.IngredientRepository;
import tacos.order.repository.TacoRepository;
import tacos.security.User;
import tacos.security.UserService;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }

    @Bean
    // @Profile({"!prod"}) все, кроме прод профиля
    @Profile({"dev"}) // Будет выполняться только в дев профиле
    public CommandLineRunner commandLineRunner(IngredientRepository repository,
                                               UserService userService,
                                               TacoRepository tacoRepository,
                                               PasswordEncoder passwordEncoder) {
        return args -> {
            final Ingredient flto = new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP);
            final Ingredient coto = new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP);
            final Ingredient grbf = new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN);
            final Ingredient carn = new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN);
            final Ingredient tmto = new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES);
            final Ingredient lett = new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES);
            final Ingredient chd = new Ingredient("CHD", "Cheddar", Ingredient.Type.CHEESE);
            final Ingredient jack = new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE);
            final Ingredient slsa = new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE);
            final Ingredient srcr = new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE);

            repository.save(flto);
            repository.save(coto);
            repository.save(grbf);
            repository.save(carn);
            repository.save(tmto);
            repository.save(lett);
            repository.save(chd);
            repository.save(jack);
            repository.save(slsa);
            repository.save(srcr);

            userService.save(new User(
                    "step",
                    passwordEncoder.encode("1234"),
                    "stepan",
                    "stepan",
                    "stepan",
                    "stepan",
                    "stepan",
                    "stepan"
            ));

            final Taco taco1 = new Taco();
            taco1.setName("Carnivore");
            taco1.setIngredients(
                    List.of(
                            flto,
                            grbf,
                            carn,
                            jack,
                            srcr
                    )
            );
            tacoRepository.save(taco1);

            final Taco taco2 = new Taco();
            taco2.setName("Bovine Bounty");
            taco2.setIngredients(
                    List.of(
                            flto,
                            coto,
                            tmto,
                            lett,
                            slsa
                    )
            );
            tacoRepository.save(taco2);
        };
    }
}
