package excel;


import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;

public class FindAndGetAnswer {

    private final String question;
    private final XSSFSheet sheet;

    public FindAndGetAnswer(XSSFSheet sheet, String question) {
        this.question = question;
        this.sheet = sheet;
    }

    public String getAnswer() {
        return findRow(question);
    }




    private String findRow(String question) {
        for (Row row : sheet) {
            if (row.getCell(0).getCellTypeEnum() == CellType.STRING
                    && row.getCell(0).getStringCellValue().contains(question)) {
                if (row.getRowNum() < 3) {
                    return "Нет ответа";
                } else {
                    return row.getCell(2).getStringCellValue();
                }
            }
        }
        return "Нет ответа";
    }
}
