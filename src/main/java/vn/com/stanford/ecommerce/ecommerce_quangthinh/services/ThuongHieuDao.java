package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieu;

import java.util.List;

public interface ThuongHieuDao extends IHanhDong<ThuongHieu>{
    List<ThuongHieu> timKiemThuongHieu(String tuKhoa);
}
