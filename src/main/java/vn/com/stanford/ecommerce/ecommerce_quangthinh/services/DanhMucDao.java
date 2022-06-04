package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;

import java.util.List;

public interface DanhMucDao extends IHanhDong<DanhMuc>{
    List<DanhMuc> timKiemDanhMuc(String tuKhoa);
}
