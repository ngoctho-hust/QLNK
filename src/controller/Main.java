package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.NhanKhau;
import model.SoHoKhau;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    public static List<SoHoKhau> soHoKhauArrayList = new ArrayList<>();
    public static List<NhanKhau> nhanKhauArrayList = new ArrayList<>();


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("/view/quanLyHoKhau.fxml"));
        primaryStage.setTitle("Quản lý hộ khẩu nhân khẩu");
        primaryStage.setScene(new Scene(root, 1510, 810));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
