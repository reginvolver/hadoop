import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;

public class DataCleanerAndUploader {
    private static final String HDFS_URI = "hdfs://your-hadoop-cluster/";

    public static void main(String[] args) throws IOException {
        String localInputPath = "/path/to/山西.csv";
        String hdfsOutputPath = "/user/hive/warehouse/山西_cleaned.csv";

        Configuration conf = new Configuration();
        conf.set("fs.defaultFS", HDFS_URI);
        FileSystem fs = FileSystem.get(URI.create(HDFS_URI), conf);
        OutputStream os = fs.create(new Path(hdfsOutputPath));

        try (BufferedReader br = new BufferedReader(new FileReader(localInputPath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String cleanedLine = cleanData(line);
                os.write((cleanedLine + "\n").getBytes());
            }
        } finally {
            os.close();
        }
    }

    private static String cleanData(String dataLine) {
        // 这里添加具体的清洗逻辑，例如删除不规范的数据等
        return dataLine.replaceAll("￥", "").replaceAll("/人", "");
    }
}
