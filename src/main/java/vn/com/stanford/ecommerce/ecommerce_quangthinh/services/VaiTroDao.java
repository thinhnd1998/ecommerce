package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTro;

import java.util.List;

public interface VaiTroDao extends IHanhDong<VaiTro>{
    List<VaiTro> timKiemVaiTro(String tuKhoa);
}
