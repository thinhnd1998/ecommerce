package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ChucNang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyen;

import java.util.List;

public interface PhanQuyenDao extends IHanhDong<PhanQuyen>{
    PhanQuyen layChiTietTheoVaiTro_ChucNang(int vaiTroId, int chucNangId);
    boolean xoaTheoVaiTro_ChucNang(int vaiTroId, int chucNangId);
    List<PhanQuyen> layChiTietTheoVaiTroId(int id);
    List<PhanQuyen> listQuyenChuaPhan(List<Integer> lstChucNangId);
}
