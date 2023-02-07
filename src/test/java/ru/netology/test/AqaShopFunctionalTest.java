package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopFunctionalTest {
    @BeforeEach
    void setupTest() {open("http://localhost:8080");}

    @DisplayName("Should APPROVE Valid Card when Paying By Card")
    @Test
    void shouldAcceptValidCardPayByCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldAccept();
    }

    @DisplayName("Should DECLINE Valid Card when Paying By Card")
    @Test
    void shouldDeclineValidCardPayByCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getDeclinedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }

    @DisplayName("Should APPROVE Valid Card when Paying By Credit")
    @Test
    void shouldAcceptValidCardPayByCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldAccept();
    }

    @DisplayName("Should DECLINE Valid Card when Paying By Credit")
    @Test
    void shouldDeclineValidCardPayByCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getDeclinedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }
}
