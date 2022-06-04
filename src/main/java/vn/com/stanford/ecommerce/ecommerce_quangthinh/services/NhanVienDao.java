package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVien;

import java.util.List;

public interface NhanVienDao extends IHanhDong<NhanVien>{
    List<NhanVien> timKiemNhanVien(String tuKhoa);
}
