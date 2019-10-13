package TelegramEmulator.TelegramWebEmulator;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;


/*
*
* Atomic operations for TelegramWeb
*
**/
public class TelegramWebAccount extends TelegramWebEmulator {

    protected final long ONESECOND=1000;

    protected long delay;

    protected String currentChat;

    public TelegramWebAccount(WebDriver webDriver, WebDriverWait wait){
        super(webDriver, wait);         //calling the constructor of parent class
        this.delay=4000;                //TODO to think about time of delay
        this.currentChat="";
    }

    public String getCurrentChat() {
        return currentChat;
    }

    public void setDelay(long delay) {
        this.delay = delay;     //setter
    }

    public void printTheStateOfIncapsulatedVariables(){
        System.out.println("delay: "+delay);
        System.out.println("current chat: "+currentChat);
    }

    public boolean isLoginWindow(){
        try {
            Thread.sleep(delay);
        }catch (Exception e){}
        return webDriver.getCurrentUrl().matches(".*login");
    }

    public void enteringThePhoneNumber(String phoneNumber) {
        try {
            wait.until(visibilityOfElementLocated(By.xpath("//input[@name='phone_country']"))).clear();             //clearing the country code input
            WebElement phoneNumber_input = wait.until(visibilityOfElementLocated(By.xpath("//input[@name='phone_number']")));
            phoneNumber_input.clear();                                                                               //clearing the phone number input
            phoneNumber_input.sendKeys(phoneNumber);                                                                 //filling the phone number input
            wait.until(visibilityOfElementLocated(By.xpath("//a[@class='login_head_submit_btn']"))).click();        //clicking on 'next' button
            wait.until(visibilityOfElementLocated(By.xpath("//button[2]"))).click();                                //clicking 'ok' on pop-up window
        }catch (Exception e){}
    }

    public void enteringThePhoneCode(String phoneCode){
        try{
            wait.until(visibilityOfElementLocated(By.xpath("//input[@name='phone_code']"))).sendKeys(phoneCode);
        }catch (Exception e){}
    }

    public void goToTelegramWeb(){
        webDriver.get("https://web.telegram.org");  //goes to url "https://web.telegram.org"
    }


    public void sendMessage(String message){
        WebElement textArea;
        try{
            textArea=wait.until(visibilityOfElementLocated(By.xpath("//div[@class='composer_rich_textarea']")));
            textArea.sendKeys(message);
            textArea.sendKeys(Keys.ENTER);
        }catch (Exception e){
            System.out.println("cant find elements in sendMessage(String message)");
        }
    }


    /*Some scenario of sending message
     *
     * @param whom       the username, group or phone number
     *
     * @param message    the message,that you want to send.
     *
     * */
    public void sendMessage(String whom,String message) {
        WebElement search;
        WebElement textArea;
        try {
            search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
            search.clear();
            search.sendKeys(whom);
            Thread.sleep(delay);
            wait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/ul/li[1]/a"))).click();
            this.currentChat=wait.until(visibilityOfElementLocated(By.xpath("//span[@class='tg_head_peer_title']"))).getText();
            textArea=wait.until(visibilityOfElementLocated(By.xpath("//div[@class='composer_rich_textarea']")));
            textArea.sendKeys(message);
            textArea.sendKeys(Keys.ENTER);
            Thread.sleep(ONESECOND);
        }catch (InterruptedException ie){
            System.out.println("Tread sleep got an error");
        }catch (TimeoutException te){
            System.out.println("Dont located element."+te.getMessage());
        }
    }


    /* Some scenario of sending image
     *
     * @param whom   the username, group or phone number
     *
     * @param path   the canonical path to the image
     *               on your harddrive
     *
     * */
    public void sendImage(String whom, String path){
        try {
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
            search.clear();
            search.sendKeys(whom);
            Thread.sleep(delay);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/ul/li[1]/a"))).click();
            this.currentChat=wait.until(visibilityOfElementLocated(By.xpath("//span[@class='tg_head_peer_title']"))).getText();
            wait.until(visibilityOfElementLocated(By.xpath("(//input[@type='file'])[2]"))).sendKeys(path);

        }catch (InterruptedException ie){
            System.out.println("Tread sleep got an error");
        }catch (TimeoutException te){
            System.out.println("Dont located element."+te.getMessage());
            webDriver.navigate().refresh();
        }
    }

    public void sendFile(String whom,String path){
        /*
         * Some scenario of sending file
         *
         * @param whom   the username,group or phone number
         *
         * @param path   the canonical path to the file
         *               on your harddrive
         * */
        try {
            WebElement search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));
            search.clear();
            search.sendKeys(whom);
            Thread.sleep(delay);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/ul/li[1]/a"))).click();
            this.currentChat=wait.until(visibilityOfElementLocated(By.xpath("//span[@class='tg_head_peer_title']"))).getText();
            wait.until(visibilityOfElementLocated(By.xpath("(//input[@type='file'])[1]"))).sendKeys(path);
        }catch (InterruptedException ie){
            System.out.println("Tread sleep got an error");
        }catch (TimeoutException te){
            System.out.println("Dont located element."+te.getMessage());
            webDriver.navigate().refresh();
        }
    }

    /*
     * Some scenario of selecting chat in the
     * list of users, publics or chats
     *
     * @param chatName   the username,group or phone number
     *
     * */
    public void selectChat(String chatName){
        WebElement search;
        try {
            search = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@type='search']")));//webDriver.findElement(By.xpath("//input[@placeholder='Search']"));
            search.clear();
            search.sendKeys(chatName);
            Thread.sleep(delay);
            wait.until(visibilityOfElementLocated(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div[2]/div/div[1]/ul/li[1]/a"))).click();
            Thread.sleep(ONESECOND);
            this.currentChat=wait.until(visibilityOfElementLocated(By.xpath("//span[@class='tg_head_peer_title']"))).getText();
            Thread.sleep(ONESECOND);
        }catch (InterruptedException ie){
            System.out.println("Tread sleep got an error");
        }catch (TimeoutException te) {
            System.out.println("Dont located element." + te.getMessage());
        }catch (NoSuchSessionException se){
            System.out.println("No such exception: "+se.getMessage());
        }
    }

    public void close(){
        for (String s:webDriver.getWindowHandles()) {
            webDriver.switchTo().window(s);
            webDriver.close();
        }
    }

}
