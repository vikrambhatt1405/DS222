import java.io.IOException;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;
public class EventsHashTableReducer extends Reducer<Text, IntWritable, Text, IntWritable> {

        @Override
        public void reduce(Text inputKey, Iterable<IntWritable> inputValues, Context context)
                throws IOException, InterruptedException {

            int sumCounts = 0;
            for (IntWritable value : inputValues) {
                sumCounts = sumCounts+value.get();
            }
            context.write(inputKey,new IntWritable(sumCounts));
        }
    }

