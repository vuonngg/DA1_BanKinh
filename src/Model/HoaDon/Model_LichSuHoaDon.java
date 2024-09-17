/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

/**
 *
 * @author acer
 */
public class Model_LichSuHoaDon {
    private int maLSHD;
    private String maHD;
    private String maNV;
    private String maCV;
    private String thoiGian;

    public Model_LichSuHoaDon() {
    }

    public Model_LichSuHoaDon(int maLSHD, String maHD, String maNV, String maCV, String thoiGian) {
        this.maLSHD = maLSHD;
        this.maHD = maHD;
        this.maNV = maNV;
        this.maCV = maCV;
        this.thoiGian = thoiGian;
    }

    public Model_LichSuHoaDon(String maHD, String maNV, String maCV, String thoiGian) {
        this.maHD = maHD;
        this.maNV = maNV;
        this.maCV = maCV;
        this.thoiGian = thoiGian;
    }

    public int getMaLSHD() {
        return maLSHD;
    }

    public void setMaLSHD(int maLSHD) {
        this.maLSHD = maLSHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getMaCV() {
        return maCV;
    }

    public void setMaCV(String maCV) {
        this.maCV = maCV;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }
    
    
}
