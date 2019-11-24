package controller;

import com.sun.org.apache.xpath.internal.operations.String;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.SoHoKhau;

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

    private ObservableList<SoHoKhau> soHoKhauObservableList = FXCollections.observableArrayList(
            new SoHoKhau(1,"Nguyen Văn A", "2313313", "Bach Khoa, Hai Ba Trung, Ha Noi", 2),
            new SoHoKhau(2, "Nguyen Van B", "34234343", "Thanh Nha, Hai Ba Trung, Ha Noi", 3)
    );
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("maHoKhau"));
        tableColumnTenChuHo.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("tenChuHo"));
        tableColumnCCCD.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("CCCD"));
        tableColumnDiaCHi.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));
        tableColumnSoNhanKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("soNhanKhau"));
        tableViewHoKhau.setItems(soHoKhauObservableList);
    }
}
