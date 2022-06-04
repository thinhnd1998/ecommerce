package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPham;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.SanPhamExtend;

import java.util.Date;
import java.util.List;

public interface SanPhamDao extends IHanhDong<SanPham> {
    List<SanPhamExtend> layDanhSachExtend();
    SanPhamExtend layChiTietTheoMaExtend(String id);
    List<SanPham> layDanhSachTheoDanhMuc(int danhMucId);
    List<SanPham> layDanhSachTheoThuongHieu(int thuongHieuId);
    List<SanPhamExtend> timKiemSanPham(String tuKhoa, int danhMucId, int thuongHieuId, Date startDate, Date endDate, int duyet);
    List<SanPham> timKiemSanPhamClient(String tuKhoa);
    Page<SanPham> phanTrangSanPham(int pageNumber);
//    List<SanPham> timKiemSanPhamTheoNgay(Date startDate, Date endDate);
    boolean duyetSanPham(Object ma);
}
