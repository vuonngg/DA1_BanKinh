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
        sql = "select ROW_NUMBER() over(order by MaHD) as STT,hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model_LichSuHoaDon> getLSHD(String hd) {
        ArrayList<Model_LichSuHoaDon> listlshd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, lshd.MaNV,cv.TenCV,lshd.NgayThucHien\n"
                + "from LichSuHoaDon lshd join CongViec cv on lshd.MaCV = cv.MaCV\n"
                + "where lshd.MaHD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, hd);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ma, ten, thoigian;
                int lshd = rs.getInt(1);
                ma = rs.getString(2);
                ten = rs.getString(3);
                thoigian = rs.getString(4);
                listlshd.add(new Model_LichSuHoaDon(lshd, ma, ten, thoigian));
            }
            return listlshd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model_HDCT> getHDCT(String ma) {
        ArrayList<Model_HDCT> listhdct = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHDCT) as STT, sp.TenSP,th.TenTH,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,Kc.TenKC,hdct.SoLuong,hdct.DonGia,hdct.SoLuong * hdct.DonGia as 'Thanh Tien'\n"
                + "from HoaDonChiTiet hdct join SanPhamChiTiet spct on hdct.MaSPCT = spct.MaSPCT\n"
                + "join SanPham sp on sp.MaSP = spct.MaSP\n"
                + "join ThuongHieu th on spct.MaTH = th.MaTH\n"
                + "join KieuDang kd on spct.MaKD = kd.MaKD\n"
                + "join LoaiMatKinh lmk on lmk.MaLMK = spct.MaLMK\n"
                + "join MauMatKinh mmk on mmk.MaMMK = spct.MaMMK\n"
                + "join ChatLieuMatKinh clmk on clmk.MaCLMK = spct.MaCLMK\n"
                + "join ChatLieuGong clg on spct.MaCLG = clg.MaCLG\n"
                + "join MauGong mg on mg.MaMG = spct.MaMG\n"
                + "join KichCo kc on kc.MaKC = spct.MaKC\n"
                + "join HoaDon hd on hd.MaHD = hdct.MaHD\n"
                + "where hdct.MaHD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String tenSP, thuongHieu, kieuDang, loaiMatKinh, mauMatKinh, chatLieuMatKinh, chatLieuGong, mauGong, kichCo;
                int soLuong, stt;
                double donGia, thanhTien;
                stt = rs.getInt(1);
                tenSP = rs.getString(2);
                thuongHieu = rs.getString(3);
                kieuDang = rs.getString(4);
                loaiMatKinh = rs.getString(5);
                mauMatKinh = rs.getString(6);
                chatLieuMatKinh = rs.getString(7);
                chatLieuGong = rs.getString(8);
                mauGong = rs.getString(9);
                kichCo = rs.getString(10);
                soLuong = rs.getInt(11);
                donGia = rs.getDouble(12);
                thanhTien = rs.getDouble(13);
                listhdct.add(new Model_HDCT(stt, tenSP, thuongHieu, kieuDang, loaiMatKinh, mauMatKinh, chatLieuMatKinh, chatLieuGong, mauGong, kichCo, soLuong, donGia, thanhTien));
            }
            return listhdct;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> searchHoaDon(String ma) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.MaHD like ? or hd.TenKhachHang like ? or hd.MaKH like ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            ps.setObject(3, '%' + ma + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> sort1HoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)\n"
                + "order by hd.GiaCuoiCung asc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> sort2HoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)\n"
                + "order by hd.GiaCuoiCung desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> sort3HoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)\n"
                + "order by hd.GiaCuoiCung asc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> sort4HoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)\n"
                + "order by hd.GiaCuoiCung desc";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> locHoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public int suaHoaDon(String ma, Model_HoaDon hd) {
        sql = "update HoaDon \n"
                + "set MaTT = (select MaTT from TrangThai where TenTT = ?) \n"
                + "where MaHD = ?\n"
                + "insert into LichSuHoaDon(MaHD,MaCV,NgayThucHien)\n"
                + "values(?,2,CONVERT(VARCHAR(10), GETDATE(), 111))";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(2, ma);
            ps.setObject(1, hd.getTenTT());
            ps.setObject(3, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
    public ArrayList<Model.HoaDon.Model_HoaDon> qrCode(String ma) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "where hd.MaHD = ?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma );
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giadau, giagiam, giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                tt = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, tt, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }
    
}
