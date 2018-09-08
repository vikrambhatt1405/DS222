package ReorganizeHashTable;
import java.io.IOException;
import java.util.ArrayList;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class EventsReorganizerReducer extends Reducer<Text, Text, Text, Text> {
        @Override
        public void reduce(Text inputKey,Iterable<Text> inputValues,Context context) throws IOException,InterruptedException{
                ArrayList<String> arrayList = new ArrayList<>();
                for(Text wordEvent:inputValues){
                        arrayList.add(wordEvent.toString());
                }
                context.write(new Text(inputKey.toString()),new Text("1"+"\t"+arrayList.toString()));
        }

}
