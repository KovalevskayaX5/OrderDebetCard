package ru.netology;

import com.codeborne.selenide.impl.WebElementSelector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class OrderDebetCardTest {
    @BeforeEach
    void setUpTests() {
        open("http://localhost:7777/");
    }

    @Test
    void testPositive() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testEnglishNameNegative() {
        $("[data-test-id=name] input").setValue("Kovalevskaya Yana");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));


    }

    @Test
    void testShortPhoneNegative() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
       // $(withText("Телефон указан неверно.")).shouldBe(visible);
    }

    @Test
    void testNotAgreementNegative() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]");
        $("button").click();
        $("[data-test-id=order-success]").shouldNotBe(visible);

        //$(withText("Ваша заявка успешно отправлена!")).shouldNotBe(visible);

    }

    @Test
    void testLongPhoneNegative() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+798963400854585");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

        //$(withText("Телефон указан неверно.")).shouldBe(visible);

    }

    @Test
    void testPhone_8Negative() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("89896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
        //$(withText("Телефон указан неверно.")).shouldBe(visible);

    }
    @Test
    void testEmptyName() {
        $("[data-test-id=name] input").setValue(" ");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=name].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }
    @Test
    void testEmptyPhone() {
        $("[data-test-id=name] input").setValue("Ковалевская Яна ");
        $("[data-test-id=phone] input").setValue(" ");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=phone].input_invalid .input__sub").shouldHave(exactText("Поле обязательно для заполнения"));


    }



}
