package UI;

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
        list.add(new QuesAns("hello", "hello2"));
        table.setItems(list);

        enter.setOnAction(event -> {
            if (!loginText.getText().equals("") && !passText.getText().equals("")) {
                question.setText("");
                //Код селениум
                question.setText("Введите url");
            }
            else
            {
                question.setText("Введите данные!!!!");
            }
        });

        GoToPage.setOnAction(event -> {
            if (!url_.getText().equals("")) {
                //Код селениум
            }
        });


    }
}
