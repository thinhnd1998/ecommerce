package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ChucNang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyen;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.PhanQuyenRepository;

import java.util.List;

@Service("phanQuyenDao")
public class PhanQuyenImpl implements PhanQuyenDao {
    @Autowired
    PhanQuyenRepository phanQuyenRepository;

    @Override
    public List<PhanQuyen> layDanhSach() {
        return (List<PhanQuyen>) phanQuyenRepository.findAll();
    }

    @Override
    public List<PhanQuyen> listQuyenChuaPhan(List<Integer> lstChucNangId) {
        return phanQuyenRepository.findByChucNangIdNotIn(lstChucNangId);
    }

    @Override
    public PhanQuyen layChiTietTheoMa(Object id) {
        PhanQuyen objPhanQuyen = null;
        try {
            objPhanQuyen = phanQuyenRepository.findById(Long.parseLong(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objPhanQuyen = null;
        }
        return objPhanQuyen;
    }

    @Override
    public List<PhanQuyen> layChiTietTheoVaiTroId(int id) {
        return phanQuyenRepository.findByVaiTroId(id);
    }

    @Override
    public boolean themMoi(PhanQuyen obj) {
        PhanQuyen objPhanQuyen = phanQuyenRepository.save(obj);

        if (objPhanQuyen != null) {
            return true;
        }

        return false;
    }

    @Override
    public boolean capNhat(PhanQuyen obj) {
        PhanQuyen objPhanQuyen = phanQuyenRepository.findById(obj.getId()).get();

        if (objPhanQuyen != null) {
            phanQuyenRepository.save(objPhanQuyen);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        PhanQuyen objPhanQuyen = layChiTietTheoMa(id);
        if (objPhanQuyen != null) {
            phanQuyenRepository.delete(objPhanQuyen);
            return true;
        }
        return false;
    }

    @Override
    public PhanQuyen layChiTietTheoVaiTro_ChucNang(int vaiTroId, int chucNangId) {
        PhanQuyen objPhanQuyen = null;
        try {
            objPhanQuyen = phanQuyenRepository.findByVaiTroIdAndAndChucNangId(vaiTroId, chucNangId);
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objPhanQuyen = null;
        }
        return objPhanQuyen;
    }

    @Override
    public boolean xoaTheoVaiTro_ChucNang(int vaiTroId, int chucNangId) {
        PhanQuyen objPhanQuyen = layChiTietTheoVaiTro_ChucNang(vaiTroId, chucNangId);
        if (objPhanQuyen != null) {
            phanQuyenRepository.delete(objPhanQuyen);
            return true;
        }
        return false;
    }
}
