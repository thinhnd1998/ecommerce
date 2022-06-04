package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieu;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieuRepository;

import java.util.List;

@Service("thuongHieuDao")
public class ThuongHieuImpl implements ThuongHieuDao {
    @Autowired
    ThuongHieuRepository thuongHieuRepository;

    @Override
    public List<ThuongHieu> layDanhSach() {
        return (List<ThuongHieu>) thuongHieuRepository.findAll();
    }

    @Override
    public ThuongHieu layChiTietTheoMa(Object id) {
        ThuongHieu objThuongHieu = null;
        try {
            objThuongHieu = thuongHieuRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objThuongHieu = null;
        }
        return objThuongHieu;
    }

    @Override
    public List<ThuongHieu> timKiemThuongHieu(String tuKhoa) {
        if (tuKhoa != null) {
            return thuongHieuRepository.timKiemThuongHieu(tuKhoa);
        }
        return (List<ThuongHieu>) thuongHieuRepository.findAll();
    }

    @Override
    public boolean themMoi(ThuongHieu obj) {
        ThuongHieu objThuongHieu = thuongHieuRepository.save(obj);

        if (objThuongHieu != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(ThuongHieu obj) {
        ThuongHieu objThuongHieu = thuongHieuRepository.findById(obj.getId()).get();

        if (objThuongHieu != null) {
            objThuongHieu.setTenThuongHieu(obj.getTenThuongHieu());
            thuongHieuRepository.save(objThuongHieu);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        ThuongHieu objThuongHieu = layChiTietTheoMa(id);
        if (objThuongHieu != null) {
            thuongHieuRepository.delete(objThuongHieu);
            return true;
        }
        return false;
    }
}
