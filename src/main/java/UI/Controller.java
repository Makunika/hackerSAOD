package UI;

import excel.Excel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.ArrayList;
import java.util.List;

public class Controller {


    ObservableList<QuesAns> list;

    @FXML
    private TextField loginText;

    @FXML
    private TextField passText;

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

        question.setCellValueFactory(cellData -> cellData.getValue().questionProperty());
        answer.setCellValueFactory(cellData -> cellData.getValue().answerProperty());
        list = FXCollections.observableList(new ArrayList<>());
        list.add(new QuesAns("Для завершения поиска в глубин", ""));
        table.setItems(list);

        enter.setOnAction(event -> {


            Excel excel = new Excel(list);

            if (!loginText.getText().equals("") && !passText.getText().equals("")) {
                debug.setText("");
                //Код селениум
                debug.setText("Введите url");
            }
            else
            {
                debug.setText("Введите данные!!!!");
            }
        });

        GoToPage.setOnAction(event -> {
            if (!url_.getText().equals("")) {
                //Код селениум
            }
        });


    }
}
