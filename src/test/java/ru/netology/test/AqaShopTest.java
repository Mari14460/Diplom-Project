package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import static com.codeborne.selenide.Selenide.open;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class AqaShopTest {
    @BeforeEach
    void setupTest() {open("http://localhost:8080");}

    @Order(10)
    @DisplayName("Should Show Mandatory field message for CARD")
    @Test
    void shouldShowMandatoryFieldCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMandatoryField(0);
    }

    @Order(20)
    @DisplayName("Should Show Mandatory field message for MONTH")
    @Test
    void shouldShowMandatoryFieldMonth() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMandatoryField(1);
    }

    @Order(30)
    @DisplayName("Should Show Mandatory field message for YEAR")
    @Test
    void shouldShowMandatoryFieldYear() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMandatoryField(2);
    }

    @Order(40)
    @DisplayName("Should Show Mandatory field message for OWNER")
    @Test
    void shouldShowMandatoryFieldOwner() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMandatoryField(3);
    }

    @Order(50)
    @DisplayName("Should Show Mandatory field message for CVC")
    @Test
    void shouldShowMandatoryFieldCVC() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMandatoryField(4);
    }

    @Order(60)
    @DisplayName("Should APPROVE Valid Card when Paying By Card")
    @Test
    void shouldAcceptValidCardPayByCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fill(0, DataHelper.getAcceptedCard());
        paymentPage.fill(1, DataHelper.getValidMonth());
        paymentPage.fill(2, DataHelper.getValidYear());
        paymentPage.fill(3, DataHelper.getValidOwner());
        paymentPage.fill(4, DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldAccept();
    }

    @Order(70)
    @DisplayName("Should DECLINE Valid Card when Paying By Card")
    @Test
    void shouldDeclineValidCardPayByCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fill(0, DataHelper.getDeclinedCard());
        paymentPage.fill(1, DataHelper.getValidMonth());
        paymentPage.fill(2, DataHelper.getValidYear());
        paymentPage.fill(3, DataHelper.getValidOwner());
        paymentPage.fill(4, DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }

    @Order(80)
    @DisplayName("Should APPROVE Valid Card when Paying By Credit")
    @Test
    void shouldAcceptValidCardPayByCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fill(0, DataHelper.getAcceptedCard());
        paymentPage.fill(1, DataHelper.getValidMonth());
        paymentPage.fill(2, DataHelper.getValidYear());
        paymentPage.fill(3, DataHelper.getValidOwner());
        paymentPage.fill(4, DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldAccept();
    }

    @Order(90)
    @DisplayName("Should DECLINE Valid Card when Paying By Credit")
    @Test
    void shouldDeclineValidCardPayByCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fill(0, DataHelper.getDeclinedCard());
        paymentPage.fill(1, DataHelper.getValidMonth());
        paymentPage.fill(2, DataHelper.getValidYear());
        paymentPage.fill(3, DataHelper.getValidOwner());
        paymentPage.fill(4, DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.shouldDecline();
    }
}
