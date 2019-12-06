package controller;
import java.lang.String;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Callback;
import model.*;


import java.net.URL;


import java.util.ArrayList;
import java.util.ResourceBundle;
public class ControllerThemSHK implements Initializable {
	@FXML
    private TableView tableNhanKhau;
	@FXML
    private TableColumn<NhanKhau, String> colMaHoKhau;
    @FXML
    private TableColumn<NhanKhau, String> colHoTen;
    @FXML
    private TableColumn<NhanKhau, String> colNgaySinh;
    @FXML
    private TableColumn<NhanKhau, String> colQueQuan;
    @FXML
    private TableColumn<NhanKhau, String> colDanToc;
    @FXML
    private TableColumn<NhanKhau, String> colCMND;
    @FXML
    private TableColumn<NhanKhau, String> colTonGiao;
    @FXML
    private TableColumn<NhanKhau, String> colNgheNghiep;
    @FXML
    private TableColumn<NhanKhau, String> colNoiLamViec;
    @FXML
    private TableColumn<NhanKhau, String> colQuanHe;
    @FXML
    private TableColumn<NhanKhau, GioiTinh> colGioiTinh;
    @FXML
    private TableColumn<NhanKhau, String> colMaNhanKhau;
    @FXML
    private TableColumn<NhanKhau, String> colNoiOTruoc;
    @FXML
    private TableColumn<NhanKhau, String> colQuocTich;
    @FXML
    private TableColumn<NhanKhau, String> colTenGoiKhac;

	@FXML
	private Button btnThemNhanKhau;
	@FXML
	private Button btnChinhSua;
	@FXML
	private Button btnXoa;
	@FXML
	private Button btnLuu;
	@FXML
	private Button btnHuy;
	@FXML
	private Text textMaHoKhau;
	@FXML
	private TextField txtDiaChi;


	private String maHoKhau;
	private String diaChi;
	public ArrayList<NhanKhau> nhanKhauArrayList = new ArrayList<>();
	private ObservableList<NhanKhau> nhankhauObservableList;
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		khoiTaoTable();

		btnThemNhanKhau.setOnAction(event -> {

		});

		btnLuu.setOnAction(event -> {
			if (!txtDiaChi.getText().equals(diaChi)){
				ConnectSQLServer.updateDiaChi(maHoKhau, txtDiaChi.getText());
			}
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			stage.close();
		});

		btnHuy.setOnAction(event -> {
			Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
			ConnectSQLServer cnt = new ConnectSQLServer();
			cnt.xoaSoHoKhau(maHoKhau);
			stage.close();
		});
	}

	private void khoiTaoTable(){
		colHoTen.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("hoTen"));
		colNgaySinh.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngaySinh"));
		colQueQuan.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("queQuan"));
		colCMND.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("cmnd"));
		colDanToc.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("danToc"));
		colTonGiao.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("tonGiao"));
		colNgheNghiep.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("ngheNghiep"));
		colNoiLamViec.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("noiLamViec"));
		colQuanHe.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quanHe"));
		colQuocTich.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("quocTich"));
		colMaNhanKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maNhanKhau"));
		colMaHoKhau.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("maHoKhau"));
		colNoiOTruoc.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("noiThuongTruTruocKhiChuyenDen"));
		colTenGoiKhac.setCellValueFactory(new PropertyValueFactory<NhanKhau, String>("tenGoiKhac"));

		nhankhauObservableList = FXCollections.observableArrayList(nhanKhauArrayList);
		tableNhanKhau.setItems(nhankhauObservableList);
	}

	public void setMaVaDiaChiHoKhau(String maHoKhau, String diaChi) {
		this.maHoKhau = maHoKhau;
		this.diaChi = diaChi;
		textMaHoKhau.setText(maHoKhau);
		txtDiaChi.setText(diaChi);
	}

	private void refreshTable(){
		nhankhauObservableList.removeAll(nhankhauObservableList);
		nhankhauObservableList = FXCollections.observableArrayList(nhanKhauArrayList);
		tableNhanKhau.setItems(nhankhauObservableList);
	}
}
