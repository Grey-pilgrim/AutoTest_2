package ru.task.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import ru.task.infrastructure.Core;

import java.util.*;
import static org.junit.Assert.assertTrue;
import static ru.task.pages.DesignPage.Action.*;

public class DesignPage extends Page {

    /**
    * Работать с полями.
    * @param section Секция.
    * @param fieldsAndValues Поля и Значения.
    * @param action Действие.
    */
    public void workingWithFields(String section, Map<String, String> fieldsAndValues, Action action) {
        WebElement root = textOrImage.apply("//h3[contains(text(), '" + section + "')]/parent::section[1]");

        for (Map.Entry<String, String> fieldAndValue : fieldsAndValues.entrySet()) {
            List<WebElement> inputs = root.findElements(
                    By.xpath(locators.get(section) +
                            fieldAndValue.getKey() +
                            "']/parent::fieldset//*[@type='text']"));
            List<String> values = Arrays.asList(fieldAndValue.getValue().split("#"));

            for (int i = 0; i < inputs.size(); i++) {
                action.toDo(inputs.get(i), values.get(i));
            }
        }
    }

    /**
     * Закрыть вкладку.
     */
    public void closeTab() {
        Core.DRIVER.close();
    }

    /**
     * Проверить сообщение об ошибке.
     * @param message Сообщение.
     */
    public void checkErrorMessage(String message) {
        textOrImage.apply("//div[contains(@class, 'error-message')]//div[contains(text(), '" + message + "')]");
    }

    /**
     * Проверить заполнение полей в секции.
     * @param section Название секции.
     * @param fieldsAndValues Поля и значения.
     */
    public void checkFillingOfFieldsInSection(String section, Map<String, String> fieldsAndValues) {
        workingWithFields(section, fieldsAndValues, CHECK_VALUE);
    }

    /**
     * В секции заполнить поля.
     * @param section Название секции.
     * @param fieldsAndValues Поля и значения, которыми мы их заполняем.
     */
    public void inSectionFillFields(String section, Map<String, String> fieldsAndValues) {
        workingWithFields(section, fieldsAndValues, INPUT_VALUE);
    }

    /**
     * Действие.
     */
    enum Action {
        /**
         * Проверка.
         */
        CHECK_VALUE {
            @Override
            void toDo(WebElement field, String value) {
                assertTrue("Ошибка заполнения поля",
                        field.getAttribute("value").equalsIgnoreCase(value));
            }},
        /**
         * Ввод.
         */
        INPUT_VALUE {
            @Override
            void toDo(WebElement field, String value) {
                new Actions(Core.DRIVER).click(field).sendKeys(value).build().perform();
            }};

        abstract void toDo(WebElement field, String value);
    }
}
