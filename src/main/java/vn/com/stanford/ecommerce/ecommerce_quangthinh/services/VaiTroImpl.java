package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTro;

import java.util.List;

@Service("vaiTroDao")
public class VaiTroImpl implements VaiTroDao {
    @Autowired
    VaiTroRepository vaiTroRepository;

    @Override
    public List<VaiTro> layDanhSach() {
        return (List<VaiTro>) vaiTroRepository.findAll();
    }

    @Override
    public VaiTro layChiTietTheoMa(Object id) {
        VaiTro objVaiTro = null;
        try {
            objVaiTro = vaiTroRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objVaiTro = null;
        }
        return objVaiTro;
    }

    @Override
    public List<VaiTro> timKiemVaiTro(String tuKhoa) {
        if (tuKhoa != null) {
            return vaiTroRepository.timKiemVaiTro(tuKhoa);
        }
        return (List<VaiTro>) vaiTroRepository.findAll();
    }

    @Override
    public boolean themMoi(VaiTro obj) {
        VaiTro objVaiTro = vaiTroRepository.save(obj);

        if (objVaiTro != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(VaiTro obj) {
        VaiTro objVaiTro = vaiTroRepository.findById(obj.getId()).get();

        if (objVaiTro != null) {
            objVaiTro.setTenVaiTro(obj.getTenVaiTro());
            vaiTroRepository.save(objVaiTro);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        VaiTro objVaiTro = layChiTietTheoMa(id);

        if (objVaiTro != null) {
            vaiTroRepository.delete(objVaiTro);
            return true;
        }
        return false;
    }
}
