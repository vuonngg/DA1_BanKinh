package Model.NhanVien;

import DBConnect.*;



/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class NhanVien {
    private int STT;
    private String maNV;
    private String hoTen;
    private boolean gioiTinh;
    private String diaChi;
    private String sdt;
    private String email;
    private String CCCD;
    private boolean vaiTro;

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public String getSdt() {
        return sdt;
    }

    public void setSdt(String sdt) {
        this.sdt = sdt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }    

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }
    

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTen, boolean gioiTinh, String diaChi, String sdt, String email, String CCCD, boolean vaiTro) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.CCCD = CCCD;
        this.vaiTro = vaiTro;
    }   

    public NhanVien(String hoTen, boolean gioiTinh, String diaChi, String sdt, String email, String CCCD, boolean vaiTro) {
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.CCCD = CCCD;
        this.vaiTro = vaiTro;
    }

    public NhanVien(String maNV, String hoTen) {
        this.maNV = maNV;
        this.hoTen = hoTen;
    }       

    public NhanVien(String maNV, String hoTen, boolean gioiTinh, String diaChi, String sdt, String email, boolean vaiTro) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.gioiTinh = gioiTinh;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.email = email;
        this.vaiTro = vaiTro;
    }
       
    public Object[] toDataRow(){
        return new Object[]{this.getSTT(),this.getMaNV(),this.getHoTen(),this.isGioiTinh()?"Nam":"Nữ",this.getDiaChi(),this.getSdt(),this.getEmail(),this.isVaiTro()?"Quản lý":"Nhân viên"};
    }
}
