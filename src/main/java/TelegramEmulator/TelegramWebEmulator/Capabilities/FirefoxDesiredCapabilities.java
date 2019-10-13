package TelegramEmulator.TelegramWebEmulator.Capabilities;

import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

public class FirefoxDesiredCapabilities {

    public static DesiredCapabilities firefoxCapabilities(){
        DesiredCapabilities capabilities = DesiredCapabilities.firefox();
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(false);
        capabilities.merge(options);
        String osname=System.getProperty("os.name");
        FirefoxProfile profile;
        if(osname.lastIndexOf("Windows")==0){
            String username=System.getProperty("user.name");
            String path="C:\\Users\\"+username+"\\AppData\\Roaming\\Mozilla\\Firefox\\Profiles";
            File file=new File(path);
            String[] profiles=file.list();
            file=new File(path+"\\"+profiles[0]);
            profile =  new FirefoxProfile(file);
            capabilities.setCapability("firefox_profile", profile);
        }
        //some realization for unix :)
        return capabilities;
    }
}
