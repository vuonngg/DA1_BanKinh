/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

/**
 *
 * @author acer
 */
public class Model_HoaDonChiTiet {
    private int maHDCT;
    private String maHD;
    private int maSPCT;
    private int soLuong;
    private double donGia;
    private double thanhTien;

    public Model_HoaDonChiTiet() {
    }

    public Model_HoaDonChiTiet(int maHDCT, String maHD, int maSPCT, int soLuong, double donGia, double thanhTien) {
        this.maHDCT = maHDCT;
        this.maHD = maHD;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public Model_HoaDonChiTiet(String maHD, int maSPCT, int soLuong, double donGia, double thanhTien) {
        this.maHD = maHD;
        this.maSPCT = maSPCT;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getMaHDCT() {
        return maHDCT;
    }

    public void setMaHDCT(int maHDCT) {
        this.maHDCT = maHDCT;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getMaSPCT() {
        return maSPCT;
    }

    public void setMaSPCT(int maSPCT) {
        this.maSPCT = maSPCT;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public double getDonGia() {
        return donGia;
    }

    public void setDonGia(double donGia) {
        this.donGia = donGia;
    }

    public double getThanhTien() {
        return this.donGia*this.soLuong;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }
    
    
}
