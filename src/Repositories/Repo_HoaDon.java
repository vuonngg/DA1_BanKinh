/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.sql.*;
import java.util.ArrayList;
import Model.HoaDon.*;
import DBConnect.*;
/**
 *
 * @author acer
 */
public class Repo_HoaDon {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql = "";

    public ArrayList<Model.HoaDon.Model_HoaDon> getHoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                hd = rs.getString(1);
                kh = rs.getString(2);
                pgg = rs.getString(3);
                httt = rs.getString(4);
                tt = rs.getString(5);
                ngay = rs.getString(6);
                giadau = rs.getDouble(7);
                giagiam = rs.getDouble(8);
                giacuoi = rs.getDouble(9);
                ten = rs.getString(10);
                sdt = rs.getString(11);
                dc = rs.getString(12);
                listhd.add(new Model_HoaDon(hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model_LichSuHoaDon> getHDCT() {
        ArrayList<Model_LichSuHoaDon> listlshd = new ArrayList<>();
        sql = "select lshd.MaLSHD, lshd.MaNV,cv.TenCV,lshd.NgayThucHien\n"
                + "from LichSuHoaDon lshd join CongViec cv on lshd.MaCV = cv.MaCV";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                String ma,ten,thoigian;
                int lshd = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                thoigian = rs.getString(4);
                listlshd.add(new Model_LichSuHoaDon(lshd,ma,ten,thoigian));
            }
            return listlshd;
        } catch (Exception e) {
            return null;
        }
    }
}
