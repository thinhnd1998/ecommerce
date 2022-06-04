package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDung;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NguoiDungExtend;

import java.util.List;

public interface NguoiDungDao extends IHanhDong<NguoiDung>{
    List<NguoiDungExtend> layDanhSachExtend();

    List<NguoiDungExtend> timKiemNguoiDung(String tuKhoa, int vaiTroId);

    NguoiDung layNguoiDungTheoUsername(String tenDN);
}
