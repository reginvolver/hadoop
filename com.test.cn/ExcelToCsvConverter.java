import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.excel.read.metadata.ReadSheet;

// easyexcel
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ExcelToCsvConverter {

    public static void main(String[] args) {
        String excelFilePath = "/path/to/山西(1).xlsx";
        String csvFilePath = "/path/to/山西.csv";

        try {
            FileWriter csvWriter = new FileWriter(new File(csvFilePath));

            // 读取Excel文件并转换为CSV
            EasyExcel.read(excelFilePath, new PageReadListener<Object>(dataList -> {
                dataList.forEach(data -> {
                    try {
                        // 假设data是一个Object数组，每个元素代表一列
                        csvWriter.write(String.join(",", (CharSequence[]) data) + "\n");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            })).sheet().doRead();

            csvWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
