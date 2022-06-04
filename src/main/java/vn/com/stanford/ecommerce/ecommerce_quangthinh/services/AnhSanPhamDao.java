package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.AnhSanPham;

import java.util.List;

public interface AnhSanPhamDao extends IHanhDong<AnhSanPham> {
    List<AnhSanPham> layAnhTheoMaSanPham(String maSanPham);
    AnhSanPham layAnhTheoMaSanPhamVaTenAnh(String maSanPham, String tenAnh);
    List<AnhSanPham> timKiemAnhSP(String tuKhoa);
    boolean xoaTheoMaVaTenAnh(String maSanPham, String tenAnh);
    void xoaTheoMaSanPham(String maSP);
}
