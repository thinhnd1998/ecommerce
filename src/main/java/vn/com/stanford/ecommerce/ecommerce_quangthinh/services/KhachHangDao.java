package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHang;

import java.util.List;

public interface KhachHangDao extends IHanhDong<KhachHang>{
    List<KhachHang> timKiemKhachHang(String tuKhoa);
}
