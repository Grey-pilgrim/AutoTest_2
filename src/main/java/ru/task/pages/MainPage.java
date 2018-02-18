package ru.task.pages;

import static ru.task.infrastructure.Core.DRIVER;

public class MainPage extends Page {

    /**
     * Перейти на страницу.
     * @param url URL-адрес.
     */
    public void goToPage(String url) {
        DRIVER.get(url);
    }

    /**
     * Выбрать.
     * @param name Название.
     */
    public void choose(String name) {
        clickOn(name);
    }

    /**
     * Нажать на баннер.
     * @param banner Баннер.
     */
    public void clickOnBanner(String banner) {
        textOrImage.apply(".//img[contains(@src, '" + banner + "')]").click();
    }
}
