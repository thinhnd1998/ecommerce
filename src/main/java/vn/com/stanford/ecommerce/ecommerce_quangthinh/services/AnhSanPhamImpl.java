package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.AnhSanPham;

import java.util.List;

@Service("anhSPDao")
public class AnhSanPhamImpl implements AnhSanPhamDao {
    @Autowired
    AnhSanPhamRepository anhSanPhamRepository;

    @Override
    public List<AnhSanPham> layDanhSach() {
        return (List<AnhSanPham>) anhSanPhamRepository.findAll();
    }

    @Override
    public AnhSanPham layChiTietTheoMa(Object id) {
        AnhSanPham objAnhSanPham = null;
        try {
            objAnhSanPham = anhSanPhamRepository.findById(Long.parseLong(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objAnhSanPham = null;
        }
        return objAnhSanPham;
    }

    @Override
    public List<AnhSanPham> timKiemAnhSP(String tuKhoa) {
        if (tuKhoa != null) {
            return anhSanPhamRepository.timKiemAnh(tuKhoa);
        }
        return (List<AnhSanPham>) anhSanPhamRepository.findAll();
    }

    @Override
    public List<AnhSanPham> layAnhTheoMaSanPham(String maSanPham) {
        List<AnhSanPham> lstAnhSP = anhSanPhamRepository.findByMaSanPham(maSanPham);
        return lstAnhSP;
    }

    @Override
    public AnhSanPham layAnhTheoMaSanPhamVaTenAnh(String maSanPham, String tenAnh) {
        return anhSanPhamRepository.findByMaSanPhamAndMoTa(maSanPham, tenAnh);
    }

    @Override
    public boolean themMoi(AnhSanPham obj) {
        AnhSanPham objAnhSanPham = anhSanPhamRepository.save(obj);

        if (objAnhSanPham != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(AnhSanPham obj) {
        AnhSanPham objAnhSanPham = anhSanPhamRepository.findById(obj.getId()).get();

        if (objAnhSanPham != null) {
            objAnhSanPham.setTenAnh(obj.getTenAnh());
            objAnhSanPham.setMaSanPham(obj.getMaSanPham());
            anhSanPhamRepository.save(objAnhSanPham);
            return true;
        }

        return false;
    }

    @Override
    public boolean xoa(Object id) {
        AnhSanPham objAnhSanPham = layChiTietTheoMa(id);

        if (objAnhSanPham != null) {
            anhSanPhamRepository.delete(objAnhSanPham);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoaTheoMaVaTenAnh(String maSanPham, String tenAnh) {
        AnhSanPham objAnhSanPham = layAnhTheoMaSanPhamVaTenAnh(maSanPham, tenAnh);

        if (objAnhSanPham != null) {
            anhSanPhamRepository.delete(objAnhSanPham);
            return true;
        }
        return false;
    }

    @Override
    public void xoaTheoMaSanPham(String maSP) {
        anhSanPhamRepository.xoaByMaDonHang(maSP);
    }
}
