/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author acer
 */
public class GioHang {
    private String maSPCT;
    private String tenSP;
    private String tenKD;
    private String tenLMK;
    private String TenMMK;
    private String tenCLMK;
    private String tenCLG;
    private String tenMG;
    private String tenKC;
    private String tenTH;
    private double donGia;
    private int soLuong;

    public GioHang() {
    }

    public GioHang(String maSPCT, String tenSP, String tenKD, String tenLMK, String TenMMK, String tenCLMK, String tenCLG, String tenMG, String tenKC, String tenTH, double donGia, int soLuong) {
        this.maSPCT = maSPCT;
        this.tenSP = tenSP;
        this.tenKD = tenKD;
        this.tenLMK = tenLMK;
        this.TenMMK = TenMMK;
        this.tenCLMK = tenCLMK;
        this.tenCLG = tenCLG;
        this.tenMG = tenMG;
        this.tenKC = tenKC;
        this.tenTH = tenTH;
        this.donGia = donGia;
        this.soLuong = soLuong;
    }

    

    public String getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(String maSPCT) {
        this.maSPCT = maSPCT;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getTenKD() {
        return tenKD;
    }

    public void setTenKD(String tenKD) {
        this.tenKD = tenKD;
    }

    public String getTenLMK() {
        return tenLMK;
    }

    public void setTenLMK(String tenLMK) {
        this.tenLMK = tenLMK;
    }

    public String getTenMMK() {
        return TenMMK;
    }

    public void setTenMMK(String TenMMK) {
        this.TenMMK = TenMMK;
    }

    public String getTenCLMK() {
        return tenCLMK;
    }

    public void setTenCLMK(String tenCLMK) {
        this.tenCLMK = tenCLMK;
    }

    public String getTenCLG() {
        return tenCLG;
    }

    public void setTenCLG(String tenCLG) {
        this.tenCLG = tenCLG;
    }

    public String getTenMG() {
        return tenMG;
    }

    public void setTenMG(String tenMG) {
        this.tenMG = tenMG;
    }

    
    public String getTenKC() {
        return tenKC;
    }

    public void setTenKC(String tenKC) {
        this.tenKC = tenKC;
    }

    public String getTenTH() {
        return tenTH;
    }

    public void setTenTH(String tenTH) {
        this.tenTH = tenTH;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    public Object[] toGioHang(){
        return new Object[]{this.getMaSPCT(),this.getTenSP(),this.getTenKD(),this.getTenLMK(),this.getTenMMK(),this.getTenCLMK(),this.getTenCLG(),this.getTenMG(),this.getTenKC(),this.getTenTH(),this.getSoLuong(),this.getDonGia()};
    }
   
    
}
