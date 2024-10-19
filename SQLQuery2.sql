Use SonGlasses

create or alter trigger ThemLSHD on HoaDon
for Insert
as 
begin
Insert into LichSuHoaDon(MaHD,MaNV,NgayThucHien,MaCV)
values((Select MaHD from inserted),'NV01',getdate(),1)
end

select * from HoaDon

Insert into HoaDon(MaTT,NgayTao,MaKH,TenKhachHang)
values(1,convert(nvarchar(10),getdate(),120),'KH00','Vang lai')

select * from LichSuHoaDon

select hd.MaHD,hd.NgayTao,lshd.MaNV,hd.TenKhachHang from HoaDon hd
join LichSuHoaDon lshd on hd.MaHD = lshd.MaHD
where hd.NgayTao = convert(nvarchar(10),getdate(),120) and MaTT = 1

select hdct.MaSPCT,sp.TenSP,hdct.DonGia,hdct.SoLuong from HoaDonChiTiet 
hdct join SanPhamChiTiet spct on hdct.MaSPCT = spct.MaSPCT
join SanPham sp on sp.MaSP = spct.MaSP
where MaHD = ?

select sum(DonGia*SoLuong) from HoaDonChiTiet
where MaHD = 'D693B0FA60'

select * from HoaDonChiTiet

insert into HoaDonChiTiet(MaHD,MaSPCT,SoLuong,DonGia)
values(?,?,?,?)

delete from HoaDonChiTiet
where MaHD = ? and MaSPCT = ?

select SoLuong from SanPhamChiTiet
where MaSPCT = ?


update HoaDon
set MaTT = 3
where MaHD = ?

SELECT ROW_NUMBER() OVER (ORDER BY maPGG) AS STT, MaPGG,TenPGG,DieuKienGiam,GiaGiam,GiaGiamToiDa,SoLuong,NgayTao,NgayBatDau,NgayKetThuc,MaHTGG,TrangThai
FROM PhieuGiamGia

alter table PhieuGiamGia
add GiaGiam float