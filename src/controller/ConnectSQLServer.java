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
    private static String QUERYHoKhau = "select R2.MaHoKhau, R1.HoTen, R1.MaNhanKhau, R2.NoiThuongTru, R2.SoNhanKhau from NhanKhau R1, SoHoKhau R2 where R1.MaNhanKhau = R2.MaChuHo";
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
    public Connection getConnect(String dbURL, String userNaem, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            Connection connection = DriverManager.getConnection(DB_URL, userNaem, password);
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
}
