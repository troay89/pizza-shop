package com.example.pizzashop.web;

import com.example.pizzashop.Ingredient;
import com.example.pizzashop.Pizza;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.pizzashop.Ingredient.*;

@Slf4j
@Controller
@RequestMapping("/design")
public class DesignPizzaController {



    @ModelAttribute
    public void addIngredientsToModel(Model model) {
        List<Ingredient> ingredients = Arrays.asList(
                new Ingredient("FLTO", "Толстое тесто", Type.WRAP),
                new Ingredient("COTO", "Тонкое тесто", Type.WRAP),
                new Ingredient("GRBF", "Гавядина ", Type.PROTEIN),
                new Ingredient("CARN", "Курица", Type.PROTEIN),
                new Ingredient("TMTO", "Помидоры", Type.VEGGIES),
                new Ingredient("LETC", "Листья салата", Type.VEGGIES),
                new Ingredient("CHED", "Чеддер", Type.CHEESE),
                new Ingredient("JACK", "Монтерей джек", Type.CHEESE),
                new Ingredient("SLSA", "Соус", Type.SAUCE),
                new Ingredient("SRCR", "Кетчуп", Type.SAUCE)
        );

        Type[] types = Ingredient.Type.values();
        for (Type type : types) {
            model.addAttribute(type.toString().toLowerCase(),
                    filterByType(ingredients, type));
        }
    }

    @GetMapping
    public String showDesignForm(Model model) {
        model.addAttribute("design", new Pizza());
        return "design";
    }

    @PostMapping
    public String processDesign(@Valid @ModelAttribute("design") Pizza design, Errors errors, Model model) {
        if (errors.hasErrors()) {
            return "design";
        }
        log.info("Ваша пицца: " + design);
        return "redirect:/orders/current";
    }

    private List<Ingredient> filterByType(
            List<Ingredient> ingredients, Type type) {
        return ingredients
                .stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }
}

