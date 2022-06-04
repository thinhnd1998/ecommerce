package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHangRepository;

import java.util.List;

@Service("khachHangDao")
public class KhachHangImpl implements KhachHangDao {
    @Autowired
    KhachHangRepository khachHangRepository;

    @Override
    public List<KhachHang> layDanhSach() {
        return (List<KhachHang>) khachHangRepository.findAll();
    }

    @Override
    public KhachHang layChiTietTheoMa(Object id) {
        KhachHang objKhachHang = null;
        try {
            objKhachHang = khachHangRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objKhachHang = null;
        }
        return objKhachHang;
    }

    @Override
    public List<KhachHang> timKiemKhachHang(String tuKhoa) {
        if (tuKhoa != null) {
            return khachHangRepository.timKiemKhachHang(tuKhoa);
        }
        return (List<KhachHang>) khachHangRepository.findAll();
    }

    @Override
    public boolean themMoi(KhachHang obj) {
        KhachHang objKhachHang = khachHangRepository.save(obj);

        if (objKhachHang != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(KhachHang obj) {
        KhachHang objKhachHang = khachHangRepository.findById(obj.getId()).get();

        if (objKhachHang != null) {
            objKhachHang.setHoTen(obj.getHoTen());
            khachHangRepository.save(objKhachHang);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        KhachHang objKhachHang = layChiTietTheoMa(id);

        if (objKhachHang != null) {
            khachHangRepository.delete(objKhachHang);
            return true;
        }
        return false;
    }
}
