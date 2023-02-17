package ru.netology.test;

import org.junit.jupiter.api.*;
import ru.netology.data.DBHelper;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;

public class AqaShopFieldsTest {
    @BeforeEach
    void setupTest() throws SQLException {
        DBHelper.cleanDB();
        open("http://localhost:8080");
    }

    //region CARD
    //region All Fields Test
    @DisplayName("PAY BY CARD Should Show Mandatory Field messages for All Fields when empty")
    @Test
    void shouldShowMandatoryFieldAllFieldsPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMessageMandatoryCard();
        paymentPage.checkMessageMandatoryMonth();
        paymentPage.checkMessageMandatoryYear();
        paymentPage.checkMessageMandatoryOwner();
        paymentPage.checkMessageMandatoryCvc();
    }
    //endregion

    //region Card Field Test
    @DisplayName("PAY BY CARD Should Show Wrong Format message for Card")
    @Test
    void shouldShowWrongFormatCardPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getIncompleteCard());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCard();
    }
    //endregion

    //region Month Field Test
    @DisplayName("PAY BY CARD Should Show Wrong Format message for MONTH = 00")
    @Test
    void shouldWrongMonth00PayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getInvalidMonth00());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateMonth();
    }
    @DisplayName("PAY BY CARD Should Show Wrong Format  message for MONTH = 13")
    @Test
    void shouldWrongMonth13PayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getInvalidMonth13());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateMonth();
    }
    //endregion

    //region Year Field Test
    @DisplayName("PAY BY CARD Should Show Card Expired message for YEAR < current")
    @Test
    void shouldShowExpiredPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getInvalidPreviousYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageExpiredYear();
    }

    @DisplayName("PAY BY CARD Should Show Card Wrong Year message for YEAR > current + 6")
    @Test
    void shouldShowWrongYearPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getInvalidFutureYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateYear();
    }
    //endregion

    //region Owner Field Test
    @DisplayName("PAY BY CARD Should Show Wrong Format message for OWNER in English")
    @Test
    void shouldShowWrongFormatOwnerEngPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }

    @DisplayName("PAY BY CARD Should Show Wrong Format message for OWNER with Symbols")
    @Test
    void shouldShowWrongFormatOwnerSymbolsPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidSymbolsOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }
    @DisplayName("PAY BY CARD Should Show Wrong Format message for OWNER with Numbers")
    @Test
    void shouldShowWrongFormatOwnerNumbersPayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidNumberOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }
    //endregion

    //region CVC Field Test
    @DisplayName("PAY BY CARD Should Show Wrong Format message for CVC 1 digit")
    @Test
    void shouldShowWrongFormatCvc1PayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getIncompleteCVC1());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCvc();
    }

    @DisplayName("PAY BY CARD Should Show Wrong Format message for CVC 2 digits")
    @Test
    void shouldShowWrongFormatCvc2PayCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCard();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getIncompleteCVC2());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCvc();
    }
    //endregion
    //endregion

    //region CREDIT
    //region All Fields Test
    @DisplayName("PAY WITH CREDIT Should Show Mandatory Field messages for All Fields when empty")
    @Test
    void shouldShowMandatoryFieldAllFieldsCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.clickContinue();
        paymentPage.checkMessageMandatoryCard();
        paymentPage.checkMessageMandatoryMonth();
        paymentPage.checkMessageMandatoryYear();
        paymentPage.checkMessageMandatoryOwner();
        paymentPage.checkMessageMandatoryCvc();
    }
    //endregion

    //region Card Field Test
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for Card")
    @Test
    void shouldShowWrongFormatCardCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getIncompleteCard());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCard();
    }
    //endregion

    //region Month Field Test
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for MONTH = 00")
    @Test
    void shouldWrongMonth00Credit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getInvalidMonth00());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateMonth();
    }
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format  message for MONTH = 13")
    @Test
    void shouldWrongMonth13Credit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getInvalidMonth13());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateMonth();
    }
    //endregion

    //region Year Field Test
    @DisplayName("PAY WITH CREDIT Should Show Card Expired message for YEAR < current")
    @Test
    void shouldShowExpiredCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getInvalidPreviousYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageExpiredYear();
    }

    @DisplayName("PAY WITH CREDIT Should Show Card Wrong Year message for YEAR > current + 6")
    @Test
    void shouldShowWrongYearCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getInvalidFutureYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongDateYear();
    }
    //endregion

    //region Owner Field Test
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for OWNER in English")
    @Test
    void shouldShowWrongFormatOwnerEngCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }

    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for OWNER with Symbols")
    @Test
    void shouldShowWrongFormatOwnerSymbolsCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidSymbolsOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for OWNER with Numbers")
    @Test
    void shouldShowWrongFormatOwnerNumbersCredit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getInvalidNumberOwner());
        paymentPage.fillCvc(DataHelper.getValidCVC());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatOwner();
    }
    //endregion

    //region CVC Field Test
    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for CVC 1 digit")
    @Test
    void shouldShowWrongFormatCvc1Credit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getIncompleteCVC1());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCvc();
    }

    @DisplayName("PAY WITH CREDIT Should Show Wrong Format message for CVC 2 digits")
    @Test
    void shouldShowWrongFormatCvc2Credit() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getAcceptedCard());
        paymentPage.fillMonth(DataHelper.getValidMonth());
        paymentPage.fillYear(DataHelper.getValidYear());
        paymentPage.fillOwner(DataHelper.getValidOwner());
        paymentPage.fillCvc(DataHelper.getIncompleteCVC2());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCvc();
    }
    //endregion
    //endregion
}
