package controller;

import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SoHoKhau;

import java.awt.event.ActionEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerQLHK implements Initializable {
    @FXML
    private TableView tableViewHoKhau;
    @FXML
    private TableColumn<SoHoKhau, Integer> tableColumnMaHoKhau;
    @FXML
    private TableColumn<SoHoKhau, String> tableColumnTenChuHo;
    @FXML
    private TableColumn<SoHoKhau, String> tableColumnCCCD;
    @FXML
    private TableColumn<SoHoKhau, String> tableColumnDiaCHi;
    @FXML
    private TableColumn<SoHoKhau, Integer> tableColumnSoNhanKhau;
    @FXML
    private Button btn1;
    @FXML
    private TextField txtMaHoKhau;
    @FXML
    private TextField txtTenChuHo;
    @FXML
    private TextField txtCCCD;

    private ObservableList<SoHoKhau> soHoKhauObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addList();
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("maHoKhau"));
        tableColumnTenChuHo.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("tenChuHo"));
        tableColumnCCCD.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("CCCD"));
        tableColumnDiaCHi.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));
        tableColumnSoNhanKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("soNhanKhau"));
        tableViewHoKhau.setItems(soHoKhauObservableList);
        btn1.setOnAction(event -> {
            txtTenChuHo.setText("Test");
        });
    }

    public void addList(){
        Main.soHoKhauArrayList.add(new SoHoKhau(1,"Nguyen Văn A", "2313313", "Bach Khoa, Hai Ba Trung, Ha Noi", 2));
        Main.soHoKhauArrayList.add(new SoHoKhau(2, "Nguyen Van B", "34234343", "Thanh Nha, Hai Ba Trung, Ha Noi", 3));
        soHoKhauObservableList = FXCollections.observableList(Main.soHoKhauArrayList);
    }
}
