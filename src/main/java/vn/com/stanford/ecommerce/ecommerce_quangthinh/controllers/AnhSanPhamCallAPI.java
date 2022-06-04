package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.AnhSanPhamDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.SanPhamDao;

import java.util.List;

@Controller
@RequestMapping("call-api")
public class AnhSanPhamCallAPI {
    @Autowired
    AnhSanPhamDao anhSanPhamDao;

    @RequestMapping(value = "anhsp/all")
    public String hienThiDanhSachAnhSP(Model model) {
        List<AnhSanPham> lstAnhSanPham = anhSanPhamDao.layDanhSach();

        model.addAttribute("lstAnhSanPham", lstAnhSanPham);
        model.addAttribute("anhSanPhamSearch", new AnhSanPhamViewModel());

        return "QuanLyAnhSanPham";
    }

    @RequestMapping(value = "anhsp/timkiem")
    public String timKiemAnhSP(@ModelAttribute("anhSanPhamSearch") DanhMucViewModel anhSP, Model model) {
        List<AnhSanPham> lstAnhSanPham = anhSanPhamDao.timKiemAnhSP(anhSP.getTuKhoa());
        model.addAttribute("lstAnhSanPham", lstAnhSanPham);
        model.addAttribute("anhSanPhamSearch", anhSP);
        return "QuanLyAnhSanPham";
    }

    @Autowired
    SanPhamDao sanPhamDao;
    @ModelAttribute("lstSanPham")
    public List<SanPham> hienThiDanhSachSP() {
        List<SanPham> lstSanPham = sanPhamDao.layDanhSach();

        return lstSanPham;
    }
}
