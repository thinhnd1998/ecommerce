package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMucViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHang;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.KhachHangViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.KhachHangDao;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class KhachHangCallAPI {
    @Autowired
    KhachHangDao khachHangDao;

    @RequestMapping(value = "khachhang/all")
    public String hienThiDanhSachKhachHang(Model model) {
        List<KhachHang> lstKhachHang = khachHangDao.layDanhSach();

        model.addAttribute("lstKhachHang", lstKhachHang);
        model.addAttribute("khachHangSearch", new KhachHangViewModel());

        return "QuanLyKhachHang";
    }

    @RequestMapping(value = "khachhang/timkiem")
    public String timKiemKhachHang(@ModelAttribute("khachHangSearch") KhachHangViewModel khachHang, Model model) {
        List<KhachHang> lstKhachHang = khachHangDao.timKiemKhachHang(khachHang.getTuKhoa());
        model.addAttribute("lstKhachHang", lstKhachHang);
        model.addAttribute("khachHangSearch", khachHang);
        return "QuanLyKhachHang";
    }
}
