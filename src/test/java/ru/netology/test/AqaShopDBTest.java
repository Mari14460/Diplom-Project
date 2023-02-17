package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopDBTest {
    @BeforeEach
    void setupTest() throws SQLException {
        DBHelper.cleanDB();
        open("http://localhost:8080");
    }

    //region CARD
    @DisplayName("PAY BY CARD Should verify AMOUNT Paying By Card")
    @Test
    void verifyAmountPaidByCard() {
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
        int price = DashboardPage.getTravelPrice();
        int paid = DBHelper.getPaidAmountCard();
        Assertions.assertEquals(price, paid);
    }

    @DisplayName("PAY BY CARD Should APPROVE APPROVED Card when Paying By Card")
    @Test
    void shouldApproveApprovedCardByCard() {
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
        String statusCard = DBHelper.getStatusCard();
        Assertions.assertEquals("APPROVED", statusCard);
    }

    @DisplayName("PAY BY CARD Should DECLINE DECLINED Card when Paying By Card")
    @Test
    void shouldDeclineDeclinedCardByCard() {
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
        String statusCard = DBHelper.getStatusCard();
        Assertions.assertEquals("DECLINED", statusCard);
    }

    @DisplayName("PAY BY CARD Should MATCH TRANSACTION IDs when Paying By Card")
    @Test
    void shouldMatchTransactionByCard() {
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
        String transactionOrder = DBHelper.getTransactionIDOrderCard();
        String transactionCard = DBHelper.getTransactionIDCard();
        Assertions.assertEquals(transactionOrder, transactionCard);
    }
    //endregion

    //region CREDIT

    @DisplayName("PAY WITH CREDIT Should APPROVE APPROVED Card when Paying By Credit")
    @Test
    void shouldApproveApprovedWithCredit() {
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
        String statusCard = DBHelper.getStatusCredit();
        Assertions.assertEquals("APPROVED", statusCard);
    }

    @DisplayName("PAY WITH CREDIT Should DECLINE DECLINED Card when Paying By Credit")
    @Test
    void shouldDeclineDeclinedWithCredit() {
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
        String statusCard = DBHelper.getStatusCredit();
        Assertions.assertEquals("DECLINED", statusCard);
    }

    @DisplayName("PAY WITH CREDIT Should MATCH TRANSACTION IDs when Paying By Credit")
    @Test
    void shouldMatchTransactionWithCredit() {
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
        String transactionOrder = DBHelper.getTransactionIDOrderCredit();
        String transactionIDCredit = DBHelper.getTransactionIDCredit();
        Assertions.assertEquals(transactionOrder, transactionIDCredit);
    }
    //endregion
}
