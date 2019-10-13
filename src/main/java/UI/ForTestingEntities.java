package UI;



import TelegramEmulator.Intelligence.RomanticEntity;

public class ForTestingEntities {

    public static void main(String[] args)throws Exception{

        RomanticEntity r=new RomanticEntity("resources\\entities\\RomanticEntity.json");
        r.printAllPhrases();

    }
}
