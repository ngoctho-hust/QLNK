package controller.NK;

import controller.ConnectSQLServer;
import controller.Main;
import controller.SHK.ControllerSuaNK;
import controller.SHK.ControllerThemNK;
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
import javafx.stage.Modality;
import javafx.stage.Stage;
import model.NhanKhau;


import java.io.IOException;
import java.net.URL;
import java.util.Optional;
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
    @FXML
    private Button btnXoa;
    @FXML
    private Button btnThemMoiNhanKhau;

    private ObservableList<NhanKhau> NhanKhauObservableList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        tableColumnMaNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, Integer>("maNhanKhau"));
        tableColumnHoVaTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
        tableColumnNgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
        tableColumnGioiTinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("gioiTinh"));
        tableColumnMaHoKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maHoKhau"));

        NhanKhauObservableList = FXCollections.observableList(Main.nhanKhauArrayList);

        refreshTable();

        btnQLNK.setOnAction(actionEvent->{
            try {
                Parent blad = FXMLLoader.load(getClass().getResource("/view/SHK/quanLyHoKhau.fxml"));
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

        btnChinhSua.setOnAction(event-> {
//            ObservableList<SoHoKhau> soHoKhaus = tableViewHoKhau.getSelectionModel().getSelectedItems();
//            System.out.println(soHoKhaus.get(0).getTenChuHo());
            ObservableList<NhanKhau> nhanKhaus = tableViewNhanKhau.getSelectionModel().getSelectedItems();
            if(nhanKhaus.get(0)!=null){
                Parent parent = null;
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/view/SHK/suaNK.fxml"));
                    parent = loader.load();
                    Scene scene = new Scene(parent);
                    Stage stageChinhSua = new Stage();
                    Image image = new Image("/drawable/icon.png");
                    stageChinhSua.getIcons().add(image);
                    stageChinhSua.setTitle("Chỉnh sửa nhân khẩu");
                    stageChinhSua.setScene(scene);
                    stageChinhSua.initModality(Modality.WINDOW_MODAL);
                    stageChinhSua.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
                    ControllerSuaNK controllerSuaNK = loader.getController();
                    controllerSuaNK.setNhanKhau(nhanKhaus.get(0));
                    stageChinhSua.showAndWait();
                    refreshTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        btnXoa.setOnAction(event -> {
            ObservableList<NhanKhau> nhanKhaus = tableViewNhanKhau.getSelectionModel().getSelectedItems();
            if (nhanKhaus.get(0) != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Xoá nhân khẩu");
                alert.setHeaderText("Thông tin nhân khẩu:");
                alert.setContentText("Mã nhân khẩu: "+nhanKhaus.get(0).getMaNhanKhau()+ "\nHọ và tên: " +nhanKhaus.get(0).getHoTen());
                alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
                alert.showAndWait().ifPresent((btnType)->{
                    if (btnType == ButtonType.OK){
                        ConnectSQLServer.xoaNhanKhau(nhanKhaus.get(0).getMaNhanKhau());
                        refreshTable();
                    } else if (btnType == ButtonType.CANCEL){

                    }
                });
            }
        });

        btnThemMoiNhanKhau.setOnAction(event -> {
            TextInputDialog dialog = new TextInputDialog("");
            dialog.setTitle("Thêm nhân khẩu");
            dialog.setContentText("Điền mã hộ khẩu:");
            // Traditional way to get the response value.
            Optional<String> result = dialog.showAndWait();
            boolean check = ConnectSQLServer.existSHK(result.get());
            if (check){
                Parent parent = null;
                FXMLLoader loader = new FXMLLoader();
                try {
                    loader.setLocation(getClass().getResource("/view/SHK/themNK.fxml"));
                    parent = loader.load();
                    Scene scene = new Scene(parent);
                    Stage stageChinhSua = new Stage();
                    Image image = new Image("/drawable/icon.png");
                    stageChinhSua.getIcons().add(image);
                    stageChinhSua.setTitle("Thêm nhân khẩu");
                    stageChinhSua.setScene(scene);
                    stageChinhSua.initModality(Modality.WINDOW_MODAL);
                    stageChinhSua.initOwner((Stage) ((Node) event.getSource()).getScene().getWindow());
                    ControllerThemNK controllerThemNK = loader.getController();
                    controllerThemNK.setMaHoKhau(result.get());
                    stageChinhSua.showAndWait();
                    refreshTable();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("");
                alert.setHeaderText("Lỗi");

                alert.setContentText("Không tồn tại sổ hộ khẩu");
                alert.showAndWait();
            }
        });
    }

    public void refreshTable(){
        ConnectSQLServer.pullDataNhanKhau();
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

        SortedList<NhanKhau> nhanKhaus = new SortedList<>(filteredList);

        nhanKhaus.comparatorProperty().bind(tableViewNhanKhau.comparatorProperty());
        tableViewNhanKhau.setItems(nhanKhaus);
    }
}
