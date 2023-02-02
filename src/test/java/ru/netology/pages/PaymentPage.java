package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    public static final SelenideElement wrongCardExpiration = $(byText("Неверно указан срок действия карты"));
    public static final SelenideElement expiredCard =  $(byText("Истёк срок действия карты"));
    private static final ElementsCollection inputs = $$(".input__control");
    private static final ElementsCollection inputsMessages = $$(".input__sub");
    private static final SelenideElement continueButton = $(byXpath("//span[text()='Продолжить']"));
    public static final SelenideElement transactionApproved =  $(byText("Операция одобрена Банком."));
    public static final SelenideElement transactionDeclined =  $(byText("Ошибка! Банк отказал в проведении операции."));

    public void fill(int index, String text) {
        inputs.get(index).setValue(text);
    }
    public void checkMandatoryField(int index) {
        //ElementsCollection inputsMessages = $$(".input__sub");
        inputsMessages.get(index).shouldHave(Condition.exactText("Поле обязательно для заполнения"));
    }

    public void checkMessageWrongFormat(int index) {
        //ElementsCollection inputsMessages = $$(".input__sub");
        inputsMessages.get(index).shouldHave(Condition.exactText("Неверный формат"));
    }
    public void clickContinue(){
        continueButton.click();
    }
    public void shouldAccept() {
        transactionApproved.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
    public void shouldDecline() {
        transactionDeclined.shouldBe(Condition.visible, Duration.ofSeconds(15));
    }
}
