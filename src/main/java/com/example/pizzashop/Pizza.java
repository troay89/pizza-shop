package com.example.pizzashop;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class Pizza {

    @Size(min = 5, message = "Имя должно быть не менее 5 символов")
    @NotNull
    private String name;

    @Size(min = 1, message = "Вы должны выбрать как минимум 1 ингредиент")
    private List<String> ingredients;
}
