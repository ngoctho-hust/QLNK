package controller.SHK;

import dao.ConnectSQLServer;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.NhanKhau;
import model.SoHoKhau;


import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChonSHKChuyenHo implements Initializable {
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
    private TextField txtTimKiem;
    @FXML
    private Button btnOk;
    @FXML
    private Button btnHuy;

    private ObservableList<SoHoKhau> soHoKhauObservableList;
    private ObservableList<NhanKhau> nhanKhauObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("maHoKhau"));
        tableColumnTenChuHo.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("tenChuHo"));
        tableColumnCCCD.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("CCCD"));
        tableColumnDiaCHi.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));
        tableColumnSoNhanKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("soNhanKhau"));


        soHoKhauObservableList = FXCollections.observableList(Main.soHoKhauArrayList);
        refreshTable();

        btnOk.setOnAction(event -> {
            ObservableList<SoHoKhau> soHoKhaus = tableViewHoKhau.getSelectionModel().getSelectedItems();
            if(soHoKhaus.get(0)!=null){
                boolean check = false;
                for (NhanKhau nhanKhau: nhanKhauObservableList
                     ) {
                    check = ConnectSQLServer.chuyenHo(nhanKhau.getMaNhanKhau(), soHoKhaus.get(0).getMaHoKhau());
                    ConnectSQLServer.updateHistory(nhanKhau.getMaHoKhau(), "Nhân khẩu chuyển đi: "+nhanKhau.getHoTen());
                    ConnectSQLServer.updateHistory(soHoKhaus.get(0).getMaHoKhau(), "Nhân khẩu chuyển đến: "+ nhanKhau.getHoTen());
                }
                if(check){
                    check = false;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText("Compelete!");
                    alert.setContentText("Chuyển hộ thành công");
                    alert.showAndWait();
                    Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    stage.close();
                }
            }
        });

        btnHuy.setOnAction(event -> {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.close();
        });
    }

    public void refreshTable(){
        ConnectSQLServer.pullData();
        FilteredList<SoHoKhau> filteredList = new FilteredList<>(soHoKhauObservableList, p ->true);
        txtTimKiem.textProperty().addListener((observable, oldVable, newValue) ->{
            filteredList.setPredicate(soHoKhau->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (soHoKhau.getMaHoKhau().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(soHoKhau.getTenChuHo()!= null){
                    if(soHoKhau.getTenChuHo().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    } else if(soHoKhau.getCCCD().toLowerCase().contains(lowerCaseFilter)){
                        return true;
                    }
                }
                return false;
            });
        });
        SortedList<SoHoKhau> hoKhauSortedList = new SortedList<>(filteredList);
        hoKhauSortedList.comparatorProperty().bind(tableViewHoKhau.comparatorProperty());
        tableViewHoKhau.setItems(hoKhauSortedList);
    }
    public void setNhanKhauObservableList(ObservableList<NhanKhau> nhanKhauObservableList){
        this.nhanKhauObservableList = nhanKhauObservableList;
    }
}
