package TestMessager;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataOutputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.util.Progressable;

public class GenerateTestMessages {
    public static void main(String[] args) throws Exception {
        Path pt = new Path(args[0]);//Location of file in HDFS
        FileSystem fs = FileSystem.get(new Configuration());
        FSDataOutputStream outStream = fs.create(new Path(args[1]),  new Progressable() {
            public void progress() {
                System.out.println(".");
            }
        });
        BufferedReader br = new BufferedReader(new InputStreamReader(fs.open(pt)));
        String line;
        line = br.readLine();
        int counter = 0;
        while (line != null) {
            RecordParser recordParser = new RecordParser();
            recordParser.parse(line);
            for (String word : recordParser.getArticle().split(" ")) {
                //System.out.println(word.trim()+"\t"+Integer.toString(counter));
                outStream.writeChars(word.trim() + "\t" +"2"+ "\t" +Integer.toString(counter)+"\n");
            }
            line = br.readLine();
            counter = counter + 1;
        }
    }
}
