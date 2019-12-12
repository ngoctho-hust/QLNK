package model;

import controller.Main;
import model.NgayThangNam;
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
    private static String QUERYHoKhau = "select R1.MaHoKhau, R2.HoTen, R2.MaNhanKhau as MaNhanKhau, R1.NoiThuongTru, R1.SoNhanKhau from SoHoKhau R1 left join NhanKhau R2 on R1.MaChuHo=R2.MaNhanKhau";
    private static String QUERYNhanKhau = "select * from NhanKhau";


    public static boolean existSHK(String maHoKhau){
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            ResultSet rs = cnt.createStatement().executeQuery("select * from SoHoKhau where MaHoKhau="+maHoKhau);
            rs.next();
            if (!rs.getString("MaHoKhau").equals("")) return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public static boolean executeQuery(String query){
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            cnt.createStatement().execute(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void pullData(){
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

    public static void pullDataNhanKhauTrongHo(String maHoKhau){
        Main.nhanKhauTrongHo.removeAll(Main.nhanKhauTrongHo);
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            Statement stm = cnt.createStatement();
            ResultSet rs = stm.executeQuery("select * from NhanKhau where MaHoKhau = "+maHoKhau);
            while(rs.next()){
                Main.nhanKhauTrongHo.add(new NhanKhau(rs.getString("QuanHe"), rs.getString("MaNhanKhau"), rs.getString("HoTen"), rs.getString("NgaySinh"), rs.getString("GioiTinh"), rs.getString("MaHoKhau"), rs.getString("TenGoiKhac"), rs.getString("QueQuan"), rs.getString("DanToc"), rs.getString("QuocTich"), rs.getString("NgheNghiep"),rs.getString("NoiLamViec"), rs.getString("NoiThuongTruTruocKhiChuyenDen"), rs.getString("MaNhanKhau"), rs.getString("TonGiao")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnect(String dbURL, String userName, String password){
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
    public static void pullDataNhanKhau(){
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

    public static void xoaSoHoKhau(String maSoHoKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("delete from NhanKhau where MaHoKhau = " + maSoHoKhau + "delete from SoHoKhau where MaHoKhau = " + maSoHoKhau);
            System.out.println("delete complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void xoaNhanKhau(String maNhanKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("delete from NhanKhau where MaNhanKhau = " + maNhanKhau);
            System.out.println("delete NhanKhau complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String themSoHoKhau(String diaChi){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            ResultSet rs = cnt.createStatement().executeQuery("insert into SoHoKhau (NoiThuongTru) values (N'"+diaChi+"'); select SCOPE_IDENTITY() as ID;");
            rs.next();
            System.out.println("insert SHK complete!");
            return rs.getString("ID");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "-1";
    }

    public static boolean themNK(String maHoKhau, String quanHe, String hoTen, String ngaySinh, String gioiTinh, String tenGoiKhac, String queQuan, String danToc, String quocTich, String tonGiao, String ngheNghiep, String noiLamViec, String noiThuongTruTruoc){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("insert into NhanKhau (MaHoKhau, QuanHe, HoTen, NgaySinh, GioiTinh, TenGoiKhac, QueQuan, DanToc, QuocTich, NgheNghiep, NoiLamViec, NoiThuongTruTruocKhiChuyenDen, TonGiao) "+
                    "values (N'"+maHoKhau+"', N'"+quanHe+"', N'"+hoTen+"', '"+ngaySinh+"', N'"+gioiTinh+"', N'"+tenGoiKhac+"', N'"+queQuan+"', N'"+danToc+"', N'"+quocTich+"', N'"+ngheNghiep+"', N'"+noiLamViec+"', N'"+noiThuongTruTruoc+"', N'"+tonGiao+"')");
            System.out.println("them NhanKhau complete!");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void setChuHo(String maHoKhau, String maNhanKhau){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update SoHoKhau set MaChuHo="+maNhanKhau+"where MaHoKhau="+maHoKhau);
            System.out.println("set ChuHo complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean updateThongTinNhanKhau(String maNhanKhau, String quanHe, String hoTen, String ngaySinh, String gioiTinh, String tenGoiKhac, String queQuan, String danToc, String quocTich, String tonGiao, String ngheNghiep, String noiLamViec, String noiThuongTruTruoc){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update NhanKhau set QuanHe=N'"+quanHe+"', HoTen=N'"+hoTen+"', NgaySinh='"+ngaySinh+"', GioiTinh=N'"+gioiTinh+"', TenGoiKhac=N'"+tenGoiKhac+"', QueQuan=N'"+queQuan+"', DanToc=N'"+danToc+"', QuocTich=N'"+quocTich+"', TonGiao=N'"+tonGiao+"', NgheNghiep=N'"+ngheNghiep+"', NoiLamViec=N'"+noiLamViec+"', NoiThuongTruTruocKhiChuyenDen=N'"+noiThuongTruTruoc+"' where MaNhanKhau="+maNhanKhau);
            System.out.println("update NhanKhau complete!");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public static void updateDiaChi(String maHoKhau, String noiThuongTru){
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update SoHoKhau set NoiThuongTru= N'"+noiThuongTru+"' where MaHoKhau="+maHoKhau);
            System.out.println("update diachi complete!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static NgayThangNam getNTN(String maNhanKhau){
        try {
            Connection cnt=getConnect(DB_URL, USER_NAME, PASSWORD);
            Statement stm = cnt.createStatement();
            ResultSet rs = stm.executeQuery("select FORMAT(NgaySinh, 'yyyy') as nam, FORMAT(NgaySinh, 'MM') as thang, FORMAT(NgaySinh, 'dd') as ngay from NhanKhau where MaNhanKhau="+maNhanKhau);
            while(rs.next()){
                return new NgayThangNam(rs.getString("ngay"), rs.getString("thang"), rs.getString("nam"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean chuyenHo(String maNhanKhau, String maHoKhau) {
        Connection cnt = getConnect(DB_URL, USER_NAME, PASSWORD);
        try {
            cnt.createStatement().executeUpdate("update NhanKhau set MaHoKhau= "+maHoKhau+" where MaNhanKhau="+maNhanKhau+"; update SoHoKhau set SoNhanKhau = (select COUNT(*) from NhanKhau where NhanKhau.MaHoKhau= SoHoKhau.MaHoKhau)");
            System.out.println("ChuyenHo complete!");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}