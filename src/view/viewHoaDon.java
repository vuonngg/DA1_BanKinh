/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JInternalFrame.java to edit this template
 */
package view;

import java.awt.Desktop;
import Repositories.Repo_HoaDon;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;
import Model.HoaDon.*;
import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import java.awt.Dimension;
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
import java.io.OutputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import javax.swing.JTable;
import javax.swing.table.TableModel;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.JFileChooser;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;

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
    private WebcamPanel panel = null;
    private Webcam webcam = null;
    private String maNV;
    view_trangChu view;

    /**
     * Creates new form viewHoaDon
     */
    public viewHoaDon(view_trangChu view) {
        initComponents();
        this.fillHoaDon(rp.getHoaDon());
        this.view = view;
    }

    public void fillHoaDon(ArrayList<Model_HoaDon> list) {
        model1 = (DefaultTableModel) tblHoaDon.getModel();
        model1.setRowCount(0);
        for (Model_HoaDon hd : list) {
            model1.addRow(hd.toTable());
        }
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
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

//    public void showLineChart(){
//        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
//    }
    public BufferedImage matToBufferedImage(Mat mat) {
        Mat mat2 = new Mat();
        Imgproc.cvtColor(mat, mat2, Imgproc.COLOR_BGR2RGB);  // Chuyển từ BGR sang RGB
        byte[] data = new byte[(int) (mat2.total() * mat2.channels())];
        mat2.get(0, 0, data);

        BufferedImage image = new BufferedImage(mat2.cols(), mat2.rows(), BufferedImage.TYPE_3BYTE_BGR);
        image.getRaster().setDataElements(0, 0, mat2.cols(), mat2.rows(), data);
        return image;
    }

    public void openFile(String file) {
        try {
            File path = new File(file);
            Desktop.getDesktop().open(path);
        } catch (IOException ioe) {
            System.out.println(ioe);
        }
    }

    public void initWebcam() {
        Dimension size = WebcamResolution.QVGA.getSize();
        webcam = Webcam.getWebcams().get(0);
        webcam.setViewSize(size);

        panel = new WebcamPanel(webcam);
        panel.setPreferredSize(size);
        panel.setFPSDisplayed(true);
        jPanel2.add(panel, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 470, 300));
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
        txtTimKiem = new javax.swing.JTextField();
        jLabel31 = new javax.swing.JLabel();
        cboSapXep = new javax.swing.JComboBox<>();
        btnSua = new javax.swing.JButton();
        cboTrangThai = new javax.swing.JComboBox<>();
        btnQr = new javax.swing.JButton();
        cldNgayBatDau = new com.toedter.calendar.JDateChooser();
        cldNgayKetThuc = new com.toedter.calendar.JDateChooser();
        btnTimKiem = new javax.swing.JButton();
        btnPDF = new javax.swing.JButton();
        btnExcel = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        tblHoaDon = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblLichSuHoaDon = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1350, 1076));

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
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
        );

        jPanel16.setBackground(new java.awt.Color(204, 204, 204));

        jLabel31.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel31.setText("Tìm kiếm bằng các trường của Hóa đơn");

        cboSapXep.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Giá tăng dần", "Giá giảm dần", " ", " " }));
        cboSapXep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboSapXepActionPerformed(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        cboTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "All", "Đã thanh toán", "Chưa thanh toán", "Hủy", "Hoàn trả", " " }));

        btnQr.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/reset-20-32.png"))); // NOI18N
        btnQr.setText("Mã QR");
        btnQr.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnQrActionPerformed(evt);
            }
        });

        btnTimKiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/search-454-48.png"))); // NOI18N
        btnTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTimKiemActionPerformed(evt);
            }
        });

        btnPDF.setText("Export PDF");
        btnPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPDFActionPerformed(evt);
            }
        });

        btnExcel.setText("Export Excel");
        btnExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31, javax.swing.GroupLayout.PREFERRED_SIZE, 257, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnSua)
                            .addGroup(jPanel16Layout.createSequentialGroup()
                                .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(40, 40, 40)
                                .addComponent(cldNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(31, 31, 31)
                                .addComponent(cldNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(28, 28, 28)
                                .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(27, 27, 27)
                        .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(39, 39, 39)
                        .addComponent(btnQr)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnPDF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnExcel)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel31)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btnQr)
                                .addComponent(btnPDF)
                                .addComponent(btnExcel))
                            .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(cboSapXep, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btnTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cldNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(cldNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18))
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(cboTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnSua))))
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
            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
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
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(117, 117, 117))
        );

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 30)); // NOI18N
        jLabel1.setText("Hóa đơn");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(544, 544, 544)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tblHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblHoaDonMouseClicked
        // TODO add your handling code here:
        i = tblHoaDon.getSelectedRow();
        cboTrangThai.setSelectedItem(tblHoaDon.getValueAt(i, 5));
        String ma = tblHoaDon.getValueAt(i, 1).toString();
        this.fillHDCT(rp.getHDCT(ma));
        this.fillLSHD(rp.getLSHD(ma));
    }//GEN-LAST:event_tblHoaDonMouseClicked

    private void btnExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExcelActionPerformed
        // TODO add your handling code here:
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet("danhsach");
            XSSFRow row = null;
            Cell cell = null;

            Row rowCol = sheet.createRow(3);

            cell = rowCol.createCell(0, CellType.STRING);
            cell.setCellValue("STT");

            cell = rowCol.createCell(1, CellType.STRING);
            cell.setCellValue("Mã hóa đơn");

            cell = rowCol.createCell(2, CellType.STRING);
            cell.setCellValue("Mã khách hàng");

            cell = rowCol.createCell(3, CellType.STRING);
            cell.setCellValue("Mã phiếu giảm giá");

            cell = rowCol.createCell(4, CellType.STRING);
            cell.setCellValue("Hình thức thanh toán");

            cell = rowCol.createCell(5, CellType.STRING);
            cell.setCellValue("Trạng thái");

            cell = rowCol.createCell(6, CellType.STRING);
            cell.setCellValue("Ngày tạo");

            cell = rowCol.createCell(7, CellType.STRING);
            cell.setCellValue("Giá ban đầu");

            cell = rowCol.createCell(8, CellType.STRING);
            cell.setCellValue("Giá giảm");

            cell = rowCol.createCell(9, CellType.STRING);
            cell.setCellValue("Giá cuối cùng");

            cell = rowCol.createCell(10, CellType.STRING);
            cell.setCellValue("Tên khách hàng");

            cell = rowCol.createCell(11, CellType.STRING);
            cell.setCellValue("Số điện thoại");

            cell = rowCol.createCell(12, CellType.STRING);
            cell.setCellValue("Địa chỉ");

            for (int j = 0; j < tblHoaDon.getRowCount(); j++) {
                row = sheet.createRow(4+j);
                for (int k = 0; k < tblHoaDon.getColumnCount(); k++) {
                    cell = row.createCell(k);
                    if (tblHoaDon.getValueAt(j, k) != null) {
                        cell.setCellValue(tblHoaDon.getValueAt(j, k).toString());
                    }
                }
            }
            File f = new File("D:/HoaDon.xlsx");
            try {
                FileOutputStream fos = new FileOutputStream(f);
                workbook.write(fos);
                fos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        JOptionPane.showMessageDialog(this, "Xuất file thành công");
    }//GEN-LAST:event_btnExcelActionPerformed

    private void btnPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPDFActionPerformed
        // TODO add your handling code here:

        int selectedRow = tblHoaDon.getSelectedRow();

        if (selectedRow == -1) {
            // Không có bản ghi nào được chọn
            JOptionPane.showMessageDialog(this, "Vui lòng chọn một bản ghi để xuất PDF.", "Không có bản ghi được chọn", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // Hiển thị hộp thoại chọn đường dẫn và tên file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Lưu file PDF");
        int userSelection = fileChooser.showSaveDialog(this);

        if (userSelection != JFileChooser.APPROVE_OPTION) {
            // Người dùng đã hủy bỏ việc lưu file
            return;
        }

        String filePath = fileChooser.getSelectedFile().getAbsolutePath();

        // Đảm bảo file có đuôi .pdf
        if (!filePath.toLowerCase().endsWith(".pdf")) {
            filePath += ".pdf";
        }

        // Gọi phương thức xuất PDF
        try {
            exportPDF(tblHoaDon, selectedRow, filePath);
            JOptionPane.showMessageDialog(this, "Xuất PDF thành công tại: " + filePath, "Thành công", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi khi xuất PDF: " + ex.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnPDFActionPerformed

    private void btnTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTimKiemActionPerformed
        // TODO add your handling code here:
        String tk;
        String tt;
        if (cboTrangThai.getSelectedItem().toString().equals("All")) {
            tt = "%";
        } else {
            tt = cboTrangThai.getSelectedItem().toString();
        }

        if (txtTimKiem.getText().equals("")) {
            tk = "%";
        } else {
            tk = txtTimKiem.getText();
        }
        Date ngayBatDau, ngayKetThuc;
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        if (cldNgayBatDau.getDate() == null) {
            try {
                ngayBatDau = spf.parse("0001-12-11");
            } catch (Exception e) {
                ngayBatDau = new Date();
            }
        } else {
            ngayBatDau = cldNgayBatDau.getDate();
        }

        if (cldNgayKetThuc.getDate() == null) {
            try {
                ngayKetThuc = spf.parse("9999-12-10");
            } catch (Exception e) {
                ngayKetThuc = new Date();
            }
        } else {
            ngayKetThuc = cldNgayKetThuc.getDate();
        }

        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
        } else {
            String d1 = spf.format(ngayBatDau);
            String d2 = spf.format(ngayKetThuc);

            this.fillHoaDon(rp.searchHoaDon(tk, d1, d2, tt));
            System.out.println(ngayBatDau);
            System.out.println(tk);
            System.out.println(tt);
        }
    }//GEN-LAST:event_btnTimKiemActionPerformed

    private void btnQrActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnQrActionPerformed
        // TODO add your handling code here:

        VideoCapture capture = new VideoCapture(0);  // Mở webcam
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(1);

        // Đặt thời gian dừng là 15 giây
        executor.schedule(() -> {
            System.out.println("Dừng quét sau 15 giây.");
            capture.release();  // Giải phóng webcam

        }, 15, TimeUnit.SECONDS);  // Đặt thời gian 15 giây
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
                System.out.println(rs);
                this.fillHDCT(rp.getHDCT(rs.getText()));
                this.fillLSHD(rp.getLSHD(rs.getText()));

                break;  // Thoát sau khi quét thành công

            } catch (Exception e) {
                // Không tìm thấy mã QR, tiếp tục quét
            }
        }

        capture.release();  // Giải phóng tài nguyên webcam
    }//GEN-LAST:event_btnQrActionPerformed

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        // TODO add your handling code here:
        i = tblHoaDon.getSelectedRow();
        if (i == -1) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng muốn sửa");
        } else {
            String ma = tblHoaDon.getValueAt(i, 1).toString();
            if (rp.suaHoaDon(ma, ReadForm()) > 0) {
                JOptionPane.showMessageDialog(this, "Sửa thành công");
                rp.lshd4(ma, this.getMaNV());
                this.fillHoaDon(rp.getHoaDon());
                this.fillLSHD(rp.getLSHD(ma));
            } else {
                JOptionPane.showMessageDialog(this, "Sửa thất bại");
            }
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void cboSapXepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboSapXepActionPerformed
        // TODO add your handling code here:
        String s = cboSapXep.getSelectedItem().toString();
        String tk;
        String tt = cboTrangThai.getSelectedItem().toString();
        if (txtTimKiem.getText().equals("")) {
            tk = "%";
        } else {
            tk = txtTimKiem.getText();
        }
        Date ngayBatDau, ngayKetThuc;
        SimpleDateFormat spf = new SimpleDateFormat("yyyy-MM-dd");
        if (cldNgayBatDau.getDate() == null) {
            try {
                ngayBatDau = spf.parse("0001-12-11");
            } catch (Exception e) {
                ngayBatDau = new Date();
            }
        } else {
            ngayBatDau = cldNgayBatDau.getDate();
        }

        if (cldNgayKetThuc.getDate() == null) {
            try {
                ngayKetThuc = spf.parse("9999-12-10");
            } catch (Exception e) {
                ngayKetThuc = new Date();
            }
        } else {
            ngayKetThuc = cldNgayKetThuc.getDate();
        }

        if (ngayBatDau.after(ngayKetThuc)) {
            JOptionPane.showMessageDialog(this, "Ngày bắt đầu phải nhỏ hơn ngày kết thúc");
        } else {
            String d1 = spf.format(ngayBatDau);
            String d2 = spf.format(ngayKetThuc);
            if (s.equals("Giá tăng dần")) {
                this.fillHoaDon(rp.searchHoaDon(tk, d1, d2, tt));
            } else {
                this.fillHoaDon(rp.sort1HoaDon(tk, d1, d2, tt));
            }
        }
    }//GEN-LAST:event_cboSapXepActionPerformed
    public static void exportPDF(JTable table, int selectedRow, String filePath) throws DocumentException, FileNotFoundException, IOException {
        // Tạo đối tượng Document
        Document document = new Document();

        // Tạo PdfWriter để ghi nội dung vào file PDF
        PdfWriter.getInstance(document, new FileOutputStream(filePath));

        // Mở tài liệu để ghi nội dung
        document.open();

        // Thêm tiêu đề cho tài liệu PDF
        Paragraph title = new Paragraph("Hóa Đơn Bán Hàng", com.itextpdf.text.FontFactory.getFont(com.itextpdf.text.FontFactory.HELVETICA_BOLD, 18));
        title.setAlignment(Element.ALIGN_CENTER);
        document.add(title);

        // Thêm khoảng cách sau tiêu đề
        document.add(new Paragraph(" "));

        // Tạo bảng PDF với 2 cột
        PdfPTable pdfTable = new PdfPTable(2);
        pdfTable.setWidthPercentage(100); // Chiếm 100% chiều rộng trang
        pdfTable.getDefaultCell().setBorder(PdfPCell.NO_BORDER);
        // Lấy mô hình dữ liệu từ JTable
        TableModel model = table.getModel();

        // Lặp qua từng cột của JTable
        for (int col = 0; col < model.getColumnCount(); col++) {
            String columnName = model.getColumnName(col); // Tên cột
            Object cellValue = model.getValueAt(selectedRow, col); // Giá trị của bản ghi
            BaseFont baseFont = BaseFont.createFont("fonts/times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            com.itextpdf.text.Font timesFont = new com.itextpdf.text.Font(baseFont, 12, com.itextpdf.text.Font.NORMAL);
            // Tạo ô cho tên cột
            PdfPCell columnNameCell = new PdfPCell(new Paragraph(columnName, timesFont));
            columnNameCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            columnNameCell.setPadding(5);
            columnNameCell.setBorder(PdfPCell.NO_BORDER); // Bỏ đường viền của ô
            pdfTable.addCell(columnNameCell);

            // Tạo ô cho giá trị của bản ghi với Times New Roman
            PdfPCell cellValueCell = new PdfPCell(new Paragraph(cellValue != null ? cellValue.toString() : "", timesFont));
            cellValueCell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cellValueCell.setPadding(5);
            cellValueCell.setBorder(PdfPCell.NO_BORDER); // Bỏ đường viền của ô
            pdfTable.addCell(cellValueCell);
        }

        // Thêm bảng vào tài liệu PDF
        document.add(pdfTable);

        // Đóng tài liệu PDF
        document.close();
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnExcel;
    private javax.swing.JButton btnPDF;
    private javax.swing.JButton btnQr;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnTimKiem;
    private javax.swing.JComboBox<String> cboSapXep;
    private javax.swing.JComboBox<String> cboTrangThai;
    private com.toedter.calendar.JDateChooser cldNgayBatDau;
    private com.toedter.calendar.JDateChooser cldNgayKetThuc;
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
