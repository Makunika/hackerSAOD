package excel;

import UI.QuesAns;
import javafx.collections.ObservableList;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class Excel {

    public Excel(ObservableList<QuesAns> list) {
        try {
            String fileName = "src/main/resorces/saodCheat.xlsx";

            InputStream input = new FileInputStream(fileName);
            XSSFWorkbook wb = new XSSFWorkbook(input);
            XSSFSheet sheet = wb.getSheetAt(0);

            for (QuesAns quest: list) {
                quest.setAnswer(new FindAndGetAnswer(sheet, quest.getQuestion()).getAnswer());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
