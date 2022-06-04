package vn.com.stanford.ecommerce.ecommerce_quangthinh.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangChiTiet;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DonHangChiTietRepository;

import java.util.List;

@Service("donHangCTDao")
public class DonHangChiTietImpl implements DonHangChiTietDao {
    @Autowired
    DonHangChiTietRepository donHangChiTietRepository;

    @Override
    public List<DonHangChiTiet> layDanhSach() {
        return (List<DonHangChiTiet>) donHangChiTietRepository.findAll();
    }

    @Override
    public List<DonHangChiTiet> layDanhSachTheoMaDonHang(long maDonHang) {
        return donHangChiTietRepository.findByMaDonHang(maDonHang);
    }

    @Override
    public DonHangChiTiet layChiTietTheoMa(Object id) {
        DonHangChiTiet objDonHangCT = null;

        try {
            objDonHangCT = donHangChiTietRepository.findById(Long.parseLong(id + "")).get();
        } catch (Exception ex) {
            System.out.println("Lỗi: " + ex.getMessage());
            objDonHangCT = null;
        }

        return objDonHangCT;
    }

    @Override
    public List<DonHangChiTiet> timKiemDonHangCT(String tuKhoa) {
        if (tuKhoa != null) {
            return donHangChiTietRepository.timKiemDonHangCT(tuKhoa);
        }
        return (List<DonHangChiTiet>) donHangChiTietRepository.findAll();
    }

    @Override
    public boolean themMoi(DonHangChiTiet obj) {
        DonHangChiTiet objDonHangCT = donHangChiTietRepository.save(obj);

        if (objDonHangCT != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean capNhat(DonHangChiTiet obj) {
        DonHangChiTiet objDonHangCT = donHangChiTietRepository.findById(obj.getId()).get();

        if (objDonHangCT != null) {
            objDonHangCT.setDonGia(obj.getDonGia());
            objDonHangCT.setSoLuong(obj.getSoLuong());
            objDonHangCT.setMaDonHang(obj.getMaDonHang());
            objDonHangCT.setMaSanPham(obj.getMaSanPham());
            donHangChiTietRepository.save(objDonHangCT);
            return true;
        }
        return false;
    }

    @Override
    public boolean xoa(Object id) {
        DonHangChiTiet objDonHangCT = layChiTietTheoMa(id);

        if (objDonHangCT != null) {
            donHangChiTietRepository.delete(objDonHangCT);
            return true;
        }
        return false;
    }

    @Override
    public void xoaTheoMaDonHang(long id) {
        donHangChiTietRepository.xoaByMaDonHang(id);
    }
}
