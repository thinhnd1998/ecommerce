package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.*;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.NguoiDungDao;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.VaiTroDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class NguoiDungCallAPI {
    @Autowired
    NguoiDungDao nguoiDungDao;

    @RequestMapping(value = "nguoidung/all")
    public String hienThiDanhSachNguoiDung(Model model, HttpSession session) {
        List<NguoiDungExtend> lstNguoiDung = nguoiDungDao.layDanhSachExtend();

        model.addAttribute("lstNguoiDung", lstNguoiDung);
        model.addAttribute("nguoiDungSearch", new NguoiDungViewModel());
        model.addAttribute("QL_NGUOIDUNG_XEM", session.getAttribute("QL_NGUOIDUNG_XEM"));
        model.addAttribute("QL_NGUOIDUNG_THEM", session.getAttribute("QL_NGUOIDUNG_THEM"));
        model.addAttribute("QL_NGUOIDUNG_SUA", session.getAttribute("QL_NGUOIDUNG_SUA"));
        model.addAttribute("QL_NGUOIDUNG_XOA", session.getAttribute("QL_NGUOIDUNG_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_NGUOIDUNG_XEM") + "") == 1) {
            return "QuanLyNguoiDung";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "nguoidung/timkiem")
    public String timKiemNguoiDung(@ModelAttribute("nguoiDungSearch") NguoiDungViewModel nguoiDung, Model model, HttpSession session) {
        List<NguoiDungExtend> lstNguoiDung = nguoiDungDao.timKiemNguoiDung(nguoiDung.getTuKhoa(), nguoiDung.getVaiTroId());
        model.addAttribute("lstNguoiDung", lstNguoiDung);
        model.addAttribute("nguoiDungSearch", nguoiDung);
        model.addAttribute("QL_NGUOIDUNG_XEM", session.getAttribute("QL_NGUOIDUNG_XEM"));
        model.addAttribute("QL_NGUOIDUNG_THEM", session.getAttribute("QL_NGUOIDUNG_THEM"));
        model.addAttribute("QL_NGUOIDUNG_SUA", session.getAttribute("QL_NGUOIDUNG_SUA"));
        model.addAttribute("QL_NGUOIDUNG_XOA", session.getAttribute("QL_NGUOIDUNG_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_NGUOIDUNG_XEM") + "") == 1) {
            return "QuanLyNguoiDung";
        } else {
            return "AuthErr";
        }
    }

    @Autowired
    VaiTroDao vaiTroDao;

    @ModelAttribute("lstVaiTro")
    public List<VaiTro> hienThiDanhSachVaiTro() {
        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();

        return lstVaiTro;
    }
}
