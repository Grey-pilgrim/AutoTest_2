package ru.task.infrastructure;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class Core {
    public final static WebDriver DRIVER;
    public final static WebDriverWait WAIT;

    /*
     * Инициализация драйвера
     */
    static {
        System.setProperty("webdriver.chrome.driver", "drv/chromedriver.exe");
        DRIVER = new ChromeDriver();
        WAIT = new WebDriverWait(DRIVER, 30);
    }
}
