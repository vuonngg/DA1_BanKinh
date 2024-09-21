Create Database P
ro1041_Nhom4
use Pro1041_Nhom4
-- Bang san pham
create table SanPham(
	MaSP nvarchar(10) primary key,
	TenSP nvarchar(20),
);

--Bang kieu dang
create table KieuDang(
	MaKD nvarchar(10) primary key,
	TenKD nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Loai mat kinh
create table LoaiMatKinh(
	MaLMK nvarchar(10) primary key,
	TenLMK nvarchar(20) not null,
	Mota nvarchar(20) 
);

--Bang Mau mat kinh
create table MauMatKinh(
	MaMMK nvarchar(10) primary key,
	TenMMK nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Chat lieu mat kinh
create table ChatLieuMatKinh(
	MaCLMK nvarchar(10) primary key,
	TenCLMK nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Chat lieu gong
create table ChatLieuGong(
	MaCLG nvarchar(10) primary key,
	TenCLG nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Màu gong
create table MauGong(
	MaMG nvarchar(10) primary key,
	TenMG nvarchar(20) not null,
	MoTa nvarchar(20)
);

--Bang Kich co
create table KichCo(
	MaKC nvarchar(10) primary key,
	TenKC nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Thuong hieu
create table ThuongHieu(
	MaTH nvarchar(10) primary key,
	TenTH nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Chi tiet san pham
create table SanPhamChiTiet(
	MaSPCT int identity(1,1) primary key,
	MaSP nvarchar(10) references SanPham(MaSP),
	MaTH nvarchar(10) references ThuongHieu(MaTH),
	MaKD nvarchar(10) references KieuDang(MaKD),
	MaLMK nvarchar(10) references LoaiMatKinh(MaLMK),
	MaMMK nvarchar(10) references MauMatKinh(MaMMK),
	MaCLMK nvarchar(10) references ChatLieuMatKinh(MaCLMK),
	MaCLG nvarchar(10) references ChatLieuGong(MaCLG),
	MaMG nvarchar(10) references MauGong(MaMG),
	MaKC nvarchar(10) references KichCo(MaKC),
	SoLuong int,
	GiaBan float,
	NgayNhap date,
);

-- Table Khach hang
create table KhachHang(
	MaKH nvarchar(10) primary key,
	TenKH nvarchar(20) not null,
	DiaChi nvarchar(100),
	SDT varchar(15) not null,
	Email varchar(50),
	CCCD nchar(12),
);

-- Bang Vai tro
create table VaiTro(
	MaVT int identity(1,1) primary key,
	TenVT nvarchar(20),
	MoTa nvarchar(20)
);

-- Bang Nhan vien
create table NhanVien(
	MaNV nvarchar(10) primary key,
	HoTen nvarchar(30),
	GioiTinh bit,
	DiaChi nvarchar(100),
	SDT varchar(12),
	Email varchar(50),
	CCCD nchar(12),
	MaVT int references VaiTro(MaVT)
);


-- Bang Hinh thuc giam gia
create table HinhThucGiamGia(
	MaHTGG bit primary key,
	TenHTGG nvarchar(20) not null,
	MoTa nvarchar(20),
);

-- Bang Hinh thuc thanh toan
create table HinhThucThanhToan(
	MaHTTT bit primary key,
	TenHTTT nvarchar(20),
	MoTa nvarchar(20)
);


-- Bang Trang thai
create table TrangThai(
	MaTT int primary key,
	TenTT nvarchar(20) not null,
	MoTa nvarchar(20)
);

-- Bang Voucher
create table PhieuGiamGia(
	MaPGG nvarchar(10) primary key,
	TenPGG nvarchar(20),
	DieuKienGiam float not null,
	GiaGiamToiDa float not null,
	SoLuong int,
	NgayTao date,
	NgayBatDau date,
	NgayKetThuc date,
	MaHTGG bit references HinhThucGiamGia(MaHTGG)
);

-- Bang Hoa don
create table HoaDon(
	MaHD nvarchar(100) primary key,
	MaKH nvarchar(10) references KhachHang(MaKH),
	MaPGG nvarchar(10) references PhieuGiamGia(MaPGG),
	MaHTTT bit references HinhThucThanhToan(MaHTTT),
	MaTT int references TrangThai(MaTT),
	NgayTao date,
	GiaBanDau float,
	GiaGiam float,
	GiaCuoiCung float,
	TenKhachHang nvarchar(30),
	DiaChi nvarchar(100),
	SDT nvarchar(12)
);


-- Bang Hoa don chi tiet
create table HoaDonChiTiet(
	MaHDCT int identity(1,1) primary key,
	MaHD nvarchar(100) references HoaDon(MaHD),
	MaSPCT int references SanPhamChiTiet(MaSPCT),
	SoLuong int,
	DonGia float,
	ThanhTien float,
);

-- Bang Account
create table Account(
	TaiKhoan nvarchar(20) primary key,
	MatKhau nvarchar(20) not null,
	MaNV nvarchar(10) references NhanVien(MaNV),
	SDT varchar(12),
);

-- Bang Cong viec
create table CongViec(
	MaCV int primary key,
	TenCV nvarchar(20),
	MoTa nvarchar(20),
);

-- Bang Lich su hoa don
create table LichSuHoaDon(
	MaLSHD int identity(1,1) primary key,
	MaHD nvarchar(100) references HoaDon(MaHD),
	MaNV nvarchar(10) references NhanVien(MaNV),
	MaCV int references CongViec(MaCV),
	NgayThucHien date,
);

delete from HoaDonChiTiet

select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG

select ROW_NUMBER() over(order by MaLSHD) as STT, lshd.MaNV,cv.TenCV,lshd.NgayThucHien
from LichSuHoaDon lshd join CongViec cv on lshd.MaCV = cv.MaCV
where lshd.MaHD = 'HD01'

select ROW_NUMBER() over(order by MaHDCT) as STT, sp.TenSP,th.TenTH,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,Kc.TenKC,hdct.SoLuong,hdct.DonGia,hdct.SoLuong * hdct.DonGia as 'Thanh Tien'
from HoaDonChiTiet hdct join SanPhamChiTiet spct on hdct.MaSPCT = spct.MaSPCT
join SanPham sp on sp.MaSP = spct.MaSP
join ThuongHieu th on spct.MaTH = th.MaTH
join KieuDang kd on spct.MaKD = kd.MaKD
join LoaiMatKinh lmk on lmk.MaLMK = spct.MaLMK
join MauMatKinh mmk on mmk.MaMMK = spct.MaMMK
join ChatLieuMatKinh clmk on clmk.MaCLMK = spct.MaCLMK
join ChatLieuGong clg on spct.MaCLG = clg.MaCLG
join MauGong mg on mg.MaMG = spct.MaMG
join KichCo kc on kc.MaKC = spct.MaKC
join HoaDon hd on hd.MaHD = hdct.MaHD
where hdct.MaHD = ?

--Tìm kiếm hoa don
select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.MaHD like ? or hd.TenKhachHang like ? or hd.MaKH like ?

--Lọc theo ngày
select ROW_NUMBER() over(order by MaHD) as STT,hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)

select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)

-- Sap xep tang dan
select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)
order by hd.GiaCuoiCung asc

select ROW_NUMBER() over(order by MaHD) as STT, hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)
order by hd.GiaCuoiCung asc

-- Sap xep giam dan
select ROW_NUMBER() over(order by MaHD) as STT hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)
order by hd.GiaCuoiCung desc

select ROW_NUMBER() over(order by MaHD) as STT hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)
order by hd.GiaCuoiCung asc

Insert into HoaDon(MaHD,NgayTao,GiaCuoiCung)
values('HD04','2024-09-21',2000000)

select * from HoaDon
order by GiaCuoiCung desc

Insert into TrangThai(MaTT,TenTT)
values(4,N'Hoàn trả')

Insert into PhieuGiamGia(MaPGG,DieuKienGiam,GiaGiamToiDa)
values('PGG03',120000,40000)

insert into HinhThucThanhToan(MaHTTT,TenHTTT)
values(1,'Chuyen khoan')

update HoaDon
set MaPGG = 'PGG03' , MaHTTT = 0 , MaTT = 2 
where MaHD = 'HD04'

update HoaDon
set MaTT = (select MaTT from TrangThai where TenTT = ?)
where MaHD = ?

insert into HoaDonChiTiet(MaHD,SoLuong,DonGia)
values('HD02',2,30000)

select * from HoaDonChiTiet

insert into ThuongHieu


-- Insert data into SanPham
INSERT INTO SanPham (MaSP, TenSP) VALUES
('SP001', 'Sản phẩm 1'),
('SP002', 'Sản phẩm 2'),
('SP003', 'Sản phẩm 3'),
('SP004', 'Sản phẩm 4'),
('SP005', 'Sản phẩm 5');

-- Insert data into KieuDang
INSERT INTO KieuDang (MaKD, TenKD, MoTa) VALUES
('KD001', 'Kiểu dáng 1', 'Mô tả 1'),
('KD002', 'Kiểu dáng 2', 'Mô tả 2'),
('KD003', 'Kiểu dáng 3', 'Mô tả 3'),
('KD004', 'Kiểu dáng 4', 'Mô tả 4'),
('KD005', 'Kiểu dáng 5', 'Mô tả 5');

-- Insert data into LoaiMatKinh
INSERT INTO LoaiMatKinh (MaLMK, TenLMK, MoTa) VALUES
('LMK001', 'Loại mắt kính 1', 'Mô tả 1'),
('LMK002', 'Loại mắt kính 2', 'Mô tả 2'),
('LMK003', 'Loại mắt kính 3', 'Mô tả 3'),
('LMK004', 'Loại mắt kính 4', 'Mô tả 4'),
('LMK005', 'Loại mắt kính 5', 'Mô tả 5');

-- Insert data into MauMatKinh
INSERT INTO MauMatKinh (MaMMK, TenMMK, MoTa) VALUES
('MMK001', 'Màu mắt kính 1', 'Mô tả 1'),
('MMK002', 'Màu mắt kính 2', 'Mô tả 2'),
('MMK003', 'Màu mắt kính 3', 'Mô tả 3'),
('MMK004', 'Màu mắt kính 4', 'Mô tả 4'),
('MMK005', 'Màu mắt kính 5', 'Mô tả 5');

-- Insert data into ChatLieuMatKinh
INSERT INTO ChatLieuMatKinh (MaCLMK, TenCLMK, MoTa) VALUES
('CLMK001', 'Chất liệu mắt kính 1', 'Mô tả 1'),
('CLMK002', 'Chất liệu mắt kính 2', 'Mô tả 2'),
('CLMK003', 'Chất liệu mắt kính 3', 'Mô tả 3'),
('CLMK004', 'Chất liệu mắt kính 4', 'Mô tả 4'),
('CLMK005', 'Chất liệu mắt kính 5', 'Mô tả 5');

-- Insert data into ChatLieuGong
INSERT INTO ChatLieuGong (MaCLG, TenCLG, MoTa) VALUES
('CLG001', 'Chất liệu gọng 1', 'Mô tả 1'),
('CLG002', 'Chất liệu gọng 2', 'Mô tả 2'),
('CLG003', 'Chất liệu gọng 3', 'Mô tả 3'),
('CLG004', 'Chất liệu gọng 4', 'Mô tả 4'),
('CLG005', 'Chất liệu gọng 5', 'Mô tả 5');

-- Insert data into MauGong
INSERT INTO MauGong (MaMG, TenMG, MoTa) VALUES
('MG001', 'Màu gọng 1', 'Mô tả 1'),
('MG002', 'Màu gọng 2', 'Mô tả 2'),
('MG003', 'Màu gọng 3', 'Mô tả 3'),
('MG004', 'Màu gọng 4', 'Mô tả 4'),
('MG005', 'Màu gọng 5', 'Mô tả 5');

-- Insert data into KichCo
INSERT INTO KichCo (MaKC, TenKC, MoTa) VALUES
('KC001', 'Kích cỡ 1', 'Mô tả 1'),
('KC002', 'Kích cỡ 2', 'Mô tả 2'),
('KC003', 'Kích cỡ 3', 'Mô tả 3'),
('KC004', 'Kích cỡ 4', 'Mô tả 4'),
('KC005', 'Kích cỡ 5', 'Mô tả 5');

-- Insert data into ThuongHieu
INSERT INTO ThuongHieu (MaTH, TenTH, MoTa) VALUES
('TH001', 'Thương hiệu 1', 'Mô tả 1'),
('TH002', 'Thương hiệu 2', 'Mô tả 2'),
('TH003', 'Thương hiệu 3', 'Mô tả 3'),
('TH004', 'Thương hiệu 4', 'Mô tả 4'),
('TH005', 'Thương hiệu 5', 'Mô tả 5');

INSERT INTO SanPhamChiTiet (MaSP, MaTH, MaKD, MaLMK, MaMMK, MaCLMK, MaCLG, MaMG, MaKC, SoLuong, GiaBan, NgayNhap) VALUES
('SP001', 'TH001', 'KD001', 'LMK001', 'MMK001', 'CLMK001', 'CLG001', 'MG001', 'KC001', 10, 1500000.0, '2024-01-01'),
('SP002', 'TH002', 'KD002', 'LMK002', 'MMK002', 'CLMK002', 'CLG002', 'MG002', 'KC002', 20, 2000000.0, '2024-01-02'),
('SP003', 'TH003', 'KD003', 'LMK003', 'MMK003', 'CLMK003', 'CLG003', 'MG003', 'KC003', 15, 1800000.0, '2024-01-03'),
('SP004', 'TH004', 'KD004', 'LMK004', 'MMK004', 'CLMK004', 'CLG004', 'MG004', 'KC004', 25, 2200000.0, '2024-01-04'),
('SP005', 'TH005', 'KD005', 'LMK005', 'MMK005', 'CLMK005', 'CLG005', 'MG005', 'KC005', 30, 2500000.0, '2024-01-05');

select * from SanPhamChiTiet

select * from HoaDonChiTiet

insert into HoaDonChiTiet(MaSPCT,MaHD,SoLuong,DonGia)
values(2,'HD03',7,10)

select * from LichSuHoaDon

insert into CongViec(MaCV,TenCV)
values(4,'Xoa sam pham')

insert into LichSuHoaDon(MaHD,MaCV,NgayThucHien)
values(?,2,CONVERT(VARCHAR(10), GETDATE(), 111))

insert into LichSuHoaDon

update HoaDon
set TenKhachHang = N'Hoàng Ngọc Vương'
where MaHD = 'HD03'

select ROW_NUMBER() over(order by MaHD) as STT, MaCV,NgayThucHien from LichSuHoaDon
