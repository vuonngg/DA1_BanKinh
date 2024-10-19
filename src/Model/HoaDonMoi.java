/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class HoaDonMoi {
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
    

    public HoaDonMoi() {
    }

    public HoaDonMoi(int stt, String maHD, String maKH, String maPGG, String tenHTTT, String tenTT, String ngayTao, double giaBanDau, double giaGiam, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi, String maNV) {
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

    public HoaDonMoi(int stt, String maHD, String maKH, String tenTT, String ngayTao, double giaCuoiCung, String tenKhachHang, String sdt, String diaChi) {
        this.stt = stt;
        this.maHD = maHD;
        this.maKH = maKH;
        this.tenTT = tenTT;
        this.ngayTao = ngayTao;
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

    

    
    public Object[] toHDM(){
        return new Object[]{this.getStt(),this.getMaHD(),this.getNgayTao(),this.getMaKH(),this.getTenKhachHang(),this.getSdt(),this.getDiaChi(),this.getGiaCuoiCung(),this.getTenTT()};
    }
}
