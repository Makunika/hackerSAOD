package UI;

import excel.Excel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Config;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.io.Console;
import java.sql.Driver;
import java.util.ArrayList;
import java.util.List;

public class Controller {


    ObservableList<QuesAns> list;

    @FXML
    private TextField loginText;

    @FXML
    private PasswordField passText;

    @FXML
    private TextArea debug;

    @FXML
    private TextField url_;

    @FXML
    private Button GoToPage;

    @FXML
    private TableView<QuesAns> table;

    @FXML
    private TableColumn<QuesAns, String> question;

    @FXML
    private TableColumn<QuesAns, String> answer;

    @FXML
    private TableColumn<QuesAns, String> number;

    @FXML
    private Button enter;

    @FXML
    void initialize() {
        ChromeDriver driver = Main.webDriver;


        question.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        answer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
        number.setCellValueFactory(cellData -> cellData.getValue().numberProperty());
        list = FXCollections.observableList(new ArrayList<>());
        table.setItems(list);

        table.setRowFactory(new Callback<TableView<QuesAns>, TableRow<QuesAns>>() {
            @Override
            public TableRow<QuesAns> call(TableView<QuesAns> param) {
                final TableRow<QuesAns> row = new TableRow<>();

                row.setOnMouseClicked(event -> {
                    setClipboard(table.getSelectionModel().getSelectedItem().getQuestion());
                });


                return row;
            }
        });

        enter.setOnAction(event -> {




            if (!loginText.getText().equals("") && !passText.getText().equals("")) {
                //Код селениум
                debug.setText("Введите url");

                driver.get(Config.getProperty("loginpage"));
                driver.findElementByXPath("//input[@id=\"username\"][@name=\"username\"]").sendKeys(loginText.getText());
                driver.findElementByXPath("//input[@id=\"password\"][@name=\"password\"]").sendKeys(passText.getText());
                driver.findElementByXPath("//button[@type=\"submit\"][@id=\"loginbtn\"]").click();
            }
            else
            {
                debug.setText("Введите данные!!!!");
            }
        });

        GoToPage.setOnAction(event -> {
            if (!url_.getText().equals("")) {
                //Код селениум
                driver.get(url_.getText());

                while(true){
                    try{
                        // Ищем кнопку "закончить тест".
                        WebElement qtext = driver.findElementByXPath("//input[@type=\"submit\"][@name=\"next\"][@value=\"Закончить попытку...\"]");

                        break;
                    }
                    catch (NoSuchElementException ignored){
                        ;
                    }

                    try {

                        WebElement qtext = driver.findElementByXPath("//div[@class=\"qtext\"]");

                        list.add(new QuesAns(qtext.getText().substring(0, qtext.getText().length() - 5), "???"));
                    }
                    catch (NoSuchElementException ignored){
                    }



                    driver.findElementByXPath("//input[@type=\"submit\"][@name=\"next\"][@value=\"Следующая страница\"]").click();
                }
            }
            Excel excel = new Excel(list);
        });
    }

    public static void setClipboard(String str) {
        StringSelection ss = new StringSelection(str);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(ss, null);
    }
}
