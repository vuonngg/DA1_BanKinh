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


select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG

select lshd.MaLSHD, lshd.MaNV,cv.TenCV,lshd.NgayThucHien
from LichSuHoaDon lshd join CongViec cv on lshd.MaCV = cv.MaCV
where lshd.MaHD = ?

select sp.TenSP,th.TenTH,kd.TenKD,lmk.TenLMK,mmk.TenMMK,clmk.TenCLMK,clg.TenCLG,mg.TenMG,Kc.TenKC,hdct.SoLuong,hdct.DonGia,hdct.SoLuong * hdct.DonGia as 'Thanh Tien'
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
select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.MaHD like ? or hd.TenKhachHang like ? or hd.MaKH like ?

--Lọc theo ngày
select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao = CONVERT(VARCHAR(10), GETDATE(), 111)

select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
where hd.NgayTao <= CONVERT(VARCHAR(10), GETDATE(), 111) and hd.NgayTao >= CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)

select CONVERT(VARCHAR(10), dateadd(DAY,-3,CONVERT(VARCHAR(10), GETDATE(), 111)), 111)

select CONVERT(VARCHAR(10), GETDATE(), 111) AS NgayThangNam

-- Sap xep tang dan
select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
order by hd.GiaCuoiCung asc

-- Sap xep giam dan
select hd.MaHD,hd.MaKH,hd.MaPGG,httt.TenHTTT,tt.TenTT,hd.NgayTao,hd.GiaBanDau,hd.GiaGiam,hd.GiaCuoiCung,hd.TenKhachHang,hd.SDT,hd.DiaChi 
from HoaDon hd join HinhThucThanhToan httt on hd.MaHTTT = httt.MaHTTT
join TrangThai tt on hd.MaTT = tt.MaTT 
join PhieuGiamGia pgg on hd.MaPGG = pgg.MaPGG
order by hd.GiaCuoiCung desc

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