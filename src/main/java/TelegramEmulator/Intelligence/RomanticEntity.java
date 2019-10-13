package TelegramEmulator.Intelligence;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class RomanticEntity extends Entity {

    private String[] phrases;

    public RomanticEntity(String path){
        this.entity=new File(path);
        String jsonText="";
        try {
            FileReader fileReader = new FileReader(this.entity);
            int c;
            while((c=fileReader.read())!=-1){
                jsonText+=(char)c;
            }
            fileReader.close();
        }catch (IOException ioe){
            System.out.println("Exeption!");
        }
        Gson g=new Gson();
        JsonObject jsonObject=g.fromJson(jsonText,JsonObject.class);
        JsonElement jsonElement=jsonObject.get("phrases");
        JsonArray jsonArray=jsonElement.getAsJsonArray();
        this.phrases=new String[jsonArray.size()];
        for (int i=0;i<jsonArray.size();i++){
            phrases[i]=jsonArray.get(i).getAsString();
        }
    }

    public void printAllPhrases(){
        for(String s:phrases){
            System.out.println(s);
        }
    }

}
