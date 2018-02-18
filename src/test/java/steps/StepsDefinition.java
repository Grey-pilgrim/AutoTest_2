package steps;

import cucumber.api.DataTable;
import cucumber.api.java.ru.*;
import ru.task.pages.DesignPage;
import ru.task.pages.MainPage;

public class StepsDefinition {
    private final MainPage mainPage = new MainPage();
    private final DesignPage designPage = new DesignPage();

    @Дано("^открыта страница \"([^\"]*)\"$")
    public void openPage(String url) {
        mainPage.goToPage(url);
    }

    @Тогда("^нажать на \"([^\"]*)\"$")
    public void clickOn(String name) {
        mainPage.clickOn(name);
    }

    @И("^выбрать \"([^\"]*)\"$")
    public void choose(String name) {
        mainPage.choose(name);
    }

    @Если("^заголовок страницы \"([^\"]*)\"$")
    public void checkTitleOnPage(String title) {
        mainPage.checkTitleOnPage(title);
    }

    @И("^нажать на баннер \"([^\"]*)\"$")
    public void clickOnBanner(String banner) {
        mainPage.clickOnBanner(banner);
        designPage.switchToTab();
    }

    @Если("^заполнить в секции \"([^\"]*)\" поля:$")
    public void inSectionFillFields(String section, DataTable data) {
        designPage.inSectionFillFields(section, data.asMap(String.class, String.class));
    }

    @И("^в секции \"([^\"]*)\" в полях содержатся значения:$")
    public void checkFillingOfFieldsInSection(String section, DataTable data) {
        designPage.checkFillingOfFieldsInSection(section, data.asMap(String.class, String.class));
    }

    @Тогда("^проверить сообщение \"([^\"]*)\"$")
    public void checkErrorMessage(String message) {
        designPage.checkErrorMessage(message);
        designPage.closeTab();
    }
}
