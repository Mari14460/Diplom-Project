package ru.netology.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.pages.DashboardPage;
import ru.netology.pages.PaymentPage;

import com.codeborne.selenide.Selenide;
import static com.codeborne.selenide.Selenide.open;

public class AqaShopFieldsTest {
    @BeforeEach
    void setupTest() {open("http://localhost:8080");}

    //region All Field Test
    @DisplayName("Should Show Mandatory Field messages for All Fields when empty")
    @Test
    void shouldShowMandatoryFieldAllFields() {
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
    @DisplayName("Should Show Wrong Format message for Card")
    @Test
    void shouldShowWrongFormatCard() {
        var dashboardPage = new DashboardPage();
        dashboardPage.shouldPayWithCredit();
        var paymentPage = new PaymentPage();
        paymentPage.fillCardNumber(DataHelper.getIncompleteCard());
        paymentPage.clickContinue();
        paymentPage.checkMessageWrongFormatCard();
    }
    //endregion

    //region Month Field Test
    @DisplayName("Should Show Wrong Format message for MONTH = 00")
    @Test
    void shouldWrongMonth00() {
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
    @DisplayName("Should Show Wrong Format  message for MONTH = 13")
    @Test
    void shouldWrongMonth13() {
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
    @DisplayName("Should Show Card Expired message for YEAR < current")
    @Test
    void shouldShowExpired() {
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

    @DisplayName("Should Show Card Expired message for YEAR > current + 6")
    @Test
    void shouldShowWrongYear() {
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
    @DisplayName("Should Show Wrong Format message for OWNER in English")
    @Test
    void shouldShowWrongFormatOwnerEng() {
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

    @DisplayName("Should Show Wrong Format message for OWNER with Symbols")
    @Test
    void shouldShowWrongFormatOwnerSymbols() {
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
    @DisplayName("Should Show Wrong Format message for OWNER with Numbers")
    @Test
    void shouldShowWrongFormatOwnerNumbers() {
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
    @DisplayName("Should Show Wrong Format message for CVC 1 digit")
    @Test
    void shouldShowWrongFormatCvc1() {
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

    @DisplayName("Should Show Wrong Format message for CVC 2 digits")
    @Test
    void shouldShowWrongFormatCvc2() {
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
}
