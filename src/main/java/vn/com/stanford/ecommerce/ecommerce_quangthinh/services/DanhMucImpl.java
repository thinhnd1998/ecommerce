package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMucRepository;

import java.util.List;

@Service("danhMucDao")
public class DanhMucImpl implements DanhMucDao {
    @Autowired
    DanhMucRepository danhMucRepository;
    
    @Override
    public List<DanhMuc> layDanhSach() {
        return (List<DanhMuc>) danhMucRepository.findAll();
    }

    @Override
    public DanhMuc layChiTietTheoMa(Object id) {
        DanhMuc objDanhMuc = null;
        try {
            objDanhMuc = danhMucRepository.findById(Integer.parseInt(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objDanhMuc = null;
        }
        return objDanhMuc;
    }

    @Override
    public List<DanhMuc> timKiemDanhMuc(String tuKhoa) {
        if (tuKhoa != null) {
            return danhMucRepository.timKiemDanhMuc(tuKhoa);
        }
        return (List<DanhMuc>) danhMucRepository.findAll();
    }

    @Override
    public boolean themMoi(DanhMuc obj) {
        DanhMuc objDanhMuc = danhMucRepository.save(obj);

        if (objDanhMuc != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DanhMuc obj) {
        DanhMuc objDanhMuc = danhMucRepository.findById(obj.getId()).get();

        if (objDanhMuc != null) {
            objDanhMuc.setTenDanhMuc(obj.getTenDanhMuc());
            danhMucRepository.save(objDanhMuc);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DanhMuc objDanhMuc = layChiTietTheoMa(id);

        if (objDanhMuc != null) {
            danhMucRepository.delete(objDanhMuc);
            return true;
        }
        return false;
    }
}
