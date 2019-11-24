package model;

public class SoHoKhau {
    private int maHoKhau;
    private String tenChuHo;
    private String CCCD;
    private String diaChi;
    private int soNhanKhau;

    public SoHoKhau(int maHoKhau, String tenChuHo, String CCCD, String diaChi, int soNhanKhau) {
        this.maHoKhau = maHoKhau;
        this.tenChuHo = tenChuHo;
        this.CCCD = CCCD;
        this.diaChi = diaChi;
        this.soNhanKhau = soNhanKhau;
    }

    public int getMaHoKhau() {
        return maHoKhau;
    }

    public void setMaHoKhau(int maHoKhau) {
        this.maHoKhau = maHoKhau;
    }

    public String getTenChuHo() {
        return tenChuHo;
    }

    public void setTenChuHo(String tenChuHo) {
        this.tenChuHo = tenChuHo;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public int getSoNhanKhau() {
        return soNhanKhau;
    }

    public void setSoNhanKhau(int soNhanKhau) {
        this.soNhanKhau = soNhanKhau;
    }
}
