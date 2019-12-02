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
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
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
import javafx.util.Callback;
import model.*;


import java.net.URL;


import java.util.ResourceBundle;
public class ControllerAddNhanKhau implements Initializable {
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
    private TableColumn<NhanKhau, Boolean> colDeath;
	
	@FXML
	   private Button ADDBUTTON;
	@FXML
	   private Button butReMove;
	  
	   @FXML
	   private TextField tfHoTen;
	   @FXML
	   private TextField tfNgaySinh;
	   @FXML
	   private TextField tfQueQuan;
	   @FXML
	   private TextField tfCMND;
	   @FXML
	   private TextField tfDanToc;
	   @FXML
	   private TextField tfNgheNghiep;
	   @FXML
	   private TextField tfNoiLamViec;
	   @FXML
	   private TextField tfQuanHe;
	   @FXML
	   private TextField tfTonGiao;
	   @FXML
	   private TextField tfGioiTinh;
	   @FXML
	   private TextField tfTenGoiKhac;
	   @FXML
	   private TextField tfQuocTich;
	   @FXML
	   private TextField tfNoiOTruoc;
	   @FXML
	   private TextField tfMaNhanKhau;
	   @FXML
	   private TextField tfMaHoKhau;
	   
	   @FXML
	   private ComboBox<GioiTinh> comboGioiTinh;
	 
	   private ObservableList<NhanKhau> nhankhauObservableList;
	   @Override
	   public void initialize(URL location, ResourceBundle resources) {
		   	tableNhanKhau.setEditable(true);
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
	        colDeath.setCellValueFactory(new Callback<CellDataFeatures<NhanKhau, Boolean>, ObservableValue<Boolean>>() {
	        	 
	            @Override
	            public ObservableValue<Boolean> call(CellDataFeatures<NhanKhau, Boolean> param) {
	                NhanKhau person = param.getValue();
	  
	                SimpleBooleanProperty booleanProp = new SimpleBooleanProperty(person.isDeath());
	                booleanProp.addListener(new ChangeListener<Boolean>() {
	  
	                    @Override
	                    public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue,
	                            Boolean newValue) {
	                        person.setDeath(newValue);
	                    }
	                });
	                return booleanProp;
	            }
	        });
	        //true edit
	        ObservableList<GioiTinh> genderList = FXCollections.observableArrayList(GioiTinh.values());
	        //ObservableList<String> gioitinh = FXCollections.observableArrayList("Nam","Nu","Khac");
	        
	        colGioiTinh.setCellValueFactory(new Callback<CellDataFeatures<NhanKhau, GioiTinh>, ObservableValue<GioiTinh>>() {
	        	 
	           @Override
	            public ObservableValue<GioiTinh> call(CellDataFeatures<NhanKhau, GioiTinh> param) {
	                NhanKhau person = param.getValue();
	                // F,M
	                String genderCode = person.getGioiTinh();
	                GioiTinh gender = GioiTinh.getByCode(genderCode);
	                return new SimpleObjectProperty<GioiTinh>(gender);
	            }
	        });
	        
	        comboGioiTinh.setItems(genderList);
	        comboGioiTinh.getSelectionModel().select(1);
	        //set textfirld
	        colNgaySinh.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colCMND.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colQueQuan.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	       colDanToc.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colHoTen.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colTonGiao.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colNgheNghiep.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	       colNoiLamViec.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colQuanHe.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colQuocTich.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        
	        colNoiOTruoc.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colTenGoiKhac.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	       colMaNhanKhau.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colMaHoKhau.setCellFactory(TextFieldTableCell.<NhanKhau>forTableColumn());
	        colDeath.setCellFactory(new Callback<TableColumn<NhanKhau, Boolean>,TableCell<NhanKhau, Boolean>>() {
	        	           @Override
	        	           public TableCell<NhanKhau, Boolean> call(TableColumn<NhanKhau, Boolean> p) {
	        	               CheckBoxTableCell<NhanKhau, Boolean> cell = new CheckBoxTableCell<NhanKhau, Boolean>();
	        	               cell.setAlignment(Pos.CENTER);
	        	               return cell;
	        	           }
	        	       });
	        //edit table column
	        colGioiTinh.setCellFactory(ComboBoxTableCell.forTableColumn(genderList));
	        
	        colGioiTinh.setOnEditCommit((CellEditEvent<NhanKhau, GioiTinh> event) -> {
	            TablePosition<NhanKhau, GioiTinh> pos = event.getTablePosition();
	  
	            GioiTinh newGender = event.getNewValue();
	  
	            int row = pos.getRow();
	            NhanKhau person = event.getTableView().getItems().get(row);
	  
	            person.setGioiTinh(newGender.getCode());
	            
	        });
	        
	        colHoTen.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String ten=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setHoTen(ten);
	        });
	        colNgaySinh.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String ngaysinh=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setNgaySinh(ngaysinh);
	        	
	        });
	        colCMND.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String cmnd=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setCmnd(cmnd);
	        	
	        });
	        colQueQuan.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String quequan=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setQueQuan(quequan);
	        	
	        });
	        colDanToc.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setDanToc(a);
	        });
	        colTonGiao.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setTonGiao(a);
	        });
	        colNgheNghiep.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setNgheNghiep(a);
	        });
	        colNoiLamViec.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setNoiLamViec(a);
	        });
	        colQuanHe.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setQuanHe(a);
	        });
	        colQuocTich.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setQuocTich(a);
	        });
	        
	        colMaNhanKhau.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setMaNhanKhau(a);
	        });
	        colMaHoKhau.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setMaHoKhau(a);
	        });
	        colNoiOTruoc.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setNoiThuongTruTruocKhiChuyenDen(a);
	        });
	        colTenGoiKhac.setOnEditCommit((CellEditEvent<NhanKhau,String> event) -> {
	        	TablePosition<NhanKhau, String> pos=event.getTablePosition();
	        	String a=event.getNewValue();
	        	int row=pos.getRow();
	        	NhanKhau p=event.getTableView().getItems().get(row);
	        	p.setTenGoiKhac(a);
	        });
	        
	        
	        
	   }
	    public void addArrayList(ActionEvent event) {
	       
	    	System.out.print("abc");
	    	
	           
            NhanKhau p = new NhanKhau(tfMaHoKhau.getText(), tfHoTen.getText(), tfNgaySinh.getText(), tfGioiTinh.getText(),tfMaHoKhau.getText());
            	p.setMaHoKhau(tfMaHoKhau.getText());
	            p.setHoTen(tfHoTen.getText());
	            p.setCmnd(tfCMND.getText());
	            p.setDanToc(tfDanToc.getText());
	            p.setNgaySinh(tfNgaySinh.getText());
	            p.setQueQuan(tfQueQuan.getText());
	            p.setTonGiao(tfTonGiao.getText());
	            p.setNgheNghiep(tfNgheNghiep.getText());
	            p.setNoiLamViec(tfNoiLamViec.getText());
	            p.setQuanHe(tfQuanHe.getText());
	            p.setQuocTich(tfQuocTich.getText());
	            String gioitinh=comboGioiTinh.getSelectionModel().getSelectedItem().toString();
	            p.setGioiTinh(gioitinh);
	           // p.setGioiTinh(tfGioiTinh.getText());
	            System.out.print(gioitinh);
	            p.setNoiThuongTruTruocKhiChuyenDen(tfNoiOTruoc.getText());
	            p.setTenGoiKhac(tfTenGoiKhac.getText());
	            
	            p.setMaNhanKhau(tfMaNhanKhau.getText());
	            
	            Main.nhanKhauArrayList.add(p);
	           tfHoTen.clear();
	           tfCMND.clear();
	           tfDanToc.clear();
	           tfNgaySinh.clear();
	           tfNgheNghiep.clear();
	           tfTonGiao.clear();
	           tfNoiLamViec.clear();
	           tfQuanHe.clear();
	           tfQueQuan.clear();
	           tfQuocTich.clear();
	           
	           tfTenGoiKhac.clear();
	           tfMaHoKhau.clear();
	           tfMaNhanKhau.clear();
	           tfNoiOTruoc.clear();
	           
	           nhankhauObservableList = FXCollections.observableList(Main.nhanKhauArrayList);
	            tableNhanKhau.setItems(nhankhauObservableList);
	            for (NhanKhau c : Main.nhanKhauArrayList) {
		            System.out.println(c.toString());
		        }
	           
	  }
	    public void buttonReMove(ActionEvent event) {
	    	ObservableList<NhanKhau> all,single;
	    	all=tableNhanKhau.getItems();
	    	single=tableNhanKhau.getSelectionModel().getSelectedItems();
	    	single.forEach(all::remove);
	    	for (NhanKhau p : Main.nhanKhauArrayList) {
	            System.out.println(p.toString());
	        }
	    }
}
