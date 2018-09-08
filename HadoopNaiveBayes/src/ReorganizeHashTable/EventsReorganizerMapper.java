package ReorganizeHashTable;
import java.io.IOException;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class EventsReorganizerMapper extends Mapper<Object,Text,Text, Text> {
    @Override
    public void map(Object inputKey, Text inputValue, Context context) throws IOException, InterruptedException {
        String line = inputValue.toString();
        ParseRecord recordParser = new ParseRecord();
        recordParser.parse(line);
        String word=recordParser.getWord();
        if (word.equals("")) {
            assert true;
        } else {
            context.write(new Text(word), new Text(line));
        }

    }
}
