package controller;

import model.NhanKhau;
import model.SoHoKhau;

import javax.xml.parsers.DocumentBuilder;
import java.sql.*;
import java.util.Collection;

public class ConnectSQLServer {
    private static String DB_URL = "jdbc:sqlserver://localhost:1433;"
            +"databaseName=QLNK;"
            +"integratedSecurity=true";
    private static String USER_NAME = "sa";
    private static String PASSWORD = "123456";
    private static String QUERYHoKhau = "select R1.MaHoKhau, R2.HoTen, R2.MaNhanKhau, R1.NoiThuongTru, R1.SoNhanKhau from SoHoKhau R1 left join NhanKhau R2 on R1.MaChuHo=R2.MaNhanKhau";
    private static String QUERYNhanKhau = "select * from NhanKhau";


    public void pullData(){
        Main.soHoKhauArrayList.removeAll(Main.soHoKhauArrayList);
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            Statement stm = cnt.createStatement();
            ResultSet rs = stm.executeQuery(QUERYHoKhau);
            while(rs.next()){
                Main.soHoKhauArrayList.add(new SoHoKhau(rs.getString("MaHoKhau"), rs.getString("HoTen"), rs.getString("MaNhanKhau"), rs.getString("NoiThuongTru"), rs.getInt("SoNhanKhau")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public Connection getConnect(String dbURL, String userName, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DB_URL, userName, password);
            System.out.println("Connect database successful");
            return connection;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public void pullDataNhanKhau(){
        Main.nhanKhauArrayList.removeAll(Main.nhanKhauArrayList);
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            Statement stm = cnt.createStatement();
            ResultSet rs = stm.executeQuery(QUERYNhanKhau);
            while(rs.next()){
                Main.nhanKhauArrayList.add(new NhanKhau(rs.getString("MaNhanKhau"), rs.getString("HoTen"), rs.getString("NgaySinh"), rs.getString("GioiTinh"), rs.getString("MaHoKhau")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaSoHoKhau(String maSoHoKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("delete from NhanKhau where MaHoKhau = " + maSoHoKhau + "delete from SoHoKhau where MaHoKhau = " + maSoHoKhau);
            System.out.println("delete complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void xoaNhanKhau(String maNhanKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("delete from NhanKhau where MaNhanKhau = " + maNhanKhau);
            System.out.println("delete NhanKhau complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void themSoHoKhau(String diaChi){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("insert SoHoKhau (NoiThuongTru) values (N'"+diaChi+"')" );
            System.out.println("insert SHK complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void themNhanKhau(String hoTen, String maHoKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("insert NhanKhau (MaHoKhau, HoTen) values ("+maHoKhau+",N'"+hoTen+"')" );
            System.out.println("insert NK complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setChuHo(String maHoKhau, String maNhanKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update SoHoKhau set MaChuHo="+maNhanKhau+"where MaHoKhau="+maHoKhau);
            System.out.println("set ChuHo complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateThongTinNhanKhau(String maNhanKhau, String quanHe, String hoTen, String ngaySinh, String gioiTinh, String tenGoiKhac, String queQuan, String danToc, String quocTich, String tonGiao, String ngheNghiep, String noiLamViec, String noiThuongTruTruoc){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update SoHoKhau set QuanHe=N'"+quanHe+"', HoTen=N'"+hoTen+"', NgaySinh="+ngaySinh+", GioiTinh=N'"+gioiTinh+"', TenGoiKhac=N'"+tenGoiKhac+"', QueQuan=N'"+queQuan+"', DanToc=N'"+danToc+"', QuocTich=N'"+quocTich+"', TonGiao=N'"+tonGiao+"', NgheNghiep=N'"+ngheNghiep+"', NoiLamViec=N'"+noiLamViec+"', NoiThuongTruTruocKhiChuyenDen=N'"+noiThuongTruTruoc+"' where MaNhanKhau="+maNhanKhau);
            System.out.println("update NhanKhau complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
