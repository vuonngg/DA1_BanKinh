/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import Repositories.Repo_HoaDon;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Model.HoaDon.*;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.NotFoundException;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.videoio.VideoCapture;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.core.CvType;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author LEGION 5
 */
public class viewHoaDon extends javax.swing.JInternalFrame {

    Repo_HoaDon rp = new Repo_HoaDon();
    DefaultTableModel model1 = new DefaultTableModel();
    DefaultTableModel model2 = new DefaultTableModel();
    DefaultTableModel model3 = new DefaultTableModel();
    int i = -1;

    /**
     * Creates new form viewHoaDon
     */
    public viewHoaDon() {
        initComponents();
        this.fillHoaDon(rp.getHoaDon());
    }

    public void fillHoaDon(ArrayList<Model_HoaDon> list) {
        model1 = (DefaultTableModel) tblHoaDon.getModel();
        model1.setRowCount(0);
        for (Model_HoaDon hd : list) {
            model1.addRow(hd.toTable());
        }
    }

    public void fillLSHD(ArrayList<Model_LichSuHoaDon> list) {
        model2 = (DefaultTableModel) tblLichSuHoaDon.getModel();
        model2.setRowCount(0);
        for (Model_LichSuHoaDon lshd : list) {
            model2.addRow(lshd.toTable());
        }
    }

    public void fillHDCT(ArrayList<Model_HDCT> list) {
        model3 = (DefaultTableModel) tblHoaDonChiTiet.getModel();
        model3.setRowCount(0);
        for (Model_HDCT hdct : list) {
            model3.addRow(hdct.toHDCT());
        }
    }

    static {
        // Load thư viện OpenCV
        System.loadLibrary(Core.NATIVE_LIBRARY_NAME);
    }

    public BufferedImage matToBufferedImage(Mat mat) {
        Mat mat2 = new Mat();
        Imgproc.cvtColor(mat, mat2, Imgproc.COLOR_BGR2RGB);  // Chuyển từ BGR sang RGB
        byte[] data = new byte[(int) (mat2.total() * mat2.channels())];
        mat2.get(0, 0, data);

        BufferedImage image = new BufferedImage(mat2.cols(), mat2.rows(), BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, mat2.cols(), mat2.rows(), data);
        return image;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblHoaDonChiTiet = new javax.swing.JTable();
        jPanel16 = new javax.swing.JPanel();
        cboNgay = new javax.swing.JComboBox<>();
        btnTimKiem = new javax.swing.JButton();
        txtTimKiem = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cboSapXep = new javax.swing.JComboBox<>();
        btnSapXep = new javax.swing.JButton();
        btnLoc = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnQr = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1330, 1076));

        jPanel1.setBackground(new java.awt.Color(204, 204, 204));

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn chi tiết", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 14))); // NOI18N

        tblHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Thương hiệu", "Kiểu dáng", "Loại mắt kính ", "Màu mắt kính", "Chất liệu mắt kính", "Chất liệu gọng", "Màu gọng", "Kích cỡ", "Số lượng", "Đơn giá", "Thành tiền"
            }
        ));
        jScrollPane1.setViewportView(tblHoaDonChiTiet);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        cboNgay.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hôm nay", "Từ 3 ngày trước", " ", " " }));
        cboNgay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboNgayActionPerformed(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-454-48.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Tìm kiếm bằng...");

        cboSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá tăng dần", "Giá giảm dần", " ", " " }));

        btnSapXep.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnSapXep.setText("Sắp xếp theo giá");
        btnSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSapXepActionPerformed(evt);
            }
        });

        btnLoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnLoc.setText("Lọc theo ngày");
        btnLoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLocActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán", "Hủy", "Hoàn trả", " " }));

        btnQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnQr.setText("Mã QR");
        btnQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQrActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLoc)
                .addGap(18, 18, 18)
                .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnSapXep)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnQr)
                .addGap(47, 47, 47))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(13, Short.MAX_VALUE)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(jLabel31)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnLoc)
                            .addComponent(cboNgay, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSua)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnSapXep)
                            .addComponent(btnQr))
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa đơn", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 12))); // NOI18N

        tblHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã hóa đơn", "Mã khách hàng", "Mã phiếu giảm giá", "Hình thức thanh toán", "Trạng thái", "Ngày tạo", "Giá ban đầu", "Giá Giảm", "Thành tiền", "Tên khách hàng", "Số điện thoại", "Địa chỉ"
            }
        ));
        tblHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblHoaDonMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(tblHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 89, Short.MAX_VALUE)
                .addContainerGap())
        );

        jScrollPane2.setBorder(javax.swing.BorderFactory.createTitledBorder("Lịch sử hóa đơn"));

        tblLichSuHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "STT", "MaNV", "TenCV", "Ngay"
            }
        ));
        jScrollPane2.setViewportView(tblLichSuHoaDon);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(610, 610, 610))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(476, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        if (txtTimKiem.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Chưa nhập mã cần tìm");
        } else {
            String tk = txtTimKiem.getText();
            this.fillHoaDon(rp.searchHoaDon(tk));
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQrActionPerformed
        // TODO add your handling code here:

        VideoCapture capture = new VideoCapture(0);  // Mở webcam

        if (!capture.isOpened()) {
            JOptionPane.showMessageDialog(this, "Không thể mở webcam", "Lỗi", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Mat frame = new Mat();
        while (capture.read(frame)) {
            BufferedImage bufferedImage = matToBufferedImage(frame);

            try {
                // Chuyển BufferedImage thành BinaryBitmap để ZXing xử lý
                BufferedImageLuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
                BinaryBitmap binaryBitmap = new BinaryBitmap(new HybridBinarizer(luminanceSource));

                // Quét mã QR
                Result rs = new MultiFormatReader().decode(binaryBitmap);
                JOptionPane.showMessageDialog(this, "Quét thành công");
                this.fillHoaDon(rp.qrCode(rs.getText()));
//                this.fillHDCT(rp.getHDCT(rs.getText()));
//                this.fillLSHD(rp.getLSHD(rs.getText()));

                break;  // Thoát sau khi quét thành công

            } catch (Exception e) {
                // Không tìm thấy mã QR, tiếp tục quét
            }
        }

        capture.release();  // Giải phóng tài nguyên webcam

    }//GEN-LAST:event_btnQrActionPerformed

    private void btnLocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLocActionPerformed
        // TODO add your handling code here:
        String l = cboNgay.getSelectedItem().toString();
        if (l.equals("Hôm nay")) {
            this.fillHoaDon(rp.getHoaDon());
        } else {
            this.fillHoaDon(rp.locHoaDon());
        }

    }//GEN-LAST:event_btnLocActionPerformed

    private void btnSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSapXepActionPerformed
        // TODO add your handling code here:
        String s = cboSapXep.getSelectedItem().toString();
        String l = cboNgay.getSelectedItem().toString();
        if (l.equals("Hôm nay")) {
            if (s.equals("Giá tăng dần")) {
                this.fillHoaDon(rp.sort1HoaDon());
            } else {
                this.fillHoaDon(rp.sort2HoaDon());
            }
        } else {
            if (s.equals("Giá tăng dần")) {
                this.fillHoaDon(rp.sort3HoaDon());
            } else {
                this.fillHoaDon(rp.sort4HoaDon());
            }
        }

    }//GEN-LAST:event_btnSapXepActionPerformed

    private void cboNgayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboNgayActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboNgayActionPerformed

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        i = tblHoaDon.getSelectedRow();
        cboTrangThai.setSelectedItem(tblHoaDon.getValueAt(i, 5));
        String ma = tblHoaDon.getValueAt(i, 1).toString();
        this.fillHDCT(rp.getHDCT(ma));
        this.fillLSHD(rp.getLSHD(ma));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        i = tblHoaDon.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng muốn sửa");
        } else {
            String ma = tblHoaDon.getValueAt(i, 0).toString();
            if (rp.suaHoaDon(ma, ReadForm()) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                this.fillHoaDon(rp.getHoaDon());
                this.fillLSHD(rp.getLSHD(ma));
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLoc;
    private javax.swing.JButton btnQr;
    private javax.swing.JButton btnSapXep;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboNgay;
    private javax.swing.JComboBox<String> cboSapXep;
    private javax.swing.JComboBox<String> cboTrangThai;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTable tblHoaDon;
    private javax.swing.JTable tblHoaDonChiTiet;
    private javax.swing.JTable tblLichSuHoaDon;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables

    Model_HoaDon ReadForm() {
        String tt = cboTrangThai.getSelectedItem().toString();
        return new Model_HoaDon(tt);
    }
}
