package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMucViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVien;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.NhanVienViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NhanVienDao;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class NhanVienCallAPI {
    @Autowired
    NhanVienDao nhanVienDao;

    @RequestMapping(value = "nhanvien/all")
    public String hienThiDanhSachNhanVien(Model model) {
        List<NhanVien> lstNhanVien = nhanVienDao.layDanhSach();

        model.addAttribute("lstNhanVien", lstNhanVien);
        model.addAttribute("nhanVienSearch", new NhanVienViewModel());

        return "QuanLyNhanVien";
    }

    @RequestMapping(value = "nhanvien/timkiem")
    public String timKiemNhanVien(@ModelAttribute("nhanVienSearch") NhanVienViewModel nhanVien, Model model) {
        List<NhanVien> lstNhanVien = nhanVienDao.timKiemNhanVien(nhanVien.getTuKhoa());
        model.addAttribute("lstNhanVien", lstNhanVien);
        model.addAttribute("nhanVienSearch", nhanVien);
        return "QuanLyNhanVien";
    }
}
