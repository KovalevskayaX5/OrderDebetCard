package ru.netology;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class OrderDebetCardTest {
    @BeforeEach
    void setUpTests() {
        open("http://localhost:7777/");
    }
    @Test
    void testPositive(){
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testEnglishNaneNegative(){
        $("[data-test-id=name] input").setValue("Kovalevskaya Yana");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    @Test
    void testShortPhoneNegative(){
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testNotAgreementNegative(){
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+79896340085");
        $("[data-test-id=agreement]");
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testLongPhoneNegative(){
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("+798963400854585");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void testPhone_8Negative(){
        $("[data-test-id=name] input").setValue("Ковалевская Яна");
        $("[data-test-id=phone] input").setValue("89896340085");
        $("[data-test-id=agreement]").click();
        $("button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }
    


}
