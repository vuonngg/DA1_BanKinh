/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import Model.GioHang;
import Model.KhachHang;
import Repositories.Repo_HoaDon;
import Repositories.rp_KhachHang;
import javax.swing.table.DefaultTableModel;
import Model.HoaDon.Model_HoaDon;
import Model.HoaDonMoi;
import Model.PhieuGiamGia.model_PhieuGiamGia;
import Model.SanPhamChiTietJoin;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import Repositories.SanPhamChiTietRepository;
import Service.SanPhamChiTietService;
import model.SanPhamChiTiet;
import Model.SanPhamChiTietJoin;
import Model.ThuongHieu;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import model.ChatLieuGong;
import model.KichCo;
import view.viewF.SearchKH;
import view.viewF.TaoKH;

/**
 *
 * @author LEGION 5
 */
public class viewBanHang extends javax.swing.JInternalFrame {

    int i = -1;
    int j = -1;
    int k = -1;
    Repo_HoaDon rphd = new Repo_HoaDon();
    rp_KhachHang rpkh = new rp_KhachHang();
    SanPhamChiTietRepository rpspct = new SanPhamChiTietRepository();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();
    SanPhamChiTietService spcts = new SanPhamChiTietService();
    view_trangChu view;
    private String maNV;

    /**
     * Creates new form banHang
     */
    public viewBanHang(view_trangChu view) {
        initComponents();
        txtMaKH.setText("ADA7E5DA14");
        txtTenKH.setText("Vang lai");
        this.view = view;
        this.fillHoaDonMoi(rphd.getHDM());
        this.fillTableSPCT(rpspct.getSP());
        this.fillPGG(rphd.getAll());
        this.fillCBOTH(rphd.getTenTH());
        this.fillCBOKC(rphd.getTenKC());
        this.fillCBOCLG(rphd.getTenCLG());
        txtMaKH.setEnabled(false);
        txtTenKH.setEnabled(false);
        txtDiaChi.setEnabled(false);
        btnThanhToan.setEnabled(false);
    }
    
    

    public void fillHoaDonMoi(ArrayList<HoaDonMoi> list) {
        model1 = (DefaultTableModel) tblHoaDonMoi.getModel();
        model1.setRowCount(0);
        for (HoaDonMoi hd : list) {
            model1.addRow(hd.toHDM());
        }
    }

    public void showData() {
        tblHoaDonMoi.setRowSelectionInterval(0, 0);
        lblHoaDon.setText(tblHoaDonMoi.getValueAt(0, 1).toString());
        lblGiaCuoi.setText(tblHoaDonMoi.getValueAt(0, 7).toString());
        txtMaKH.setText(tblHoaDonMoi.getValueAt(0, 3).toString());
        txtTenKH.setText(tblHoaDonMoi.getValueAt(0, 4).toString());
        if (tblHoaDonMoi.getValueAt(0, 5) == null) {
            txtSDT.setText("");
        } else {
            txtSDT.setText(tblHoaDonMoi.getValueAt(0, 5).toString());
        }
        if (tblHoaDonMoi.getValueAt(0, 6) == null) {
            txtDiaChi.setText("");
        } else {
            txtDiaChi.setText(tblHoaDonMoi.getValueAt(0, 6).toString());
        }
    }

    public double gia() {
        return Double.parseDouble(lblTongTien.getText());
    }

    public void fillPGG(ArrayList<model_PhieuGiamGia> list) {
        cboPhieuGiamGia.removeAllItems();
        for (model_PhieuGiamGia pgg : list) {
            cboPhieuGiamGia.addItem(pgg.toCombobox());
        }
    }

    public void fillGioHang(ArrayList<GioHang> list) {
        model2 = (DefaultTableModel) tblGioHang.getModel();
        model2.setRowCount(0);
        for (GioHang gh : list) {
            model2.addRow(gh.toGioHang());
        }
    }

    public void suaThanhTien() {
        i = tblHoaDonMoi.getSelectedRow();
        String ma = tblHoaDonMoi.getValueAt(i, 1).toString();
        lblTongTien.setText(Double.toString(rphd.gia(ma)));
    }

    public void fillTableSPCT(ArrayList<SanPhamChiTietJoin> list) {
        DefaultTableModel dtm = (DefaultTableModel) tblSanPham.getModel();
        dtm.setRowCount(0);
        for (SanPhamChiTietJoin spct : list) {
            String trangThai;
            if (!spct.getTrangThai()) {
                trangThai = "Ngừng kinh doanh";
            } else if (spct.getSoLuong() > 0) {
                trangThai = "Còn hàng";
            } else {
                trangThai = "Hết hàng";
            }
            dtm.addRow(new Object[]{
                list.indexOf(spct) + 1,
                spct.getMaSPCT(),
                spct.getTenSP(),
                spct.getTenKD(),
                spct.getTenLMK(),
                spct.getTenMMK(),
                spct.getTenCLMK(),
                spct.getTenCLG(),
                spct.getTenMG(),
                spct.getTenKC(),
                spct.getTenTH(),
                spct.getSoLuong(),
                spct.getGiaBan(),
                spct.getNgayNhap(),
                trangThai
            });
        }
    }

    public void sdt(String sdt) {
        txtSDT.setText(sdt);
    }

    public void setKH() {
        String ma = txtSDT.getText();
        KhachHang kh = rpkh.timKH(ma);
        if (kh != null) {
            txtMaKH.setText(kh.getMaKH());
            txtTenKH.setText(kh.getTenKH());
            txtSDT.setText(kh.getDiaChi());
            txtDiaChi.setText(kh.getSdt());
        } else {
            JOptionPane.showMessageDialog(this, "Số điện thoại không tồn tại");
        }

    }

    public void thanhTien() {
        double giaGoc = Double.parseDouble(lblTongTien.getText());
        double giaGiam;
        double thanhTien;

        if (cboPhieuGiamGia.getItemCount() == 0) {
            lblGiaGiam.setText("0");
            lblGiaCuoi.setText(lblTongTien.getText());
        } else {
            String ten = cboPhieuGiamGia.getSelectedItem().toString();
            model_PhieuGiamGia pgg = rphd.getPGG(ten);
            if (pgg != null) {
                if (pgg.isMaHTGG() == true) {
                    giaGiam = (giaGoc * pgg.getGiaGiam()) / 100;
                    if (giaGiam > pgg.getGiaGiamToiDa()) {
                        lblGiaGiam.setText(Double.toString(pgg.getGiaGiamToiDa()));
                        lblGiaCuoi.setText(Double.toString(giaGoc - pgg.getGiaGiamToiDa()));
                    } else {
                        lblGiaGiam.setText(Double.toString(giaGiam));
                        lblGiaCuoi.setText(Double.toString(giaGoc - giaGiam));
                    }
                } else {
                    lblGiaGiam.setText(Double.toString(pgg.getGiaGiamToiDa()));
                    lblGiaCuoi.setText(Double.toString(giaGoc - pgg.getGiaGiamToiDa()));
                }
            }
        }
    }

    public void setMaNV(String ma) {
        this.maNV = ma;
    }

    public String getMaNV() {
        return maNV;
    }

    public void fillCBOTH(ArrayList<ThuongHieu> list) {
        cboThuongHieu.removeAllItems();
        cboThuongHieu.addItem("Tất cả");
        for (ThuongHieu th : list) {
            cboThuongHieu.addItem(th.getTenTH());
        }
    }

    public void fillCBOKC(ArrayList<KichCo> list) {
        cboKichCo.removeAllItems();
        cboKichCo.addItem("Tất cả");
        for (KichCo th : list) {
            cboKichCo.addItem(th.getTenKC());
        }
    }

    public void fillCBOCLG(ArrayList<ChatLieuGong> list) {
        cboChatLieuGong.removeAllItems();
        cboChatLieuGong.addItem("Tất cả");
        for (ChatLieuGong th : list) {
            cboChatLieuGong.addItem(th.getTenCLG());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDonMoi = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblGioHang = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jPanel14 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtMaKH = new javax.swing.JTextField();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        btnTimKiem = new javax.swing.JButton();
        btnLuuKhachHang = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        cboPhieuGiamGia = new javax.swing.JComboBox<>();
        cboHinhThucThanhToan = new javax.swing.JComboBox<>();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnThanhToan = new javax.swing.JButton();
        txtTienKhachDua = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        btnTao = new javax.swing.JButton();
        lblHoaDon = new javax.swing.JLabel();
        lblTongTien = new javax.swing.JLabel();
        lblGiaGiam = new javax.swing.JLabel();
        lblGiaCuoi = new javax.swing.JLabel();
        lblTienThua = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        jLabel343 = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblSanPham = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        cboThuongHieu = new javax.swing.JComboBox<>();
        cboKichCo = new javax.swing.JComboBox<>();
        cboChatLieuGong = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 102, 51));
        setPreferredSize(new java.awt.Dimension(1350, 885));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn mới", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonMoi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HD", "Ngày tạo", "Mã KH", "Tên KH", "SDT", "Địa chỉ", "Giá", "Trạng thái"
            }
        ));
        tblHoaDonMoi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMoiMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDonMoi);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 204));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblGioHang.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Mã sản phẩm", "Tên sản phẩm", "Tên Kiểu Dáng", "Tên LMK", "Tên MMK", "Ten CLMK", "Tên CLG", "Tên MG", "Tên KC", "Tên TH", "Số lượng", "Giá bán"
            }
        ));
        tblGioHang.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblGioHangMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tblGioHang);

        jButton2.setText("Xóa sản phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 204));

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Khách hàng"));

        jLabel1.setText("Mã KH");

        jLabel2.setText("Tên KH");

        jLabel3.setText("SDT");

        jLabel4.setText("Địa chỉ");

        txtMaKH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtMaKHActionPerformed(evt);
            }
        });

        txtSDT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtSDTActionPerformed(evt);
            }
        });

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        btnTimKiem.setText("Tìm kiếm");
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnLuuKhachHang.setText("Lưu");
        btnLuuKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLuuKhachHangActionPerformed(evt);
            }
        });

        jButton3.setText("Tạo nhanh");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(20, 20, 20)
                        .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.DEFAULT_SIZE, 162, Short.MAX_VALUE)
                            .addComponent(txtTenKH)
                            .addComponent(txtSDT))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnTimKiem)
                            .addComponent(btnLuuKhachHang)
                            .addComponent(jButton3))
                        .addGap(40, 40, 40))))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(txtMaKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(19, 19, 19)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(17, 17, 17))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jButton3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnTimKiem)))
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(16, 16, 16)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(16, 16, 16)
                        .addComponent(btnLuuKhachHang)))
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel5.setText("Mã hóa đơn");

        jLabel6.setText("Tổng tiền");

        jLabel7.setText("Phiếu giảm giá");

        jLabel8.setText("Giá giảm");

        jLabel9.setText("Thanh toán");

        jLabel10.setText("Tiền khách đưa");

        jLabel11.setText("Tiền thừa trả khách");

        jLabel12.setText("Hình thức thanh toán");

        cboPhieuGiamGia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboPhieuGiamGia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPhieuGiamGiaActionPerformed(evt);
            }
        });

        cboHinhThucThanhToan.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Tiền mặt", "Chuyển khoản" }));

        jButton4.setText("Hủy");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Làm mới");

        btnThanhToan.setText("Thanh toán");
        btnThanhToan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnThanhToanActionPerformed(evt);
            }
        });

        txtTienKhachDua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTienKhachDuaActionPerformed(evt);
            }
        });

        jLabel13.setText("VND");

        jLabel14.setText("VND");

        jLabel15.setText("VND");

        jLabel16.setText("VND");

        jLabel17.setText("VND");

        btnTao.setText("Tạo hóa đơn");
        btnTao.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTaoActionPerformed(evt);
            }
        });

        lblHoaDon.setText("jLabel18");

        lblTongTien.setText("0");

        lblGiaGiam.setText("0");

        lblGiaCuoi.setText("0");

        lblTienThua.setText("0");

        jButton5.setText("Chọn");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel11)
                                .addGap(31, 31, 31)
                                .addComponent(lblTienThua)
                                .addGap(86, 86, 86)
                                .addComponent(jLabel16))
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addComponent(jLabel12)
                                .addGap(18, 18, 18)
                                .addComponent(cboHinhThucThanhToan, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(41, 41, 41)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, 353, Short.MAX_VALUE)
                            .addGroup(jPanel14Layout.createSequentialGroup()
                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addGap(72, 72, 72)
                                                .addComponent(lblGiaCuoi))
                                            .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 340, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel14Layout.createSequentialGroup()
                                            .addGap(65, 65, 65)
                                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGap(38, 38, 38)
                                            .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel5)
                                            .addComponent(jLabel6))
                                        .addGap(53, 53, 53)
                                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addComponent(lblHoaDon)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btnTao)
                                                .addGap(19, 19, 19))
                                            .addGroup(jPanel14Layout.createSequentialGroup()
                                                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                                        .addComponent(lblTongTien)
                                                        .addGap(86, 86, 86)
                                                        .addComponent(jLabel14))
                                                    .addComponent(lblGiaGiam)
                                                    .addGroup(jPanel14Layout.createSequentialGroup()
                                                        .addComponent(cboPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jButton5)))
                                                .addGap(0, 0, Short.MAX_VALUE)))))
                                .addGap(0, 13, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jLabel13))
                        .addGap(74, 74, 74))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(52, 52, 52)
                        .addComponent(txtTienKhachDua)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel17)
                        .addGap(73, 73, 73))))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(btnTao)
                    .addComponent(lblHoaDon))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel14)
                    .addComponent(lblTongTien))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(cboPhieuGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jLabel13)
                    .addComponent(lblGiaGiam))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel15)
                    .addComponent(lblGiaCuoi))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtTienKhachDua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel17))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel16)
                    .addComponent(lblTienThua))
                .addGap(18, 18, 18)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(cboHinhThucThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton4))
                .addGap(18, 18, 18)
                .addComponent(btnThanhToan, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel343.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel343.setText("Bán hàng");

        jPanel13.setBackground(new java.awt.Color(204, 204, 204));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "MaSPCT", "TenSP", "Kiểu dáng", "Loại mắt kính", "Màu mắt kính", "Chất liệu mắt kính", "Chất liệu gọng", "Màu gọng", "Kích cỡ", "Thương hiệu", "Số lượng", "Giá"
            }
        ));
        tblSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblSanPham);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-454-48.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        cboThuongHieu.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cboThuongHieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboThuongHieuActionPerformed(evt);
            }
        });

        cboKichCo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        cboChatLieuGong.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel18.setText("Thương hiệu");

        jLabel19.setText("Kích cỡ");

        jLabel20.setText("Chất liệu gọng");

        jLabel21.setText("Tìm kiếm theo MaSPCT hoặc TenSP");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 892, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(41, 41, 41)
                        .addComponent(jLabel18)
                        .addGap(18, 18, 18)
                        .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel19)
                        .addGap(18, 18, 18)
                        .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cboChatLieuGong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(56, 56, 56)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(cboThuongHieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboKichCo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cboChatLieuGong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel18)
                                .addComponent(jLabel19)
                                .addComponent(jLabel20))
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(343, 343, 343))
            .addGroup(layout.createSequentialGroup()
                .addGap(679, 679, 679)
                .addComponent(jLabel343, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel343)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(26, 26, 26))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cboThuongHieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboThuongHieuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboThuongHieuActionPerformed

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void btnTaoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTaoActionPerformed
        // TODO add your handling code here:
        System.out.println(this.getMaNV());
        rphd.taoHoaDon(maNV);
        JOptionPane.showMessageDialog(this, "Tạo hóa đơn thành công");
        this.fillHoaDonMoi(rphd.getHDM());
        this.showData();
    }//GEN-LAST:event_btnTaoActionPerformed


    private void txtMaKHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtMaKHActionPerformed
        // TODO add your handling code here:

    }//GEN-LAST:event_txtMaKHActionPerformed

    private void tblHoaDonMoiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMoiMouseClicked
        // TODO add your handling code here:
        i = tblHoaDonMoi.getSelectedRow();
        lblHoaDon.setText(tblHoaDonMoi.getValueAt(i, 1).toString());
    }//GEN-LAST:event_tblHoaDonMoiMouseClicked

    private void btnThanhToanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnThanhToanActionPerformed
        // TODO add your handling code here:
        i = tblHoaDonMoi.getSelectedRow();
        boolean mahttt = false;
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn muốn thanh toán");
        } else {
            String mahd = tblHoaDonMoi.getValueAt(i, 1).toString();
            String ten;
            String tt = cboHinhThucThanhToan.getSelectedItem().toString();
            if (tt.equals("Tiền mặt")) {
                mahttt = false;
            } else {
                mahttt = true;
            }
            double giabd = Double.parseDouble(lblTongTien.getText());
            double giaGiam = Double.parseDouble(lblGiaGiam.getText());
            double giaCuoi = Double.parseDouble(lblGiaCuoi.getText());
            if (cboPhieuGiamGia.getSelectedItem() == null) {
                int w = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán không?");
                if (w == 0) {
                    if (rphd.thanhToan2(mahd, mahttt, giabd, giaGiam, giaCuoi) > 0) {
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                        rphd.lshd4(mahd, this.getMaNV());
                        this.fillHoaDonMoi(rphd.getHDM());
                        model2 = (DefaultTableModel) tblGioHang.getModel();
                        model2.setRowCount(0);
                        lblHoaDon.setText("");
                        lblTongTien.setText("0");
                        lblGiaGiam.setText("0");
                        lblGiaCuoi.setText("0");
                        txtTienKhachDua.setText("");
                        if(rphd.getHDM().size()>0){
                            this.showData();
                        }else{
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
                    }
                }
            } else {
                ten = cboPhieuGiamGia.getSelectedItem().toString();
                model_PhieuGiamGia pgg = rphd.getPGG(ten);
                String mapgg = pgg.getMaPGG();
                int w = JOptionPane.showConfirmDialog(this, "Xác nhận thanh toán không?");
                if (w == 0) {
                    if (rphd.thanhToan(mahd, mahttt, mapgg, giabd, giaGiam, giaCuoi) > 0) {
                        JOptionPane.showMessageDialog(this, "Thanh toán thành công");
                        rphd.lshd4(mahd, this.getMaNV());
                        this.fillHoaDonMoi(rphd.getHDM());
                        rphd.updatePgg(mapgg);
                        model2 = (DefaultTableModel) tblGioHang.getModel();
                        model2.setRowCount(0);
                        lblHoaDon.setText("");
                        lblTongTien.setText("0");
                        lblGiaGiam.setText("0");
                        lblGiaCuoi.setText("0");
                        txtTienKhachDua.setText("");
                        this.showData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thanh toán thất bại");
                    }
                }
            }
        }
    }//GEN-LAST:event_btnThanhToanActionPerformed

    private void btnLuuKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLuuKhachHangActionPerformed
        // TODO add your handling code here:
        i = tblHoaDonMoi.getSelectedRow();
        if (i != -1) {
            String ma = tblHoaDonMoi.getValueAt(i, 1).toString();
            String mkh = txtMaKH.getText().trim();
            String ten = txtTenKH.getText().trim();
            String sdt = txtSDT.getText().trim();
            String dc = "";
            if (txtDiaChi.getText().equals("")) {
                dc = null;
            } else {
                dc = txtDiaChi.getText().trim();
            }
            if (rphd.capNhatKhachHang(ma, mkh, ten, sdt, dc) > 0) {
                JOptionPane.showMessageDialog(this, "Lưu thành công");
                this.fillHoaDonMoi(rphd.getHDM());
                this.showData();
            }
        }
    }//GEN-LAST:event_btnLuuKhachHangActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        SearchKH kh = new SearchKH(this);
        kh.setVisible(true);
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void tblSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblSanPhamMouseClicked
        // TODO add your handling code here:
        j = tblSanPham.getSelectedRow();
        String ma2 = tblSanPham.getValueAt(j, 1).toString();
        i = tblHoaDonMoi.getSelectedRow();
        String ma1 = tblHoaDonMoi.getValueAt(i, 1).toString();
        double gia = Double.parseDouble(tblSanPham.getValueAt(j, 12).toString());
        int sl = Integer.parseInt(tblSanPham.getValueAt(j, 11).toString());
        int x = Integer.parseInt(JOptionPane.showInputDialog("Vui lòng nhập số lượng").toString());
        int index = -1;
        int slNew = -1;
        for (int i = 0; i < rphd.getGioHang(ma1).size(); i++) {
            if (rphd.getGioHang(ma1).get(i).getMaSPCT().equalsIgnoreCase(ma2)) {
                index = i;
                slNew = rphd.getGioHang(ma1).get(i).getSoLuong() + x;
            }
        }
        try {
            if (x < 0 || x > sl) {
                JOptionPane.showMessageDialog(this, "Số lượng nhiều hơn kho");
                return;
            } else {
                if (index == -1) {

                    if (rphd.themGioHang(ma1, ma2, x, gia) > 0) {
                        rphd.updatesl1(x, ma2);
                        double g = x * gia;
                        this.fillGioHang(rphd.getGioHang(ma1));
                        this.suaThanhTien();
                        this.fillPGG(rphd.getPGG(Double.parseDouble(lblTongTien.getText())));
                        this.fillTableSPCT(rpspct.getSP());
                        rphd.updateGia1(ma1,g);
                        rphd.lshd2(ma1, this.getMaNV());
                        this.fillHoaDonMoi(rphd.getHDM());
                        this.showData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Thêm thất bại");
                    }
                } else {
                    if (rphd.updateGioHang(slNew, ma1, ma2) > 0) {
                        rphd.updatesl1(x, ma2);
                        this.fillTableSPCT(rpspct.getSP());
                        this.fillGioHang(rphd.getGioHang(ma1));
                        this.suaThanhTien();
                        this.fillPGG(rphd.getPGG(Double.parseDouble(lblTongTien.getText())));
                        rphd.updateGia1(ma1,slNew*gia);
                        rphd.lshd2(ma1, this.getMaNV());
                        this.fillHoaDonMoi(rphd.getHDM());
                        this.showData();
                    }
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chỉ được nhập số nguyên");
        }
    }//GEN-LAST:event_tblSanPhamMouseClicked

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        i = tblHoaDonMoi.getSelectedRow();
        k = tblGioHang.getSelectedRow();
        if (k == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn sản phẩm muốn xóa");

        } else {
            String ma1 = tblHoaDonMoi.getValueAt(i, 1).toString();
            String ma2 = tblGioHang.getValueAt(k, 0).toString();
            int sl = Integer.parseInt(tblGioHang.getValueAt(k, 10).toString());
            System.out.println(ma1);
            if (rphd.xoa1GH(ma1, ma2) > 0) {
                JOptionPane.showMessageDialog(this, "Xóa thành công");
                rphd.updatesl2(sl, ma2);
                rphd.updateGia2(ma1);
                this.fillHoaDonMoi(rphd.getHDM());
                this.fillGioHang(rphd.getGioHang(ma1));
                this.fillTableSPCT(rphd.getSP());
                
                this.showData();
                txtTienKhachDua.setText("");
                lblTienThua.setText("0");
                lblGiaGiam.setText("0");
                lblGiaCuoi.setText("0");
                rphd.lshd3(ma1, this.getMaNV());
                this.suaThanhTien();
                this.fillPGG(rphd.getPGG(Double.parseDouble(lblTongTien.getText())));
            } else {
                JOptionPane.showMessageDialog(this, "Xóa thất bại");
            }
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tblGioHangMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblGioHangMouseClicked
        // TODO add your handling code here:


    }//GEN-LAST:event_tblGioHangMouseClicked

    private void cboPhieuGiamGiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPhieuGiamGiaActionPerformed
        // TODO add your handling code here:


    }//GEN-LAST:event_cboPhieuGiamGiaActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        double giaGoc = Double.parseDouble(lblTongTien.getText());
        double giaGiam = 0;
        double thanhTien = 0;
        String ten = "";
        i = tblHoaDonMoi.getSelectedRow();
        String ma = tblHoaDonMoi.getValueAt(i, 1).toString();
        model_PhieuGiamGia pgg = new model_PhieuGiamGia();
        if (cboPhieuGiamGia.getSelectedItem() != null) {
            ten = cboPhieuGiamGia.getSelectedItem().toString();
            pgg = rphd.getPGG(ten);
            if (pgg != null) {
                if (pgg.isMaHTGG() == false) {
                    giaGiam = (giaGoc * pgg.getGiaGiam()) / 100;
                    if (giaGiam > pgg.getGiaGiamToiDa()) {
                        lblGiaGiam.setText(Double.toString(pgg.getGiaGiamToiDa()));
                        lblGiaCuoi.setText(Double.toString(giaGoc - pgg.getGiaGiamToiDa()));
                        thanhTien = Double.parseDouble(lblGiaCuoi.getText());
                        rphd.updateGia(thanhTien, ma);

                        this.fillHoaDonMoi(rphd.getHDM());
                        this.showData();
                    } else {
                        thanhTien = giaGoc - giaGiam;
                        lblGiaGiam.setText(Double.toString(giaGiam));
                        lblGiaCuoi.setText(Double.toString(thanhTien));
                        rphd.updateGia(thanhTien, ma);
                        this.fillHoaDonMoi(rphd.getHDM());
                        this.showData();
                    }
                } else {
                    giaGiam = pgg.getGiaGiam();
                    thanhTien = giaGoc - giaGiam;
                    lblGiaGiam.setText(Double.toString(giaGiam));
                    lblGiaCuoi.setText(Double.toString(thanhTien));
                    rphd.updateGia(thanhTien, ma);
                    this.fillHoaDonMoi(rphd.getHDM());
                    this.showData();
                }
            }
        } else {
            giaGiam = 0;
            thanhTien = giaGoc;
            lblGiaGiam.setText(Double.toString(giaGiam));
            lblGiaCuoi.setText(Double.toString(thanhTien));
        }
        System.out.println(giaGoc);
        System.out.println(ten);
        System.out.println(giaGiam);
        System.out.println(thanhTien);
        System.out.println(pgg.getGiaGiam());
        System.out.println(pgg.getGiaGiamToiDa());
        System.out.println(pgg.isMaHTGG());
    }//GEN-LAST:event_jButton5ActionPerformed

    private void txtTienKhachDuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTienKhachDuaActionPerformed
        // TODO add your handling code here:
        Double giaCuoi = Double.parseDouble(lblGiaCuoi.getText());
        double tien = 0;
        try {
            tien = Double.parseDouble(txtTienKhachDua.getText().trim());
            if (tien < giaCuoi) {
                JOptionPane.showMessageDialog(this, "Khách đưa thiếu tiền");
                btnThanhToan.setEnabled(false);
                return;
            } else {
                btnThanhToan.setEnabled(true);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập số nguyên");
        }
        double tienThua = tien - giaCuoi;
        lblTienThua.setText(Double.toString(tienThua));
    }//GEN-LAST:event_txtTienKhachDuaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        TaoKH view = new TaoKH(this);
        view.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        String text = "";
        if (txtTimKiem.getText().equals("")) {
            text = "%";
        } else {
            text = txtTimKiem.getText().trim();
        }
        String th, kc, clg;
        if (cboThuongHieu.getSelectedItem().toString().equals("Tất cả")) {
            th = "%";
        } else {
            th = cboThuongHieu.getSelectedItem().toString();
        }
        if (cboKichCo.getSelectedItem().toString().equals("Tất cả")) {
            kc = "%";
        } else {
            kc = cboKichCo.getSelectedItem().toString();
        }
        if (cboChatLieuGong.getSelectedItem().toString().equals("Tất cả")) {
            clg = "%";
        } else {
            clg = cboChatLieuGong.getSelectedItem().toString();
        }
        this.fillTableSPCT(rphd.search(text, th, kc, clg));
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void txtSDTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtSDTActionPerformed
        // TODO add your handling code here:
        String sdtMau = "^(032|033|034|035|036|037|038|039|096|097|098|086|083|084|085|081|082|088|091|094|070|079|077|076|078|090|093|089|056|058|092|059|099)[0-9]{7}$";
        if (txtSDT.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập sdt");
        } else {
            String sdt = txtSDT.getText().trim();
            if (!sdt.matches(sdtMau)) {
                JOptionPane.showMessageDialog(this, "SDT sai định dạng");
            } else {
                this.setKH();
            }
        }

    }//GEN-LAST:event_txtSDTActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        i = tblHoaDonMoi.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn hóa đơn muốn hủy");
        } else {
            String ma = tblHoaDonMoi.getValueAt(i, 1).toString();
            int index = JOptionPane.showConfirmDialog(this, "Bạn chắc chẵn muốn hủy");
            if (index == 0) {
                if (rphd.getHDCT(ma).size() == 0) {
                    if (rphd.huyHD(ma) > 0) {
                        JOptionPane.showMessageDialog(this, "Đã hủy hóa đơn thành công");
                        this.fillHoaDonMoi(rphd.getHDM());
                        lblHoaDon.setText("");
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Vui lòng hủy hết sản phẩm trong giỏ hàng");
                }

            } else {

            }
        }
    }//GEN-LAST:event_jButton4ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLuuKhachHang;
    private javax.swing.JButton btnTao;
    private javax.swing.JButton btnThanhToan;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cboChatLieuGong;
    private javax.swing.JComboBox<String> cboHinhThucThanhToan;
    private javax.swing.JComboBox<String> cboKichCo;
    private javax.swing.JComboBox<String> cboPhieuGiamGia;
    private javax.swing.JComboBox<String> cboThuongHieu;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel343;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblGiaCuoi;
    private javax.swing.JLabel lblGiaGiam;
    private javax.swing.JLabel lblHoaDon;
    private javax.swing.JLabel lblTienThua;
    private javax.swing.JLabel lblTongTien;
    private javax.swing.JTable tblGioHang;
    private javax.swing.JTable tblHoaDonMoi;
    private javax.swing.JTable tblSanPham;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtMaKH;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTienKhachDua;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    Model_HoaDon readForm() {
        String ma = txtMaKH.getText();
        String ten = txtTenKH.getText();
        String sdt, dc;
        if (txtSDT.getText().equals("")) {
            sdt = "null";
        } else {
            sdt = txtSDT.getText().trim();
        }
        if (txtDiaChi.getText().equals("")) {
            dc = "null";
        } else {
            dc = txtDiaChi.getText().trim();
        }
        return new Model_HoaDon(ma, ten, sdt, dc);

    }
}
