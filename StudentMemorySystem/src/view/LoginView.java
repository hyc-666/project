package view;

import ctrl.BaseCtrl;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import pojo.Student;


/**
 * @author hyc
 * @date 2020/11/17
 */
public class LoginView {
    private  BaseCtrl baseCtrl = new BaseCtrl();
    private Student student = null;
    private boolean loginSuccess;

    public boolean isLoginSuccess() {
        return loginSuccess;
    }

    /**
     * 负责显示登录界面,并处理登录页面的信息
     * @param primaryStage 登录界面由调用程序创建
     */
    public void loginView(Stage primaryStage){
        AnchorPane anchorPane = new AnchorPane();
//        boolean success = false;
        HBox hBox_1 = new HBox(40);
        HBox hBox_2 = new HBox(40);
        HBox hBox_3 = new HBox(180);
        VBox vBox = new VBox(20);

        Text name_input = new Text("请输入账号:");
        Text password_input = new Text("请输入密码:");

        Button buttonCancel = new Button("退出");
        Button buttonLogin = new Button("登录");
        hBox_3.getChildren().addAll(buttonCancel,buttonLogin);


        TextField stu_num = new TextField();
        PasswordField stu_password = new PasswordField();

        hBox_1.getChildren().addAll(name_input,stu_num);
        hBox_2.getChildren().addAll(password_input,stu_password);
        vBox.getChildren().addAll(hBox_1,hBox_2,hBox_3);


        anchorPane.getChildren().addAll(vBox);

        Scene scene = new Scene(anchorPane);

        primaryStage.setTitle("学生管理系统登录窗口");

        primaryStage.setHeight(800);
        primaryStage.setWidth(800);

        AnchorPane.setTopAnchor(vBox,200.0);
        AnchorPane.setLeftAnchor(vBox,200.0);

        primaryStage.setScene(scene);

        primaryStage.show();


        //取消键直接退出
        buttonCancel.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                System.exit(0);
            }
        });

        buttonLogin.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                //获取输入的账号
                String userNumber = stu_num.getText();
                String password = stu_password.getText();

                Student student = new Student(userNumber,password);
                loginCheck(student,primaryStage);
            }
        });
    }
    public void errorHandle(Stage primaryStage){
        Stage stage = new Stage();
        DialogPane dialogPane = new DialogPane();
        dialogPane.setContentText("用户名或密码错误!");
        dialogPane.getButtonTypes().add(ButtonType.CLOSE);
        Scene sc = new Scene(dialogPane);
        stage.initStyle(StageStyle.UTILITY);
        stage.initOwner(primaryStage);
        stage.initModality(Modality.WINDOW_MODAL);

        Button close = (Button)dialogPane.lookupButton(ButtonType.CLOSE);
        close.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.close();
            }
        });

        stage.setTitle("登录错误");
        stage.setResizable(false);

        stage.setScene(sc);
        stage.show();

    }

    public void loginCheck(Student student,Stage primaryStage){
        this.student = baseCtrl.queryStudentBynNuAndPs(student);
        if (this.student == null){
            errorHandle(primaryStage);
        }else{
            primaryStage.close();
            showDataView(getLoginRes());
        }
    }
    public boolean getLoginRes(){
        //启动登录界面
        System.out.println(Thread.currentThread().getName());
        return student != null;
    }
    public void showDataView(boolean ok){
        System.out.println("登录成功");
        if (ok){
            new DataView().showDataView();
        }
    }
}
