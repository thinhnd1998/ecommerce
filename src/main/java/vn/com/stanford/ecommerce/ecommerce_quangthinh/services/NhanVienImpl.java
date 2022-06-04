package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVien;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVienRepository;

import java.util.List;

@Service("nhanVienDao")
public class NhanVienImpl implements NhanVienDao{
    @Autowired
    NhanVienRepository nhanVienRepository;

    @Override
    public List<NhanVien> layDanhSach() {
        return (List<NhanVien>) nhanVienRepository.findAll();
    }

    @Override
    public NhanVien layChiTietTheoMa(Object id) {
        NhanVien objNhanVien = null;
        try {
            objNhanVien = nhanVienRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objNhanVien = null;
        }
        return objNhanVien;
    }

    @Override
    public List<NhanVien> timKiemNhanVien(String tuKhoa) {
        if (tuKhoa != null) {
            return nhanVienRepository.timKiemNhanVien(tuKhoa);
        }
        return (List<NhanVien>) nhanVienRepository.findAll();
    }

    @Override
    public boolean themMoi(NhanVien obj) {
        NhanVien objNhanVien = nhanVienRepository.save(obj);

        if (objNhanVien != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(NhanVien obj) {
        NhanVien objNhanVien = nhanVienRepository.findById(obj.getId()).get();

        if (objNhanVien != null) {
            objNhanVien.setHoTen(obj.getHoTen());
            nhanVienRepository.save(objNhanVien);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        NhanVien objNhanVien = layChiTietTheoMa(id);

        if (objNhanVien != null) {
            nhanVienRepository.delete(objNhanVien);
            return true;
        }
        return false;
    }
}
