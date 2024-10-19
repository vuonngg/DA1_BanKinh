/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Repositories;

import Model.DangNhap;
import java.sql.*;

import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class rp_DangNhap {

    private Connection con = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;
    String sql;

    public DangNhap getRole(String userName, String passWord) {
        sql = "select ac.TaiKhoan,ac.MatKhau,nv.VaiTro,ac.SDT from Account ac \n"
                + "join NhanVien nv on ac.MaNV = nv.MaNV\n"
                + "where ac.TaiKhoan = ? and ac.MatKhau = ?";
        DangNhap dn = new DangNhap();
        try {
            con = DBConnect.DBConnect.getConnection();
            ps = con.prepareStatement(sql);
            ps.setObject(1, userName);
            ps.setObject(2, passWord);
            rs = ps.executeQuery();
            if (rs.next()) {
                String ac,pw,sdt;
                boolean r;
                ac = rs.getString(1);
                pw = rs.getString(2);
                r = rs.getBoolean(3);
                sdt = rs.getString(4);
                dn = new DangNhap(ac,pw,r,sdt);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return dn;
    }

}
