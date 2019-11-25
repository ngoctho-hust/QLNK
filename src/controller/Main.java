package controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.NhanKhau;
import model.SoHoKhau;

import java.util.ArrayList;

public class Main extends Application {

    public ArrayList<SoHoKhau> soHoKhauArrayList;
    public ArrayList<NhanKhau> nhanKhauArrayList;
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
