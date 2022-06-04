package vn.com.stanford.ecommerce.ecommerce_quangthinh.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTro;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.models.VaiTroViewModel;
import vn.com.stanford.ecommerce.ecommerce_quangthinh.services.VaiTroDao;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("call-api")
public class VaiTroCallAPI {
    @Autowired
    VaiTroDao vaiTroDao;

    @RequestMapping(value = "vaitro/all")
    public String hienThiDanhSachVaiTro(Model model, HttpSession session) {
        List<VaiTro> lstVaiTro = vaiTroDao.layDanhSach();

        model.addAttribute("lstVaiTro", lstVaiTro);
        model.addAttribute("vaiTroSearch", new VaiTroViewModel());
        model.addAttribute("QL_VAITRO_XEM", session.getAttribute("QL_VAITRO_XEM"));
        model.addAttribute("QL_VAITRO_THEM", session.getAttribute("QL_VAITRO_THEM"));
        model.addAttribute("QL_VAITRO_SUA", session.getAttribute("QL_VAITRO_SUA"));
        model.addAttribute("QL_VAITRO_XOA", session.getAttribute("QL_VAITRO_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_VAITRO_XEM") + "") == 1) {
            return "QuanLyVaiTro";
        } else {
            return "AuthErr";
        }
    }

    @RequestMapping(value = "vaitro/timkiem")
    public String timKiemVaiTro(@ModelAttribute("vaiTroSearch") VaiTroViewModel vaiTro, Model model, HttpSession session) {
        List<VaiTro> lstVaiTro = vaiTroDao.timKiemVaiTro(vaiTro.getTuKhoa());
        model.addAttribute("lstVaiTro", lstVaiTro);
        model.addAttribute("vaiTroSearch", vaiTro);
        model.addAttribute("QL_VAITRO_XEM", session.getAttribute("QL_VAITRO_XEM"));
        model.addAttribute("QL_VAITRO_THEM", session.getAttribute("QL_VAITRO_THEM"));
        model.addAttribute("QL_VAITRO_SUA", session.getAttribute("QL_VAITRO_SUA"));
        model.addAttribute("QL_VAITRO_XOA", session.getAttribute("QL_VAITRO_XOA"));

        if (Integer.parseInt(session.getAttribute("QL_VAITRO_XEM") + "") == 1) {
            return "QuanLyVaiTro";
        } else {
            return "AuthErr";
        }
    }
}
