package main;

import javafx.application.Application;
import javafx.stage.Stage;
import view.DataView;
import view.LoginView;

/**
 * @author hyc
 * @date 2020/11/11
 */
public class StartSystem extends Application {
    private LoginView loginStage = new LoginView();
    private DataView dataView = new DataView();
    public static void main(String[] args) {
        launch(args);

    }
    @Override
    public void start(Stage primaryStage) throws Exception {

        Stage stage = new Stage();
        loginStage.loginView(stage);
        boolean success = loginStage.isLoginSuccess();
//        System.out.println(Thread.currentThread().getName());
//        System.out.println(success);

    }
}
