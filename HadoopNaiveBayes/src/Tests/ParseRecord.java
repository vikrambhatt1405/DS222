package Tests;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
public class ParseRecord {
    private String word;
    public void parse(String line)throws Exception{
        if (Pattern.matches("^Y=.*\\^W=[a-zA-Z]*.*",line)) {
        String wordWithSpace = line.split("W=",2)[1];
        wordWithSpace =  wordWithSpace.trim();
        //System.out.println("In record parser: "+wordWithSpace);
        //TimeUnit.SECONDS.sleep(5);
        word=wordWithSpace.split("\t")[0];
            //System.out.println("In record parser: "+word);
        }
        else{
            //System.out.println("Didn't work");
            //TimeUnit.SECONDS.sleep(5);

            word="";
        }
    }


    public String getWord(){
        return word;
    }

}
