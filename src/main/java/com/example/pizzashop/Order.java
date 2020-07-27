package com.example.pizzashop;

import lombok.Data;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class Order {

    @NotBlank(message="Имя обязательно")
    private String name;

    @NotBlank(message="Улица обязательно")
    private String street;

    @NotBlank(message="Город обязательно")
    private String city;

    @NotBlank(message="Штат обязательно")
    private String state;

    @NotBlank(message="Почтовый индекс обязательно")
    private String zip;

    @CreditCardNumber(message="Недействительный номер кредитной карты")
    private String ccNumber;

    @Pattern(regexp="^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Должен быть MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Неверный CVV")
    private String ccCVV;

}
