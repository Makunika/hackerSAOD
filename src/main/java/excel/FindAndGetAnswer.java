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

        StringBuilder ans = new StringBuilder();
        for (Row row : sheet) {
            if (row.getCell(0).getCellTypeEnum() == CellType.STRING
                    && row.getCell(0).getStringCellValue().contains(question)) {
                if (row.getRowNum() < 3) {
                    if (ans.toString().equals("")) {
                        ans = new StringBuilder("Нет ответа");
                    } else {
                        ans.append(" ИЛИ " + "Нет ответа");
                    }
                } else {
                    if (ans.toString().equals("")) {
                        ans = new StringBuilder(row.getCell(2).getStringCellValue());
                    } else {
                        ans.append(" ИЛИ ").append(row.getCell(2).getStringCellValue());
                    }
                }
            }
        }
        return !ans.toString().equals("") ? ans.toString() : "Нет ответа или поищи вручную";
    }
}
