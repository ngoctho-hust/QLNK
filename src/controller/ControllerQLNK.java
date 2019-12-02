package controller;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.NhanKhau;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ControllerQLNK implements Initializable {
    @FXML
    private TableView tableViewNhanKhau;
    @FXML
    private TableColumn<NhanKhau, String> tableColumnMaHoKhau;
    @FXML
    private TableColumn<NhanKhau, String> tableColumnHoVaTen;
    @FXML
    private TableColumn<NhanKhau, String> tableColumnGioiTinh;
    @FXML
    private TableColumn<NhanKhau, String> tableColumnNgaySinh;
    @FXML
    private TableColumn<NhanKhau, Integer> tableColumnMaNhanKhau;
    @FXML
    private TextField txtTimKiem;
    @FXML
    private Button btnQLNK;
    @FXML
    private Button btnChinhSua;

    private ObservableList<NhanKhau> NhanKhauObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("maNhanKhau"));
        tableColumnHoVaTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        tableColumnNgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        tableColumnGioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maHoKhau"));

        NhanKhauObservableList = FXCollections.observableList(Main.nhanKhauArrayList);

        ConnectSQLServer cndb =  new ConnectSQLServer();
        cndb.pullDataNhanKhau();

        btnQLNK.setOnAction(actionEvent->{
            try {
                Parent blad = FXMLLoader.load(getClass().getResource("/view/quanLyHoKhau.fxml"));
                Scene scene = new Scene(blad);
                Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                Image image = new Image("/drawable/icon.png");
                appStage.getIcons().add(image);
                appStage.setTitle("QLHK");
                appStage.setScene(scene);
                appStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

        });
        FilteredList<NhanKhau> filteredList = new FilteredList<>(NhanKhauObservableList, p ->true);
        txtTimKiem.textProperty().addListener((observable, oldVable, newValue) ->{
            filteredList.setPredicate(nhanKhau->{
                if(newValue == null || newValue.isEmpty()){
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                if (nhanKhau.getMaHoKhau().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                } else if(nhanKhau.getHoTen().toLowerCase().contains(lowerCaseFilter)){
                    return true;
                }
                return false;
            });
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

        SortedList<NhanKhau> nhanKhaus = new SortedList<>(filteredList);

        nhanKhaus.comparatorProperty().bind(tableViewNhanKhau.comparatorProperty());
        tableViewNhanKhau.setItems(nhanKhaus);
    }
}
