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
    private int stt;
    private String maHD;
    private String maKH;
    private String maPGG;
    private String tenHTTT;
    private String tenTT;
    private String ngayTao;
    private double giaBanDau;
    private double giaGiam;
    private double giaCuoiCung;
    private String tenKhachHang;
    private String sdt;
    private String diaChi;

    public Model_HoaDon() {
    }

    public Model_HoaDon(String tenTT) {
        this.tenTT = tenTT;
    }

    public Model_HoaDon(int stt, String maHD, String maKH, String maPGG, String tenHTTT, String tenTT, String ngayTao, double giaBanDau, double giaGiam, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi) {
        this.stt = stt;
        this.maHD = maHD;
        this.maKH = maKH;
        this.maPGG = maPGG;
        this.tenHTTT = tenHTTT;
        this.tenTT = tenTT;
        this.ngayTao = ngayTao;
        this.giaBanDau = giaBanDau;
        this.giaGiam = giaGiam;
        this.giaCuoiCung = giaCuoiCung;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }
    
    
    

    public Model_HoaDon(String maKH, String maPGG, String tenHTTT, String tenTT, String ngayTao, double giaBanDau, double giaGiam, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi) {
        this.maKH = maKH;
        this.maPGG = maPGG;
        this.tenHTTT = tenHTTT;
        this.tenTT = tenTT;
        this.ngayTao = ngayTao;
        this.giaBanDau = giaBanDau;
        this.giaGiam = giaGiam;
        this.giaCuoiCung = giaCuoiCung;
        this.tenKhachHang = tenKhachHang;
        this.sdt = sdt;
        this.diaChi = diaChi;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }
    
    

    public String getMaPGG() {
        return maPGG;
    }

    public void setMaPGG(String maPGG) {
        this.maPGG = maPGG;
    }

    public String getTenHTTT() {
        return tenHTTT;
    }

    public void setTenHTTT(String tenHTTT) {
        this.tenHTTT = tenHTTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
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

    public Object[] toTable(){
        return new Object[]{this.getStt(),this.getMaHD(),this.getMaKH(),this.getMaPGG(),this.getTenHTTT(),this.getTenTT(),this.getNgayTao(),this.getGiaBanDau(),this.getGiaGiam(),this.getGiaCuoiCung(),this.getTenKhachHang(),this.getSdt(),this.getDiaChi()};
    }
}
