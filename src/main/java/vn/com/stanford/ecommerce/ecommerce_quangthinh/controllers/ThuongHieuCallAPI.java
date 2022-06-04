package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieu;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.ThuongHieuViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.ThuongHieuDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class ThuongHieuCallAPI {
    @Autowired
    ThuongHieuDao thuongHieuDao;

    @RequestMapping(value = "thuonghieu/all")
    public String hienThiDanhSachThuongHieu(Model model, HttpSession session) {
        List<ThuongHieu> lstThuongHieu = thuongHieuDao.layDanhSach();

        model.addAttribute("lstThuongHieu", lstThuongHieu);
        model.addAttribute("thuongHieuSearch", new ThuongHieuViewModel());
        model.addAttribute("QL_THUONGHIEU_XEM", session.getAttribute("QL_THUONGHIEU_XEM"));
        model.addAttribute("QL_THUONGHIEU_THEM", session.getAttribute("QL_THUONGHIEU_THEM"));
        model.addAttribute("QL_THUONGHIEU_SUA", session.getAttribute("QL_THUONGHIEU_SUA"));
        model.addAttribute("QL_THUONGHIEU_XOA", session.getAttribute("QL_THUONGHIEU_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_THUONGHIEU_XEM") + "") == 1) {
            return "QuanLyThuongHieu";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "thuonghieu/timkiem")
    public String timKiemThuongHieu(@ModelAttribute("thuongHieuSearch") ThuongHieuViewModel thuongHieu, Model model, HttpSession session) {
        List<ThuongHieu> lstThuongHieu = thuongHieuDao.timKiemThuongHieu(thuongHieu.getTuKhoa());
        model.addAttribute("lstThuongHieu", lstThuongHieu);
        model.addAttribute("thuongHieuSearch", thuongHieu);
        model.addAttribute("QL_THUONGHIEU_XEM", session.getAttribute("QL_THUONGHIEU_XEM"));
        model.addAttribute("QL_THUONGHIEU_THEM", session.getAttribute("QL_THUONGHIEU_THEM"));
        model.addAttribute("QL_THUONGHIEU_SUA", session.getAttribute("QL_THUONGHIEU_SUA"));
        model.addAttribute("QL_THUONGHIEU_XOA", session.getAttribute("QL_THUONGHIEU_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_THUONGHIEU_XEM") + "") == 1) {
            return "QuanLyThuongHieu";
        } else {
            return "AuthErr";
        }
    }
}
