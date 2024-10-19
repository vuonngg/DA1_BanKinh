/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import java.sql.*;
import java.util.ArrayList;
import Model.NhanVien.NhanVien;
import Model.NhanVien.NhanVien;
import Model.VaiTro;

/**
 *
 * @author admin
 */
public class rp_NhanVien {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    private String sql;

    public ArrayList<NhanVien> getNhanVien() {
        ArrayList<NhanVien> list = new ArrayList<>();
        sql = "select ROW_NUMBER() over(order by MaNV) as STT, manv,hoten,gioitinh,diachi,sdt,email,cccd,vaiTro from NhanVien where TrangThai = 1";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setSTT(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setGioiTinh(rs.getBoolean(4));
                nv.setDiaChi(rs.getString(5));
                nv.setEmail(rs.getString(7));
                nv.setSdt(rs.getString(6));
                nv.setCCCD((rs.getString(8)));
                nv.setVaiTro((rs.getBoolean(9)));
                list.add(nv);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int themNV(NhanVien nv) {
        sql = "insert into NhanVien( manv,hoten,gioitinh,diachi,sdt,email,cccd,vaiTro,TrangThai)\n"
                + "values(?,?,?,?,?,?,?,?,1)";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getMaNV());
            ps.setObject(2, nv.getHoTen());
            ps.setObject(3, nv.isGioiTinh());
            ps.setObject(4, nv.getDiaChi());
            ps.setObject(5, nv.getSdt());
            ps.setObject(6, nv.getEmail());
            ps.setObject(7, nv.getCCCD());
            ps.setObject(8, nv.isVaiTro());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    public int suaNV(String maNV, NhanVien nv) {
        sql = "update NhanVien\n"
                + "set HoTen=?,GioiTinh=?,DiaChi=?,SDT=?,Email=?,vaiTro=?\n"
                + "where MaNV=?";
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, nv.getHoTen());
            ps.setObject(2, nv.isGioiTinh());
            ps.setObject(3, nv.getDiaChi());
            ps.setObject(4, nv.getSdt());
            ps.setObject(5, nv.getEmail());
            ps.setObject(6, nv.isVaiTro());
            ps.setObject(7, maNV);
            return ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }

    }

    public ArrayList<VaiTro> getAllVT() {
        sql = "Select mavt,tenvt,mota from vaitro";
        ArrayList<VaiTro> listVT = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                VaiTro vt = new VaiTro();
                vt.setMaVT(rs.getInt(1));
                vt.setTenVT(rs.getString(2));
                vt.setMoTa(rs.getString(3));
                listVT.add(vt);
            }
            return listVT;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public ArrayList<NhanVien> checkTrung(String ma) {
        sql = "select manv,hoten,gioitinh,diachi,sdt,email,cccd,vaiTro\n"
                + "from NhanVien\n"
                + "where manv=?";
        ArrayList<NhanVien> listNV = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString(1));
                nv.setHoTen(rs.getString(2));
                nv.setGioiTinh(rs.getBoolean(3));
                nv.setDiaChi(rs.getString(4));
                nv.setEmail(rs.getString(6));
                nv.setSdt(rs.getString(5));
                nv.setCCCD((rs.getString(7)));
                nv.setVaiTro((rs.getBoolean(8)));
                listNV.add(nv);
            }
            return listNV;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    public ArrayList<NhanVien> search(String ma) {
        sql = "select ROW_NUMBER() over(order by MaNV) as STT, MaNV,HoTen,GioiTinh,DiaChi,SDT,Email,CCCD,vaiTro\n"
                + "from NhanVien\n"
                + "where manv like ? or HoTen like ? ";
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + ma + "%");
            ps.setObject(2, '%' + ma + "%");
            rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setSTT(rs.getInt(1));
                nv.setMaNV(rs.getString(2));
                nv.setHoTen(rs.getString(3));
                nv.setGioiTinh(rs.getBoolean(4));
                nv.setDiaChi(rs.getString(5));
                nv.setEmail(rs.getString(7));
                nv.setSdt(rs.getString(6));
                nv.setCCCD((rs.getString(8)));
                nv.setVaiTro((rs.getBoolean(9)));
                list.add(nv);
            }
            return list;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public NhanVien getNV(String ma){
        sql = """
              select MaNV,HoTen from NhanVien
              where MaNV = ?
              """;
        NhanVien nv = new NhanVien();
        try{
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            rs = ps.executeQuery();
            if(rs.next()){
                String man,ten;
                man = rs.getString(1);
                ten = rs.getString(2);
                nv = new NhanVien(man,ten);                       
            }
        }catch(Exception e){
            return null;
        }
        return nv;
    }
    public int nghiViec(String ma){
        sql = """
              update NhanVien
              set TrangThai = 0,MaNV = LEFT(REPLACE(NEWID(), '-', ''), 10)
              where MaNV = ?
              """;
        try{
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            return ps.executeUpdate();
        }catch(Exception e){
            return 0;
        }
    }
    public void delete(String ma){
        sql = """
              delete from Account
              where TaiKhoan = ?
              """;
        try{
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, ma);
            ps.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
