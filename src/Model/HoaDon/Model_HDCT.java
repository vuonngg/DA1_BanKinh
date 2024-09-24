/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model.HoaDon;

/**
 *
 * @author acer
 */
public class Model_HDCT {
    private int stt;
    private String tenSP;
    private String thuongHieu;
    private String kieuDang;
    private String loaiMatKinh;
    private String mauMatKinh;
    private String chatLieuMatKinh;
    private String chatLieuGong;
    private String mauGong;
    private String kichCo;
    private int soLuong;
    private String donGia;
    private String thanhTien;

    public Model_HDCT() {
    }

    public Model_HDCT(int STT, String tenSP, String thuongHieu, String kieuDang, String loaiMatKinh, String mauMatKinh, String chatLieuMatKinh, String chatLieuGong, String mauGong, String kichCo, int soLuong, String donGia, String thanhTien) {
        this.stt = STT;
        this.tenSP = tenSP;
        this.thuongHieu = thuongHieu;
        this.kieuDang = kieuDang;
        this.loaiMatKinh = loaiMatKinh;
        this.mauMatKinh = mauMatKinh;
        this.chatLieuMatKinh = chatLieuMatKinh;
        this.chatLieuGong = chatLieuGong;
        this.mauGong = mauGong;
        this.kichCo = kichCo;
        this.soLuong = soLuong;
        this.donGia = donGia;
        this.thanhTien = thanhTien;
    }

    public int getStt() {
        return stt;
    }

    public void setStt(int stt) {
        this.stt = stt;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public String getKieuDang() {
        return kieuDang;
    }

    public void setKieuDang(String kieuDang) {
        this.kieuDang = kieuDang;
    }

    public String getLoaiMatKinh() {
        return loaiMatKinh;
    }

    public void setLoaiMatKinh(String loaiMatKinh) {
        this.loaiMatKinh = loaiMatKinh;
    }

    public String getMauMatKinh() {
        return mauMatKinh;
    }

    public void setMauMatKinh(String mauMatKinh) {
        this.mauMatKinh = mauMatKinh;
    }

    public String getChatLieuMatKinh() {
        return chatLieuMatKinh;
    }

    public void setChatLieuMatKinh(String chatLieuMatKinh) {
        this.chatLieuMatKinh = chatLieuMatKinh;
    }

    public String getChatLieuGong() {
        return chatLieuGong;
    }

    public void setChatLieuGong(String chatLieuGong) {
        this.chatLieuGong = chatLieuGong;
    }

    public String getMauGong() {
        return mauGong;
    }

    public void setMauGong(String mauGong) {
        this.mauGong = mauGong;
    }

    public String getKichCo() {
        return kichCo;
    }

    public void setKichCo(String kichCo) {
        this.kichCo = kichCo;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public String getDonGia() {
        return donGia;
    }

    public void setDonGia(String donGia) {
        this.donGia = donGia;
    }

    public String getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(String thanhTien) {
        this.thanhTien = thanhTien;
    }


    public Object[] toHDCT() {
        return new Object[]{this.getStt(),this.getTenSP(),this.getThuongHieu(),this.getKieuDang(),this.getLoaiMatKinh(),this.getMauMatKinh(),this.getChatLieuMatKinh(),this.getChatLieuGong(),this.getMauGong(),this.getKichCo(),this.getSoLuong(),this.getDonGia(),this.getTenSP()};
    }
}
