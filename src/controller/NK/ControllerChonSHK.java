package controller.NK;

import controller.SHK.ControllerThemNK;
import javafx.stage.Modality;
import model.ConnectSQLServer;
import controller.Main;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import model.SoHoKhau;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerChonSHK implements Initializable {
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
                Parent parent = null;
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/view/SHK/themNK.fxml"));
                    parent = loader.load();
                    Scene scene = new Scene(parent);
                    Stage appStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    Image image = new Image("/drawable/icon.png");
                    appStage.getIcons().add(image);
                    appStage.setTitle("QLHK");
                    appStage.setScene(scene);
                    ControllerThemNK controllerThemNK = loader.getController();
                    controllerThemNK.setMaHoKhau(soHoKhaus.get(0).getMaHoKhau());
                    appStage.show();
                    refreshTable();
                } catch (IOException e) {
                    e.printStackTrace();
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
}
