package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangExtend;

import java.util.List;

public interface DonHangDao extends IHanhDong<DonHang>{
    List<DonHangExtend> layDanhSachExtend();

    List<DonHangExtend> timKiemDonHang(String tuKhoa);
    List<DonHang> layDanhSachTheoMaKH(int maKH);
}
