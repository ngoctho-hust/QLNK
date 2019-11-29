package model;

public class NhanKhau {
    private String maNhanKhau;
    private String hoTen;
    private String ngaySinh;
    private String gioiTinh;
    private String maHoKhau;
    private String tenGoiKhac;
    private String queQuan;
    private String danToc;
    private String quocTich;
    private String ngheNghiep;
    private String noiLamViec;
    private String noiThuongTruTruocKhiChuyenDen;

    public NhanKhau(String maNhanKhau, String hoTen, String ngaySinh, String gioiTinh, String maHoKhau) {
        this.maNhanKhau = maNhanKhau;
        this.hoTen = hoTen;
        this.ngaySinh = ngaySinh;
        this.gioiTinh = gioiTinh;
        this.maHoKhau = maHoKhau;
    }

    public String getMaNhanKhau() {
        return maNhanKhau;
    }

    public void setMaNhanKhau(String maNhanKhau) {
        this.maNhanKhau = maNhanKhau;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(String maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getTenGoiKhac() {
        return tenGoiKhac;
    }

    public void setTenGoiKhac(String tenGoiKhac) {
        this.tenGoiKhac = tenGoiKhac;
    }

    public String getQueQuan() {
        return queQuan;
    }

    public void setQueQuan(String queQuan) {
        this.queQuan = queQuan;
    }

    public String getDanToc() {
        return danToc;
    }

    public void setDanToc(String danToc) {
        this.danToc = danToc;
    }

    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    public String getNgheNghiep() {
        return ngheNghiep;
    }

    public void setNgheNghiep(String ngheNghiep) {
        this.ngheNghiep = ngheNghiep;
    }

    public String getNoiLamViec() {
        return noiLamViec;
    }

    public void setNoiLamViec(String noiLamViec) {
        this.noiLamViec = noiLamViec;
    }

    public String getNoiThuongTruTruocKhiChuyenDen() {
        return noiThuongTruTruocKhiChuyenDen;
    }

    public void setNoiThuongTruTruocKhiChuyenDen(String noiThuongTruTruocKhiChuyenDen) {
        this.noiThuongTruTruocKhiChuyenDen = noiThuongTruTruocKhiChuyenDen;
    }
}
