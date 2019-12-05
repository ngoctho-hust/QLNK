package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.SoHoKhau;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    private TextField txtTimKiem;
    @FXML
    private Button btnQLNK;
    @FXML
    private Button btnChinhSua;
    @FXML
    private Button btnThemMoiSHK;
    @FXML
    private Button btnThongKe;
    @FXML
    private Button btnXoa;

    private ObservableList<SoHoKhau> soHoKhauObservableList;
    ConnectSQLServer cndb =  new ConnectSQLServer();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("maHoKhau"));
        tableColumnTenChuHo.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("tenChuHo"));
        tableColumnCCCD.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("CCCD"));
        tableColumnDiaCHi.setCellValueFactory(new PropertyValueFactory<SoHoKhau, String>("DiaChi"));
        tableColumnSoNhanKhau.setCellValueFactory(new PropertyValueFactory<SoHoKhau, Integer>("soNhanKhau"));


        soHoKhauObservableList = FXCollections.observableList(Main.soHoKhauArrayList);
        refreshTable();

        btnQLNK.setOnAction(actionEvent->{
            try {
                Parent blad = FXMLLoader.load(getClass().getResource("/view/quanLyNhanKhau.fxml"));
                Scene scene = new Scene(blad);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Image image = new Image("/drawable/icon.png");
                appStage.getIcons().add(image);
                appStage.setTitle("QLNK");
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });

        btnChinhSua.setOnAction(actionEvent-> {
//            ObservableList<SoHoKhau> soHoKhaus = tableViewHoKhau.getSelectionModel().getSelectedItems();
//            System.out.println(soHoKhaus.get(0).getTenChuHo());
            Parent parent = null;
            try {
                parent = FXMLLoader.load(getClass().getResource("/view/SuasoHK.fxml"));
                Scene scene = new Scene(parent);
                Stage stageChinhSua = new Stage();
                Image image = new Image("/drawable/icon.png");
                stageChinhSua.getIcons().add(image);
                stageChinhSua.setTitle("Chỉnh sửa");
                stageChinhSua.setScene(scene);
                stageChinhSua.initModality(Modality.WINDOW_MODAL);
                stageChinhSua.initOwner((Stage) ((Node) actionEvent.getSource()).getScene().getWindow());
                stageChinhSua.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        btnXoa.setOnAction(actionEvent->{
            ObservableList<SoHoKhau> soHoKhaus = tableViewHoKhau.getSelectionModel().getSelectedItems();
            if (soHoKhaus.get(0) != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xoá sổ hộ khẩu");
                alert.setHeaderText("Bao gồm xoá tất cả các nhân khẩu trong sổ");
                alert.setContentText("Mã sổ: "+soHoKhaus.get(0).getMaHoKhau()+ "\nTên chủ hộ: " +soHoKhaus.get(0).getTenChuHo());
                alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait().ifPresent((btnType)->{
                    if (btnType == ButtonType.OK){
                        ConnectSQLServer connectSQLServer = new ConnectSQLServer();
                        connectSQLServer.xoaSoHoKhau(soHoKhaus.get(0).getMaHoKhau());
                        refreshTable();
                    } else if (btnType == ButtonType.CANCEL){

                    }

                });
            }
        });

        btnThemMoiSHK.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Thêm sổ hộ khẩu");
            dialog.setContentText("Điền địa chỉ:");
// Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            if(!result.get().equals("")){
                ConnectSQLServer connectSQLServer = new ConnectSQLServer();
                connectSQLServer.themSoHoKhau(result.get());
                refreshTable();
            }
        });
    }

    public void refreshTable(){
        cndb.pullData();
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
