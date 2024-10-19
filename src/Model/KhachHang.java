package Model;

import DBConnect.*;
import java.time.LocalDate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author admin
 */
public class KhachHang {
    private int STT;
    private String maKH;
    private String tenKH;
    private String diaChi;
    private String sdt;
    private String Email;
    private String ngayTao;
    
    public KhachHang() {
    }

    public KhachHang(String tenKH, String sdt) {
        this.tenKH = tenKH;
        this.sdt = sdt;
    }
    
    public KhachHang(String tenKH, String diaChi, String sdt, String Email, String ngayTao) {
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.Email = Email;
        this.ngayTao = ngayTao;
    }

    public KhachHang(String maKH, String tenKH, String diaChi, String sdt) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
    }

    
    
    public KhachHang(String maKH, String tenKH, String diaChi, String sdt, String Email, String ngayTao) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.Email = Email;
        this.ngayTao=ngayTao;
    }
    
    public KhachHang(int STT, String maKH, String tenKH, String diaChi, String sdt, String Email, String ngayTao) {
        this.STT = STT;
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.diaChi = diaChi;
        this.sdt = sdt;
        this.Email = Email;
        this.ngayTao = ngayTao;
    }
    

    public int getSTT() {
        return STT;
    }

    public void setSTT(int STT) {
        this.STT = STT;
    }
    
    public String getMaKH() {
        return maKH;
    }

    public void setMaKH(String maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
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
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(String ngayTao) {
        this.ngayTao = ngayTao;
    }
    

    
    public Object[] toDataRow() {
        return new Object[]{this.getSTT(),this.getMaKH(), this.getTenKH(), this.getDiaChi(), this.getSdt(), this.getEmail(),this.getNgayTao()};
    }
    public Object[] toTable() {
        return new Object[]{this.getMaKH(),this.getTenKH(),this.getSdt(),this.getDiaChi()};
    }
}
