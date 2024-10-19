/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.sql.*;
import java.util.ArrayList;
import Model.HoaDon.*;
import DBConnect.*;
import Model.GioHang;
import Model.HoaDonMoi;
import Model.KhachHang;
import Model.PhieuGiamGia.model_PhieuGiamGia;
import Model.SanPhamChiTietJoin;
import Model.ThuongHieu;
import model.ChatLieuGong;
import model.KichCo;

/**
 *
 * @author acer
 */
public class Repo_HoaDon {

    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    private String sql = "";

    public Repo_HoaDon() {
        con = DBConnect.getConnection();
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> getHoaDon() {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = """
              select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
                            from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
                            join TrangThai tt on hd.MaTT = tt.MaTT 
              """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giacuoi;
                double giadau, giagiam;
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
        sql = "select ROW_NUMBER() over(order by MaHDCT) as STT, sp.TenSP,th.TenTH,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,Kc.TenKC,hdct.SoLuong,hdct.DonGia,hdct.SoLuong*hdct.DonGia as ThanhTien\n"
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
                double donGia;
                double thanhTien;
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

    public ArrayList<Model.HoaDon.Model_HoaDon> searchHoaDon(String ma, String d1, String d2, String tt) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = """
              select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi from
              HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
              join TrangThai tt on hd.MaTT = tt.MaTT 
              where (hd.MaHD like ? or hd.MaKH like ? or hd.TenKhachHang like ? or hd.SDT like ?)
              and hd.NgayTao >=? and hd.NgayTao <= ? 
              and tt.TenTT like ?
              order by hd.GiaCuoiCung asc
              """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            ps.setObject(3, '%' + ma + '%');
            ps.setObject(4, '%' + ma + '%');
            ps.setObject(5, d1);
            ps.setObject(6, d2);
            ps.setObject(7, '%' + tt + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, ngay, ten, sdt, dc, trangThai;
                double giadau, giagiam;
                double giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                trangThai = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, trangThai, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
            }
            return listhd;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<Model.HoaDon.Model_HoaDon> sort1HoaDon(String ma, String d1, String d2, String tt) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = """
              select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi from
                            HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
                            join TrangThai tt on hd.MaTT = tt.MaTT 
                            where (hd.MaHD like ? or hd.MaKH like ? or hd.TenKhachHang like ? or hd.SDT like ?)
                            and hd.NgayTao >=? and hd.NgayTao <= ? 
                            and tt.TenTT like ?
                            order by hd.GiaCuoiCung desc
              """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            ps.setObject(3, '%' + ma + '%');
            ps.setObject(4, '%' + ma + '%');
            ps.setObject(5, d1);
            ps.setObject(6, d2);
            ps.setObject(7, '%' + tt + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, ngay, ten, sdt, dc, trangThai;
                double giadau, giagiam;
                double giacuoi;
                int stt = rs.getInt(1);
                hd = rs.getString(2);
                kh = rs.getString(3);
                pgg = rs.getString(4);
                httt = rs.getString(5);
                trangThai = rs.getString(6);
                ngay = rs.getString(7);
                giadau = rs.getDouble(8);
                giagiam = rs.getDouble(9);
                giacuoi = rs.getDouble(10);
                ten = rs.getString(11);
                sdt = rs.getString(12);
                dc = rs.getString(13);
                listhd.add(new Model_HoaDon(stt, hd, kh, pgg, httt, trangThai, ngay, giadau, giagiam, giacuoi, ten, sdt, dc));
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

    public ArrayList<Model_HoaDon> qrCode(String ma) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = """
            select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
            from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
            join TrangThai tt on hd.MaTT = tt.MaTT 
            where hd.MaHD = ?
            """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String hd, kh, pgg, httt, tt, ngay, ten, sdt, dc;
                double giacuoi;
                double giadau, giagiam;
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

    public void taoHoaDon(String manv) {
        sql = """
              DECLARE @InsertedMaHD TABLE (MaHD NVARCHAR(10));
              insert into HoaDon(MaKH,TenKhachHang,MaTT,NgayTao,GiaCuoiCung)
              OUTPUT INSERTED.MaHD INTO @InsertedMaHD
              values('ADA7E5DA14','Vang lai',1,convert(nvarchar(10),getdate(),120),0)
              insert into LichSuHoaDon(MaHD,MaNV,MaCV,NgayThucHien)
              values((SELECT MaHD FROM @InsertedMaHD),?,1,GETDATE())
              """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, manv);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<HoaDonMoi> getHDM() {
        sql = """
              select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.NgayTao,hd.MaKH,hd.TenKhachHang,hd.SDT,hd.DiaChi,tt.TenTT,hd.GiaCuoiCung from HoaDon hd
            join TrangThai tt on hd.MaTT = tt.MaTT
            where hd.NgayTao = convert(nvarchar(10),getdate(),120) and hd.MaTT = 1
              """;
        ArrayList<HoaDonMoi> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int stt;
                String ma, ngay, ten, nv, makh, sdt, dc, tt;
                double gia;
                stt = rs.getInt(1);
                ma = rs.getString(2);
                ngay = rs.getString(3);

                makh = rs.getString(4);
                ten = rs.getString(5);
                sdt = rs.getString(6);
                dc = rs.getString(7);
                tt = rs.getString(8);
                gia = rs.getDouble(9);
                list.add(new HoaDonMoi(stt, ma, makh, tt, ngay, gia, ten, sdt, dc));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public int thanhToan(String mahd, boolean mahttt, String mapgg, double giabd, double giaGiam, double giaCuoi) {
        sql = """
              update HoaDon
              set MaTT = 2, MaHTTT = ?, GiaBanDau = ?, GiaGiam = ?, GiaCuoiCung = ?, MaPGG = ?
              where MaHD = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahttt);
            ps.setObject(2, giabd);
            ps.setObject(3, giaGiam);
            ps.setObject(4, giaCuoi);
            ps.setObject(5, mapgg);
            ps.setObject(6, mahd);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
    
    public int thanhToan2(String mahd, boolean mahttt, double giabd, double giaGiam, double giaCuoi) {
        sql = """
              update HoaDon
              set MaTT = 2, MaHTTT = ?, GiaBanDau = ?, GiaGiam = ?, GiaCuoiCung = ?
              where MaHD = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahttt);
            ps.setObject(2, giabd);
            ps.setObject(3, giaGiam);
            ps.setObject(4, giaCuoi);
            ps.setObject(5, mahd);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }
    public int capNhatKhachHang(String ma, String ma1, String ten, String sdt, String dc) {
        sql = "Update HoaDon\n"
                + "set MaKH = ?, TenKhachHang = ?, SDT = ?,DiaChi = ? \n"
                + "where MaHD = ?";

        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma1);
            ps.setObject(2, ten);
            ps.setObject(3, sdt);
            ps.setObject(4, dc);
            ps.setObject(5, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public ArrayList<GioHang> getGioHang(String ma) {
        ArrayList<GioHang> list = new ArrayList<>();
        sql = """
              select spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,hdct.SoLuong,spct.GiaBan FROM HoaDonChiTiet hdct
              join SanPhamChiTiet spct  on hdct.MaSPCT = spct.MaSPCT
              JOIN SanPham  sp ON spct.MaSP = sp.MaSP
              JOIN KieuDang  kd ON spct.MaKD = kd.MaKD
              JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK
              JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK
              JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK
              JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG
              JOIN MauGong  mg ON spct.MaMG = mg.MaMG
              JOIN KichCo  kc ON spct.MaKC = kc.MaKC
              JOIN ThuongHieu  th ON spct.MaTH = th.MaTH
              where hdct.MaHD = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                String ms, ten, kd, lmk, mmk, clmk, clg, mg, kc, th;
                int sl;
                double gia;
                ms = rs.getString(1);
                ten = rs.getString(2);
                kd = rs.getString(3);
                lmk = rs.getString(4);
                mmk = rs.getString(5);
                clmk = rs.getString(6);
                clg = rs.getString(7);
                mg = rs.getString(8);
                kc = rs.getString(9);
                th = rs.getString(10);
                gia = rs.getDouble(12);
                sl = rs.getInt(11);
                list.add(new GioHang(ms, ten, kd, lmk, mmk, clmk, clg, mg, kc, th, gia, sl));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public double gia(String ma) {
        String sql = "SELECT SUM(DonGia * SoLuong) AS TongGia FROM HoaDonChiTiet WHERE MaHD = ?";
        double tongGia = 0.0;

        try {
            ps = con.prepareStatement(sql);
            ps.setString(1, ma); // Sử dụng setString nếu MaHD là kiểu chuỗi
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    tongGia = rs.getDouble("TongGia");
                    if (rs.wasNull()) {
                        tongGia = 0.0; // Nếu giá trị là NULL, đặt thành 0.0
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Ghi lại lỗi để dễ dàng kiểm tra
            // Bạn có thể xử lý ngoại lệ theo cách khác tùy vào yêu cầu của ứng dụng
        }
        return tongGia;
    }

    public int themGioHang(String ma, String ma2, int soLuong, double donGia) {
        sql = "insert into HoaDonChiTiet(MaHD,MaSPCT,SoLuong,DonGia)\n"
                + "values(?,?,?,?)";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, ma2);
            ps.setObject(3, soLuong);
            ps.setObject(4, donGia);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int xoa1GH(String ma1, String ma2) {
        sql = "delete from HoaDonChiTiet\n"
                + "where MaHD = ? and MaSPCT = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma1);
            ps.setObject(2, ma2);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int xoaGH(String ma) {
        sql = "delete from HoaDonChiTiet\n"
                + "where MaHD = ? ";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int updateGioHang(int sl, String ma1, String ma2) {
        sql = "update HoaDonChiTiet\n"
                + "set SoLuong = ?\n"
                + "where MaHD = ? and MaSPCT =?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, sl);
            ps.setObject(2, ma1);
            ps.setObject(3, ma2);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public int getSL(String ma) {
        sql = "select SoLuong from SanPhamChiTiet\n"
                + "where MaSPCT = ?";
        int sl = 0;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            if (rs.next()) {
                sl = rs.getInt(1);
                if (rs.wasNull()) {
                    sl = 0;
                }
            }
        } catch (Exception e) {
            return 0;
        }
        return sl;
    }

    public void updateGia1(String ma, double gia) {
        sql = """
                update HoaDon 
                set GiaCuoiCung = ? 
                where MaHD = ?
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, gia);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updateGia2(String ma) {
        sql = """
                update HoaDon 
                set GiaCuoiCung = (select sum(SoLuong * DonGia) from HoaDonChiTiet where MaHD = ?)
                where MaHD = ?
            """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<model_PhieuGiamGia> getPGG(double gia) {
        ArrayList<model_PhieuGiamGia> listPGG = new ArrayList<>();
        String sql = """
                     SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, MaPGG,TenPGG,DieuKienGiam,GiaGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG,TrangThai
                     FROM PhieuGiamGia
                     where SoLuong > 0 and DieuKienGiam<=? and TrangThai = N'Đang áp dụng'
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, gia);
            rs = ps.executeQuery();
            while (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                double giaGiam;
                int soLuong;
                java.util.Date ngayTao;
                java.util.Date ngayBatDau;
                java.util.Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiam = rs.getDouble(5);
                giaGiamToiDa = rs.getDouble(6);
                soLuong = rs.getInt(7);
                ngayTao = rs.getDate(8);
                ngayBatDau = rs.getDate(9);
                ngayKetThuc = rs.getDate(10);
                maHTGG = rs.getBoolean(11);
                trangThai = rs.getString(12);

                model_PhieuGiamGia PGG = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);

                listPGG.add(PGG);
            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void updateGia(double gia, String ma) {
        sql = """
              update HoaDon
              set GiaCuoiCung = ?
              where MaHD = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, gia);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<model_PhieuGiamGia> getAll() {
        ArrayList<model_PhieuGiamGia> listPGG = new ArrayList<>();
        String sql = """
                     SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, MaPGG,TenPGG,DieuKienGiam,GiaGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG,TrangThai
                     FROM PhieuGiamGia
                     """;
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                double giaGiam;
                int soLuong;
                java.util.Date ngayTao;
                java.util.Date ngayBatDau;
                java.util.Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiam = rs.getDouble(5);
                giaGiamToiDa = rs.getDouble(6);
                soLuong = rs.getInt(7);
                ngayTao = rs.getDate(8);
                ngayBatDau = rs.getDate(9);
                ngayKetThuc = rs.getDate(10);
                maHTGG = rs.getBoolean(11);
                trangThai = rs.getString(12);

                model_PhieuGiamGia PGG = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);

                listPGG.add(PGG);
            }
            return listPGG;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int huyHD(String ma) {
        sql = "update HoaDon\n"
                + "set MaTT = 3, MaHTTT = 0\n"
                + "where MaHD = ?";
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        } catch (Exception e) {
            return 0;
        }
    }

    public model_PhieuGiamGia getPGG(String ten) {
        sql = "SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, MaPGG,TenPGG,DieuKienGiam,GiaGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG,TrangThai\n"
                + "FROM PhieuGiamGia\n"
                + "where TenPGG = ?";
        model_PhieuGiamGia pgg = new model_PhieuGiamGia();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, ten);
            rs = ps.executeQuery();
            if (rs.next()) {
                int stt;
                String maPGG;
                String tenPGG;
                double dieuKienGiam;
                double giaGiamToiDa;
                double giaGiam;
                int soLuong;
                java.util.Date ngayTao;
                java.util.Date ngayBatDau;
                java.util.Date ngayKetThuc;
                boolean maHTGG;
                String trangThai;

                stt = rs.getInt(1);
                maPGG = rs.getString(2);
                tenPGG = rs.getString(3);
                dieuKienGiam = rs.getDouble(4);
                giaGiam = rs.getDouble(5);
                giaGiamToiDa = rs.getDouble(6);
                soLuong = rs.getInt(7);
                ngayTao = rs.getDate(8);
                ngayBatDau = rs.getDate(9);
                ngayKetThuc = rs.getDate(10);
                maHTGG = rs.getBoolean(11);
                trangThai = rs.getString(12);

                pgg = new model_PhieuGiamGia(stt, maPGG, tenPGG, dieuKienGiam, giaGiam, giaGiamToiDa, soLuong, ngayTao, ngayBatDau, ngayKetThuc, maHTGG, trangThai);
            }
        } catch (Exception e) {
            return null;
        }
        return pgg;
    }

    public KhachHang search(String tenkh) {
        sql = """
              select MaKH,TenKH,SDT,DiaChi from KhachHang
              where TenKH = ?
              """;
        KhachHang kh = new KhachHang();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, tenkh);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ma, ten, sdt, dc;
                ma = rs.getString(1);
                ten = rs.getString(2);
                sdt = rs.getString(3);
                dc = rs.getString(4);
                kh = new KhachHang(ma, ten, sdt, dc);
            }
        } catch (Exception e) {
            return null;
        }
        return kh;
    }

    public ArrayList<SanPhamChiTietJoin> getSP() {
        String sql = "SELECT spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct \n"
                + "JOIN SanPham  sp ON spct.MaSP = sp.MaSP\n"
                + "JOIN KieuDang  kd ON spct.MaKD = kd.MaKD\n"
                + "JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK\n"
                + "JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK\n"
                + "JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK\n"
                + "JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG\n"
                + "JOIN MauGong  mg ON spct.MaMG = mg.MaMG\n"
                + "JOIN KichCo  kc ON spct.MaKC = kc.MaKC\n"
                + "JOIN ThuongHieu  th ON spct.MaTH = th.MaTH\n"
                + "where spct.SoLuong > 0";
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public ArrayList<SanPhamChiTietJoin> search(String text, String th, String kc, String clg) {
        sql = """
              SELECT ROW_NUMBER() OVER (ORDER BY spct.MaSPCT) AS STT, spct.MaSPCT,sp.TenSP,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,kc.TenKC,th.TenTH,spct.SoLuong,spct.GiaBan,spct.NgayNhap,spct.TrangThai FROM SanPhamChiTiet spct 
              JOIN SanPham  sp ON spct.MaSP = sp.MaSP
              JOIN KieuDang  kd ON spct.MaKD = kd.MaKD
              JOIN LoaiMatKinh  lmk ON spct.MaLMK = lmk.MaLMK
              JOIN MauMatKinh  mmk ON spct.MaMMK = mmk.MaMMK
              JOIN ChatLieuMatKinh  clmk ON spct.MaCLMK = clmk.MaCLMK
              JOIN ChatLieuGong  clg ON spct.MaCLG = clg.MaCLG
              JOIN MauGong  mg ON spct.MaMG = mg.MaMG
              JOIN KichCo  kc ON spct.MaKC = kc.MaKC
              JOIN ThuongHieu  th ON spct.MaTH = th.MaTH
              where spct.SoLuong > 0 and (spct.MaSPCT like ? or sp.TenSP like ?) and th.TenTH like ? and kc.TenKC like ? and clg.TenCLG like ?
              """;
        ArrayList<SanPhamChiTietJoin> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + text + '%');
            ps.setObject(2, '%' + text + '%');
            ps.setObject(3, th);
            ps.setObject(4, kc);
            ps.setObject(5, clg);
            rs = ps.executeQuery();
            while (rs.next()) {
                Integer MaSPCT = rs.getInt("MaSPCT");
                String MaSP = rs.getString("TenSP");
                String MaKD = rs.getString("TenKD");
                String MaLMK = rs.getString("TenLMK");
                String MaMMK = rs.getString("TenMMK");
                String MaCLMK = rs.getString("TenCLMK");
                String MaCLG = rs.getString("TenCLG");
                String MaMG = rs.getString("TenMG");
                String MaKC = rs.getString("TenKC");
                String MaTH = rs.getString("TenTH");
                Integer soLuong = rs.getInt("SoLuong");
                Double giaBan = rs.getDouble("GiaBan");
                Date ngayNhap = rs.getDate("NgayNhap");
                Boolean trangThai = rs.getBoolean("TrangThai");
                SanPhamChiTietJoin spctj = new SanPhamChiTietJoin(MaSPCT, MaSP, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, MaTH, soLuong, giaBan, ngayNhap, trangThai);
                list.add(spctj);
            }
        } catch (Exception e) {
            return null;
        }
        return list;
    }

    public ArrayList<ThuongHieu> getTenTH() {
        sql = "select TenTH from ThuongHieu";
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String th = rs.getString(1);
                list.add(new ThuongHieu(th));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<KichCo> getTenKC() {
        sql = "select TenKC from KichCo";
        ArrayList<KichCo> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String kc = rs.getString(1);
                list.add(new KichCo(kc));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public ArrayList<ChatLieuGong> getTenCLG() {
        sql = "select TenCLG from ChatLieuGong";
        ArrayList<ChatLieuGong> list = new ArrayList<>();
        try {
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String kc = rs.getString(1);
                list.add(new ChatLieuGong(kc));
            }
            return list;
        } catch (Exception e) {
            return null;
        }
    }

    public void updatesl1(int sl, String ma) {
        sql = """
              update SanPhamChiTiet
              set SoLuong = SoLuong -?
              where MaSPCT = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, sl);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void updatesl2(int sl, String ma) {
        sql = """
              update SanPhamChiTiet
              set SoLuong = SoLuong +?
              where MaSPCT = ?
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, sl);
            ps.setObject(2, ma);
            ps.executeUpdate();
        } catch (Exception e) {

        }
    }

    public void lshd1(String manv) {
        sql = """
              insert into LichSuHoaDon(MaHD,MaNV,MaCV,NgayThucHien)
              values((SELECT MaHD FROM @InsertedMaHD),?,1,GETDATE())
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, manv);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lshd2(String mahd, String manv) {
        sql = """
              insert into LichSuHoaDon(MaHD,MaNV,MaCV,NgayThucHien)
              values(?,?,2,GETDATE())
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahd);
            ps.setObject(2, manv);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lshd3(String mahd, String manv) {
        sql = """
              insert into LichSuHoaDon(MaHD,MaNV,MaCV,NgayThucHien)
              values(?,?,3,GETDATE())
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahd);
            ps.setObject(2, manv);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void lshd4(String mahd, String manv) {
        sql = """
              insert into LichSuHoaDon(MaHD,MaNV,MaCV,NgayThucHien)
              values(?,?,4,GETDATE())
              """;
        try {
            ps = con.prepareStatement(sql);
            ps.setObject(1, mahd);
            ps.setObject(2, manv);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void updatePgg(String ma){
        sql = """
              update PhieuGiamGia
              set SoLuong = SoLuong -1
              where MaPGG = ?
              """;
        try{
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
