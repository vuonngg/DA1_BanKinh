/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import DBConnect.DBConnect;
import Model.HoaDon.Model_HoaDon;
import Model.KhachHang;
import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class rp_KhachHang {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    public rp_KhachHang(){
        con = DBConnect.getConnection();
    }
    public ArrayList<KhachHang> getKh() {
        sql = "select ROW_NUMBER() over(order by MaKH) as STT, makh,tenkh,diachi,sdt,email,ngaytao from KhachHang";
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setSTT(rs.getInt(1));
                kh.setMaKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setNgayTao(rs.getString(7));
                list.add(kh);

            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int them(KhachHang kh) {
        sql = "insert into KhachHang(tenkh,diachi,sdt,email,ngayTao)\n"
                + "values (?,?,?,?,getDate())";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.getDiaChi());
            ps.setObject(3, kh.getSdt());
            ps.setObject(4, kh.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int themKH(KhachHang kh){
        sql = """
              insert into KhachHang(TenKH,SDT,NgayTao)
              values(?,?,getdate())
              """;
        try{
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.getSdt());
            return ps.executeUpdate();
        }catch(Exception e){
            return 0;
        }
    }

    public int sua(String maKH, KhachHang kh) {
        sql = "update KhachHang\n"
                + "set TenKH=?,DiaChi=?,SDT=?,Email=?,ngayTao=?\n"
                + "where makh=?";
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getTenKH());
            ps.setObject(2, kh.getDiaChi());
            ps.setObject(3, kh.getSdt());
            ps.setObject(4, kh.getEmail());
            ps.setObject(5, kh.getNgayTao());
            ps.setObject(6, maKH);
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public ArrayList<KhachHang> searchKH(String ma) {
        sql = "select ROW_NUMBER() over(order by MaKH) as STT, makh,tenkh,diachi,sdt,email,ngaytao from KhachHang where tenkh like ?";
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + '%');
            ps.setObject(2, '%' + ma + '%');
            rs=ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setSTT(rs.getInt(1));
                kh.setMaKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setNgayTao(rs.getString(7));
                list.add(kh);

            }
            return list;
            

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
   
    public ArrayList<KhachHang> loc(int ngay){
        sql="select  ROW_NUMBER() over(order by MaKH) as STT, makh,tenkh,diachi,sdt,email,ngaytao from khachhang\n" +
"where ngaytao >= convert(nvarchar(10),dateadd(day,?,getdate()),120) and ngaytao <= Convert(nvarchar(10),getdate(),111)";
        ArrayList<KhachHang> list = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ngay);
            rs=ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setSTT(rs.getInt(1));
                kh.setMaKH(rs.getString(2));
                kh.setTenKH(rs.getString(3));
                kh.setDiaChi(rs.getString(4));
                kh.setSdt(rs.getString(5));
                kh.setEmail(rs.getString(6));
                kh.setNgayTao(rs.getString(7));
                list.add(kh);
            }
            return list;
            

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public ArrayList<Model_HoaDon> getLSMH(String ma) {
        ArrayList<Model_HoaDon> listhd = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by hd.MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,Sum(hdct.SoLuong*hdct.DonGia) as GiaBanDau,pgg.GiaGiamToiDa,Sum(hdct.SoLuong*hdct.DonGia) - pgg.GiaGiamToiDa as GiaCuoi,hd.TenKhachHang,hd.SDT,hd.DiaChi \n"
                + "from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT\n"
                + "join TrangThai tt on hd.MaTT = tt.MaTT \n"
                + "join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG\n"
                + "join HoaDonChiTiet hdct on hd.MaHD = hdct.MaHD\n"
                + "Group by hd.MaHD,hd.MaKH,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.TenKhachHang,hd.DiaChi,hd.SDT,hd.MaPGG,hd.GiaCuoiCung,pgg.GiaGiamToiDa\n"
                + "having hd.MaKH = ? ";
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
    public KhachHang timKH(String sdt){
        sql = """
              select MaKH,TenKH,SDT,DiaChi from KhachHang
              where SDT = ?
              """;
        KhachHang kh = new KhachHang();
        try{
            ps = con.prepareStatement(sql);
            ps.setObject(1, sdt);
            rs = ps.executeQuery();
            if(rs.next()){
                String makh, ten,sd,dc;
                makh = rs.getString(1);
                ten = rs.getString(2);
                sd = rs.getString(3);
                dc = rs.getString(4);
                kh = new KhachHang(makh,ten,sd,dc);
            }
        }catch(Exception e){
            return null;
        }
        return kh;
    }
    
}
