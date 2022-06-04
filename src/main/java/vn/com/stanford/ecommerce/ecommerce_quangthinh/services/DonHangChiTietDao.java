package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangChiTiet;

import java.util.List;

public interface DonHangChiTietDao extends IHanhDong<DonHangChiTiet> {
    List<DonHangChiTiet> timKiemDonHangCT(String tuKhoa);
    List<DonHangChiTiet> layDanhSachTheoMaDonHang(long maDonHang);
    void xoaTheoMaDonHang(long id);
}
