package controller;

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


import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class ControllerQLHK implements Initializable {
    @FXML
    private TableView tableViewHoKhau;
    @FXML
    private TableColumn<SoHoKhau, String> tableColumnMaHoKhau;
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
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("maHoKhau"));
        tableColumnTenChuHo.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("tenChuHo"));
        tableColumnCCCD.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("CCCD"));
        tableColumnDiaCHi.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));
        tableColumnSoNhanKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("soNhanKhau"));
        addList();
        btn1.setOnAction(event -> {
            String tenChuHo = txtTenChuHo.getText();
            String maHoKhau = txtMaHoKhau.getText();
            String CCCD = txtCCCD.getText();
            int n = Main.soHoKhauArrayList.size();
            List<SoHoKhau> searchList = new ArrayList<>();
            for (int i=0; i<n; i++){
                if (maHoKhau.equals(Main.soHoKhauArrayList.get(i).getMaHoKhau())){
                    searchList.add(Main.soHoKhauArrayList.get(i));
                }
            }
            soHoKhauObservableList = FXCollections.observableArrayList(searchList);
            tableViewHoKhau.setItems(soHoKhauObservableList);
        });
    }

    public void addList(){
        ConnectSQLServer cndb =  new ConnectSQLServer();
        cndb.pullData();
        soHoKhauObservableList = FXCollections.observableList(Main.soHoKhauArrayList);
        tableViewHoKhau.setItems(soHoKhauObservableList);
    }
}
