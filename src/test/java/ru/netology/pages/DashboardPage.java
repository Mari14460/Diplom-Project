package ru.netology.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DashboardPage {
    private static final SelenideElement heading = $("[id=root]");
    private static final SelenideElement payWithCardButton = $$("button").find(exactText("Купить"));
    private static final SelenideElement payWithCreditButton = $$("button").find(exactText("Купить в кредит"));
    private static final SelenideElement formOfPayment = $("#root > div > h3");
    private static final SelenideElement travelPrice = $$(".list__item").get(3);

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void shouldPayWithCard() {
        payWithCardButton.click();
        formOfPayment.shouldHave(text("Оплата по карте"));
    }

    public void shouldPayWithCredit() {
        payWithCreditButton.click();
        formOfPayment.shouldHave(text("Кредит по данным карты"));
    }

    public static int getTravelPrice() { //Всего 45 000 руб.!
        String priceLine = travelPrice.getText();
        String price = "";
        for (int i = 0; i < priceLine.length(); i++) {
            if (Character.isDigit(priceLine.charAt(i))) {
                price += priceLine.charAt(i);
            }
        }
        return Integer.parseInt(price) * 100; //convert to cents
    }
}
