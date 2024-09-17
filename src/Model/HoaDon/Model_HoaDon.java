/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

/**
 *
 * @author acer
 */
public class Model_HoaDon {
    private String maHD;
    private String maPGG;
    private String maHTTT;
    private String MaTT;
    private String ngayTao;
    private double giaBanDau;
    private double giaGiam;
    private double giaCuoiCung;
    private String tenKhachHang;
    private String sdt;
    private String diaChi;

    public Model_HoaDon() {
    }

    public Model_HoaDon(String maHD, String maPGG, String maHTTT, String MaTT, String ngayTao, double giaBanDau, double giaGiam, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi) {
        this.maHD = maHD;
        this.maPGG = maPGG;
        this.maHTTT = maHTTT;
        this.MaTT = MaTT;
        this.ngayTao = ngayTao;
        this.giaBanDau = giaBanDau;
        this.giaGiam = giaGiam;
        this.giaCuoiCung = giaCuoiCung;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public Model_HoaDon(String maPGG, String maHTTT, String MaTT, String ngayTao, double giaBanDau, double giaGiam, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi) {
        this.maPGG = maPGG;
        this.maHTTT = maHTTT;
        this.MaTT = MaTT;
        this.ngayTao = ngayTao;
        this.giaBanDau = giaBanDau;
        this.giaGiam = giaGiam;
        this.giaCuoiCung = giaCuoiCung;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaPGG() {
        return maPGG;
    }

    public void setMaPGG(String maPGG) {
        this.maPGG = maPGG;
    }

    public String getMaHTTT() {
        return maHTTT;
    }

    public void setMaHTTT(String maHTTT) {
        this.maHTTT = maHTTT;
    }

    public String getMaTT() {
        return MaTT;
    }

    public void setMaTT(String MaTT) {
        this.MaTT = MaTT;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }

    public double getGiaBanDau() {
        return giaBanDau;
    }

    public void setGiaBanDau(double giaBanDau) {
        this.giaBanDau = giaBanDau;
    }

    public double getGiaGiam() {
        return giaGiam;
    }

    public void setGiaGiam(double giaGiam) {
        this.giaGiam = giaGiam;
    }

    public double getGiaCuoiCung() {
        return giaCuoiCung;
    }

    public void setGiaCuoiCung(double giaCuoiCung) {
        this.giaCuoiCung = giaCuoiCung;
    }

    public String getTenKhachHang() {
        return tenKhachHang;
    }

    public void setTenKhachHang(String tenKhachHang) {
        this.tenKhachHang = tenKhachHang;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }
    
    
}
