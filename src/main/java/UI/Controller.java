package UI;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.openqa.selenium.InvalidArgumentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Config;

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
    private Button enter;

    @FXML
    void initialize() {
        ChromeDriver driver = Main.webDriver;


        question.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        answer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
        list = FXCollections.observableList(new ArrayList<>());
        table.setItems(list);

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
        });
    }
}
