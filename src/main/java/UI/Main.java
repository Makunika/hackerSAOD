package UI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.openqa.selenium.chrome.ChromeDriver;
import selenium.Config;

import java.io.IOException;

public class Main extends Application {

    static ChromeDriver webDriver;

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.setProperty("webdriver.chrome.driver", Config.getProperty("chromedriver"));

        webDriver = new ChromeDriver();

        Parent root = FXMLLoader.load(getClass().getResource("/fxml/sample.fxml"));
        primaryStage.setTitle("Почтовый клиент");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        primaryStage.setOnCloseRequest(event -> {
            webDriver.close();
        });
    }


    public static void main(String[] args) {
        launch(args);
    }
}
