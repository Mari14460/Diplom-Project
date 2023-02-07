package ru.netology.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import java.time.Duration;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class PaymentPage {

    private static final SelenideElement cardContainer = $$(byXpath("//span[@class='input__inner']")).get(0);
    private static final SelenideElement monthContainer = $$(byXpath("//span[@class='input__inner']")).get(1);
    private static final SelenideElement yearContainer = $$(byXpath("//span[@class='input__inner']")).get(2);
    private static final SelenideElement ownerContainer = $$(byXpath("//span[@class='input__inner']")).get(3);
    private static final SelenideElement cvcContainer = $$(byXpath("//span[@class='input__inner']")).get(4);

    private static final SelenideElement cardField = $$(byXpath("//input[@class='input__control']")).get(0);
    private static final SelenideElement monthField = $$(byXpath("//input[@class='input__control']")).get(1);
    private static final SelenideElement yearField = $$(byXpath("//input[@class='input__control']")).get(2);
    private static final SelenideElement ownerField = $$(byXpath("//input[@class='input__control']")).get(3);
    private static final SelenideElement cvcField = $$(byXpath("//input[@class='input__control']")).get(4);

    private static final SelenideElement continueButton = $(byXpath("//span[text()='Продолжить']"));
    private static final SelenideElement transactionApproved =  $(byText("Операция одобрена Банком."));
    private static final SelenideElement transactionDeclined =  $(byText("Ошибка! Банк отказал в проведении операции."));

    public void fillCardNumber(String text) {
        cardField.setValue(text);
    }
    public void fillMonth(String text) {
        monthField.setValue(text);
    }
    public void fillYear(String text) {
        yearField.setValue(text);
    }
    public void fillOwner(String text) {
        ownerField.setValue(text);
    }
    public void fillCvc(String text) {
        cvcField.setValue(text);
    }
    public void checkMessageMandatoryCard() {
        cardContainer.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    public void checkMessageMandatoryMonth() {
        monthContainer.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    public void checkMessageMandatoryYear() {
        yearContainer.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    public void checkMessageMandatoryOwner() {
        ownerContainer.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    public void checkMessageMandatoryCvc() {
        cvcContainer.shouldHave(Condition.text("Поле обязательно для заполнения"));
    }
    public void checkMessageWrongFormatCard() {
        cardContainer.shouldHave(Condition.text("Неверный формат"));
    }
    public void checkMessageWrongFormatMonth() {
        monthContainer.shouldHave(Condition.text("Неверный формат"));
    }
    public void checkMessageWrongFormatYear() {
        yearContainer.shouldHave(Condition.text("Неверный формат"));
    }
    public void checkMessageWrongFormatOwner() {
        ownerContainer.shouldHave(Condition.text("Неверный формат"));
    }
    public void checkMessageWrongFormatCvc() {
        cvcContainer.shouldHave(Condition.text("Неверный формат"));
    }

    public void checkMessageWrongDateMonth() {
        monthContainer.shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void checkMessageWrongDateYear() {
        yearContainer.shouldHave(Condition.text("Неверно указан срок действия карты"));
    }

    public void checkMessageExpiredYear() {
        yearContainer.shouldHave(Condition.text("Истёк срок действия карты"));
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
