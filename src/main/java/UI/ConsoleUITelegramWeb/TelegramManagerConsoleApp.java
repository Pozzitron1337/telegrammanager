package UI.ConsoleUITelegramWeb;

import TelegramEmulator.TelegramWebEmulator.TelegramWebRobot;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.*;

public class TelegramManagerConsoleApp implements UI.App {
    private Scanner input;
    private ApplicationContext context;
    public TelegramManagerConsoleApp(){
        this.input =new Scanner(System.in);
        this.context=new ClassPathXmlApplicationContext("configs/config.xml");
    }

    public String getInput(){
        return input.nextLine();
    }
    public int getIntegerInput(){
        return input.nextInt();
    }
    public void printMenu(){
        System.out.println("1. Send the Message.");
        System.out.println("2. Send the Image.");
        System.out.println("3. Send the File.");
        System.out.println("4. Send the planned mesage.");
        System.out.println("5. Send the planned message periodically.");
        System.out.println("6. Select chat.");
        System.out.println("7. Stop all timers.");
        System.out.println("0. exit");
        System.out.print("Enter choise: ");
    }
    public static void main(String[] args) {
        TelegramManagerConsoleApp app=new TelegramManagerConsoleApp();
        TelegramWebRobot tr=app.context.getBean("telegramWebRobot",TelegramWebRobot.class);
        Scanner scan=new Scanner(System.in);
        tr.goToTelegramWeb();
        if(tr.isLoginWindow()){
            System.out.print("Enter phone number:");
            String phoneNumber=scan.nextLine();
            tr.enteringThePhoneNumber(phoneNumber);
            System.out.print("Enter phone code:");
            String phoneCode=scan.nextLine();
            tr.enteringThePhoneCode(phoneCode);
        }
        int choise;
        while (true) {
            try{
                app.printMenu();
                choise=Integer.parseInt(scan.nextLine());
                switch (choise) {
                    case -1:{
                        tr.printTheStateOfIncapsulatedVariables();
                        break;
                    }
                    case 0:{
                        tr.close();
                        return;
                    }
                    case 1: {
                        System.out.println("Whom: ");
                        String whom = scan.nextLine();
                        System.out.println("Message:");
                        String message = scan.nextLine();
                        tr.sendMessage(whom, message);
                        break;
                    }
                    case 2: {
                        System.out.println("Whom: ");
                        String whom = scan.nextLine();
                        System.out.println("Image path:");
                        String message = scan.nextLine();
                        tr.sendImage(whom, message);
                        break;
                    }
                    case 3: {
                        System.out.println("Whom:");
                        String whom = scan.nextLine();
                        System.out.println("File path");
                        String path = scan.nextLine();
                        tr.sendFile(whom, path);
                        break;
                    }
                    case 4:{
                        System.out.println("Whom: ");
                        String whom =scan.nextLine();
                        System.out.println("Message: ");
                        String message=scan.nextLine();
                        System.out.println("Year: ");
                        int year=scan.nextInt();
                        System.out.println("Month: ");
                        int month=scan.nextInt()-1;
                        System.out.println("Day: ");
                        int day=scan.nextInt();
                        System.out.println("Hour: ");
                        int hour=scan.nextInt();
                        System.out.println("Minute: ");
                        int minute=scan.nextInt();
                        System.out.println("Second: ");
                        int second=scan.nextInt();
                        Calendar when=new GregorianCalendar(year,month,day,hour,minute,second);
                        tr.sendPlannedMessage(whom,message,when);
                        System.out.println(when.getTime());
                        break;
                    }
                    case 5:{
                        System.out.println("Whom: ");
                        String whom =scan.nextLine();
                        System.out.println("Message: ");
                        String message=scan.nextLine();
                        System.out.println("Year: ");
                        int year=scan.nextInt();
                        System.out.println("Month: ");
                        int month=scan.nextInt()-1;
                        System.out.println("Day: ");
                        int day=scan.nextInt();
                        System.out.println("Hour: ");
                        int hour=scan.nextInt();
                        System.out.println("Minute: ");
                        int minute=scan.nextInt();
                        System.out.println("Second: ");
                        int second=scan.nextInt();
                        System.out.println("Period in seconds: ");
                        long period=scan.nextLong();
                        Calendar when=new GregorianCalendar(year,month,day,hour,minute,second);
                        tr.sendPlannedMessage(whom,message,when,period);
                        break;
                    }
                    case 6:{
                        System.out.println("Enter chat name: ");
                        String chatName=scan.nextLine();
                        tr.selectChat(chatName);
                        break;
                    }
                    case 7:{
                        tr.stopAllTimers();
                        break;
                    }
                    case 8:{

                        break;
                    }
                    default:continue;
                }
            }catch (InputMismatchException ime){
                System.out.println("Mismatch exception");
                continue;
            }
            catch (NumberFormatException nfe){
                continue;
            }
        }
    }
}











