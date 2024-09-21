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
    private int stt;
    private String maHD;
    private String maNV;
    private String tenCV;
    private String thoiGian;

    public Model_LichSuHoaDon() {
    }

    public Model_LichSuHoaDon(int maLSHD, int stt, String maHD, String maNV, String tenCV, String thoiGian) {
        this.maLSHD = maLSHD;
        this.stt = stt;
        this.maHD = maHD;
        this.maNV = maNV;
        this.tenCV = tenCV;
        this.thoiGian = thoiGian;
    }

    public Model_LichSuHoaDon(int maLSHD, int stt, String maNV, String tenCV, String thoiGian) {
        this.maLSHD = maLSHD;
        this.stt = stt;
        this.maNV = maNV;
        this.tenCV = tenCV;
        this.thoiGian = thoiGian;
    }

    public Model_LichSuHoaDon(int stt, String maNV, String tenCV, String thoiGian) {
        this.stt = stt;
        this.maNV = maNV;
        this.tenCV = tenCV;
        this.thoiGian = thoiGian;
    }
    
    
    public int getMaLSHD() {
        return maLSHD;
    }

    public void setMaLSHD(int maLSHD) {
        this.maLSHD = maLSHD;
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

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getTenCV() {
        return tenCV;
    }

    public void setTenCV(String tenCV) {
        this.tenCV = tenCV;
    }

    public String getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(String thoiGian) {
        this.thoiGian = thoiGian;
    }

    

    
    

    
    public Object[] toTable(){
        return new Object[]{this.getStt(),this.getMaNV(),this.getTenCV(),this.getThoiGian()};
    }
}
