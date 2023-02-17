package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopFunctionalTest {
    @BeforeEach
    void setupTest() throws SQLException {
        DBHelper.cleanDB();
        open("http://localhost:8080");
    }

    @DisplayName("PAY BY CARD Should APPROVE APPROVED Card when Paying By Card")
    @Test
    void shouldAcceptValidCardByCard() {
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

    @DisplayName("PAY BY CARD Should DECLINE DECLINED Card when Paying By Card")
    @Test
    void shouldDeclineValidCardByCard() {
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

    @DisplayName("PAY BY CARD Should DECLINE INVALID Card when Paying By Card")
    @Test
    void shouldDeclineInvalidCardByCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getInvalidCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }

    @DisplayName("PAY WITH CREDIT Should APPROVE APPROVED Card when Paying By Credit")
    @Test
    void shouldAcceptValidCardWithCredit() {
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

    @DisplayName("PAY WITH CREDIT Should DECLINE DECLINED Card when Paying By Credit")
    @Test
    void shouldDeclineValidCardWithCredit() {
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

    @DisplayName("PAY WITH CREDIT Should DECLINE INVALID Card when Paying By Credit")
    @Test
    void shouldDeclineInvalidCardWithCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getInvalidCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }
}
