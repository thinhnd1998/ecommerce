package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMuc;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.DanhMucViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.DanhMucDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class DanhMucCallAPI {
    @Autowired
    DanhMucDao danhMucDao;

    @RequestMapping(value = "danhmuc/all")
    public String hienThiDanhSachDanhMuc(Model model, HttpSession session) {
        List<DanhMuc> lstDanhMuc = danhMucDao.layDanhSach();

        model.addAttribute("lstDanhMuc", lstDanhMuc);
        model.addAttribute("danhMucSearch", new DanhMucViewModel());
        model.addAttribute("QL_DANHMUC_XEM", session.getAttribute("QL_DANHMUC_XEM"));
        model.addAttribute("QL_DANHMUC_THEM", session.getAttribute("QL_DANHMUC_THEM"));
        model.addAttribute("QL_DANHMUC_SUA", session.getAttribute("QL_DANHMUC_SUA"));
        model.addAttribute("QL_DANHMUC_XOA", session.getAttribute("QL_DANHMUC_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_DANHMUC_XEM") + "") == 1) {
            return "QuanLyDanhMuc";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "danhmuc/timkiem")
    public String timKiemDanhMuc(@ModelAttribute("danhMucSearch") DanhMucViewModel danhMuc, Model model, HttpSession session) {
        List<DanhMuc> lstDanhMuc = danhMucDao.timKiemDanhMuc(danhMuc.getTuKhoa());
        model.addAttribute("lstDanhMuc", lstDanhMuc);
        model.addAttribute("danhMucSearch", danhMuc);
        model.addAttribute("QL_DANHMUC_XEM", session.getAttribute("QL_DANHMUC_XEM"));

        if (Integer.parseInt(session.getAttribute("QL_DANHMUC_XEM") + "") == 1) {
            return "QuanLyDanhMuc";
        } else {
            return "AuthErr";
        }
    }
}
