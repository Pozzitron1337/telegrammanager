package TelegramEmulator.TelegramWebEmulator;

import TelegramEmulator.TelegramEmulator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class TelegramWebEmulator implements TelegramEmulator {
    protected WebDriver webDriver;
    protected WebDriverWait wait;
    public TelegramWebEmulator(WebDriver webDriver, WebDriverWait wait){
        this.webDriver=webDriver;
        this.wait=wait;
    }
}
