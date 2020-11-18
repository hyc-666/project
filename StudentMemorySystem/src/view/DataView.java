package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import javax.swing.event.MenuDragMouseEvent;

/**
 * @author hyc
 * @date 2020/11/18
 */
public class DataView {
    public void showDataView(){
        Stage stage = new Stage();
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("学生信息管理系统");
        stage.show();

        MenuBar menuBar = new MenuBar();
        Menu menuLook = new Menu("基本信息查看");
        Menu menuUpdate = new Menu("学生信息修改");
    }
}
