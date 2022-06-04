package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangChiTietDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DonHangDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.SanPhamDao;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class DonHangChiTietCallAPI {
    @Autowired
    DonHangChiTietDao donHangChiTietDao;

    @RequestMapping(value = "donhangct/all")
    public String hienThiDanhSachDonHangCT(Model model) {
        List<DonHangChiTiet> lstDonHangChiTiet = donHangChiTietDao.layDanhSach();

        model.addAttribute("lstDonHangChiTiet", lstDonHangChiTiet);
        model.addAttribute("donHangChiTietSearch", new DonHangChiTietViewModel());

        return "QuanLyDonHangCT";
    }

    @RequestMapping(value = "donhangct/timkiem")
    public String timKiemDonHangChiTiet(@ModelAttribute("donHangChiTietSearch") DonHangChiTietViewModel donHangCT, Model model) {
        List<DonHangChiTiet> lstDonHangChiTiet = donHangChiTietDao.timKiemDonHangCT(donHangCT.getTuKhoa());
        model.addAttribute("lstDonHangChiTiet", lstDonHangChiTiet);
        model.addAttribute("donHangChiTietSearch", donHangCT);
        return "QuanLyDonHangCT";
    }

    @Autowired
    SanPhamDao sanPhamDao;
    @ModelAttribute("lstSanPham")
    public List<SanPham> hienThiDanhSachSanPham() {
        List<SanPham> lstSanPham = sanPhamDao.layDanhSach();

        return lstSanPham;
    }

    @Autowired
    DonHangDao donHangDao;
    @ModelAttribute("lstDonHang")
    public List<DonHang> hienThiDanhSachDonHang() {
        List<DonHang> lstDonHang = donHangDao.layDanhSach();

        return lstDonHang;
    }
}
