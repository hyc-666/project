package view;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import pojo.Student;

import javax.swing.event.MenuDragMouseEvent;

/**
 * @author hyc
 * @date 2020/11/18
 */
public class DataView {

    private Student student;//本页面持有一个student对象方便处理
    /**
     * 展示登录成功以后的页面
     * @param student 登录成功后要将查询到的结果传递过来方便本页面做数据展示和处理
     */
    public void showDataView(Student student){
        this.student = student;
        Stage stage = new Stage();
        AnchorPane pane = new AnchorPane();
        Scene scene = new Scene(pane);
        stage.setScene(scene);
        stage.setTitle("学生信息管理系统");
        stage.show();

        MenuBar menuBar = new MenuBar();
        Menu menuLook = new Menu("基本信息查看");
        Menu menuUpdate = new Menu("学生信息修改");

        menuBar.getMenus().addAll(menuLook,menuUpdate);
        pane.getChildren().addAll(menuBar);
    }
}
