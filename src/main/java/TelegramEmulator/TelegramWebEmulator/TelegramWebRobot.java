package TelegramEmulator.TelegramWebEmulator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;



/* The superposition of atomic operations
*
* */
public class TelegramWebRobot extends TelegramWebAccount {
    private Lock locker;
    private static ArrayList<Timer> timers;
    public TelegramWebRobot(WebDriver webDriver, WebDriverWait wait){
        super(webDriver,wait);
        timers=new ArrayList<>();
        locker=new ReentrantLock();
    }

     public void sendPlannedMessage(String whom,String message,Calendar when){
        when.add(Calendar.SECOND,(int)(-1*(delay/1000)));
        Timer timer=new Timer();
        timers.add(timer);
        int timerIndex=timers.size()-1;
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                locker.lock();
                sendMessage(whom,message);          //1 delay
                timers.get(timerIndex).cancel();
                timers.remove(timer);
                locker.unlock();
            }
        },when.getTime());
     }

     public void sendPlannedMessage(String chatName,String message,Calendar when,long period){
         locker.lock();
         when.add(Calendar.SECOND,(int)(-1*((delay/1000)+2)));
         Timer timer=new Timer();
         timers.add(timer);
         selectChat(chatName);//1 delay+2seconds
         final String targetChat=getCurrentChat();
         locker.unlock();
         timer.schedule(new TimerTask() {
            @Override
            public void run() {
                locker.lock();
                //System.out.println("Target chat: "+targetChat);
                if(!currentChat.equals(targetChat))
                    selectChat(targetChat);//1 delay
                sendMessage(message);
                locker.unlock();
            }
        },when.getTime(),period*ONESECOND);
     }

     public void stopAllTimers(){
         for (Timer t:timers) {
             t.cancel();
             timers.remove(t);
         }
     }

     @Override
     public void close(){
        stopAllTimers();
        super.close();
     }


}
