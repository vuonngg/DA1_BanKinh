/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

/**
 *
 * @author acer
 */
public class Model_TrangThai {
    private int maTT;
    private String tenTT;
    private String moTa;

    public Model_TrangThai() {
    }

    public Model_TrangThai(int maTT, String tenTT, String moTa) {
        this.maTT = maTT;
        this.tenTT = tenTT;
        this.moTa = moTa;
    }

    public int getMaTT() {
        return maTT;
    }

    public void setMaTT(int maTT) {
        this.maTT = maTT;
    }

    public String getTenTT() {
        return tenTT;
    }

    public void setTenTT(String tenTT) {
        this.tenTT = tenTT;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    
    
    
}
