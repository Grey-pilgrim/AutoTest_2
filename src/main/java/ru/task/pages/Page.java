package ru.task.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.task.infrastructure.Core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class Page {

    Map<String, String> locators = new HashMap<String, String>() {{
        put("Застрахованные", ".//h4[contains(@class, 'ng-hide') and text()='");
        put("Страхователь", ".//h4[text()='");
        put("Данные паспорта РФ", ".//h4[text()='");
    }};

    /**
     * Нажать на.
     * @param name Название.
     */
    public void clickOn(String name) {
        buttonOrLink.apply(".//*[contains(@aria-label, '" + name + "') or contains(text(), '" + name + "')]").click();
    }

    /**
     * Проверить заголовок на странице.
     * @param title Заголовок.
     */
    public void checkTitleOnPage(String title) {
        textOrImage.apply("//h1[contains(text(), '" + title + "')]");
    }

    /**
     * Переключиться на вкладку.
     */
    public void switchToTab() {
        List<String> tabs = new ArrayList<>(Core.DRIVER.getWindowHandles());
        tabs.remove(Core.DRIVER.getWindowHandle());
        Core.DRIVER.switchTo().window(tabs.get(0));
    }

    /**
     * Кнопка / ссылка
     */
    private Function<String, WebElement> buttonOrLink =
            locator -> Core.WAIT.until(ExpectedConditions.elementToBeClickable(By.xpath(locator)));

    /**
     * Текст / картинка
     */
    Function<String, WebElement> textOrImage =
            locator -> Core.WAIT.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locator)));
}
