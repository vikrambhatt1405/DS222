import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;
public class EventsHashTableMapper extends Mapper<Object,Text,Text, IntWritable> {
    @Override
    public void map(Object inputKey,Text inputValue,Context context) throws IOException,InterruptedException{
        String line = inputValue.toString();
        RecordParser recordParser = new RecordParser();
        recordParser.parse(line);

        for(String label:recordParser.getLabels()){
            context.write(new Text("Y=ANY"), new IntWritable(1));
            context.write(new Text("Y="+label.replaceAll("(^[ ]*)|([ ]*$)", "")), new IntWritable(1) );
            for(String word:recordParser.getArticle().split(" ")){
                context.write(new Text("Y="+label.replaceAll("(^[ ]*)|([ ]*$)", "")+"^"+"W="+word), new IntWritable(1));
                context.write(new Text("Y="+label.replaceAll("(^[ ]*)|([ ]*$)", "")+"^"+"W=ANY"), new IntWritable(1));
            }
        }
    }

}
