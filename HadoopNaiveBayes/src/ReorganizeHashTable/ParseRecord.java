package ReorganizeHashTable;
import java.util.regex.Pattern;

public class ParseRecord {
    private String word;
    public void parse(String line){
        if (Pattern.matches("^Y=.*\\^W=[a-zA-Z]*.*",line)) {
        String wordWithSpace = line.split("W=",2)[1];
        wordWithSpace =  wordWithSpace.trim();
        //System.out.println("In record parser: "+wordWithSpace);
        //TimeUnit.SECONDS.sleep(5)
            word=wordWithSpace.split("\t")[0].trim();
        if(Pattern.matches("\\s|",word) || Pattern.matches("[0-9]*",word) || Pattern.matches("\\b(\\w)\\1+\\b",word)|| Pattern.matches("^\\w{1}$",word))
        {
            word = "";

            //System.out.println("In record parser: "+word);
        }
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
